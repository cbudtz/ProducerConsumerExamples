package c_waitonempty;

import data.DataDAO;
import data.DataDTO;

import java.util.LinkedList;
import java.util.List;

public class DataConsumer implements Runnable{
    private static final int MAX_SIZE = 1500;
    private final LinkedList<DataDTO> dataList = new LinkedList<>();
    private final DataDAO dataDAO = new DataDAO();
    private final Object emptyLock = new Object();

    public void enqueue(DataDTO data){
        synchronized (dataList){
            // In case buffer is overrun, we just drop data -
            // This is instead of Pausing the producer if the queue is full. (fullLock)
            if (dataList.size()<MAX_SIZE) {
                dataList.add(data);
            }
        }
    }

    public void waitOnEmpty() throws InterruptedException {
        synchronized (emptyLock){
            emptyLock.wait();
        }
    }
    public void notifyOnEmpty(){
        synchronized (emptyLock){
            emptyLock.notifyAll();
        }
    }


    @Override
    public void run() {
        while(true){
            if (dataList.isEmpty()){
                try {
                    //This makes the Thread pause until the producer wakes it up
                    waitOnEmpty();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
            List<DataDTO> listCopy;
            synchronized (dataList){
                //Take a copy of list and empty it;
                listCopy = new LinkedList<>();
                listCopy.addAll(dataList);
                dataList.clear();

            }
            dataDAO.save(listCopy);
        }
    }
}
