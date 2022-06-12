package b_buffering;

import data.DataDAO;
import data.DataDTO;

import java.util.LinkedList;
import java.util.List;

public class DataConsumer implements Runnable{
    private final LinkedList<DataDTO> dataList = new LinkedList<>();
    private final DataDAO dataDAO = new DataDAO();

    public void enqueue(DataDTO data){
        synchronized (dataList){
            dataList.add(data);
        }
    }


    @Override
    public void run() {
        while(true){
            List<DataDTO> listCopy;
            synchronized (dataList){
                //Take a copy of list and empty it;
                listCopy = new LinkedList<>(dataList);
                dataList.clear();
            }
            dataDAO.save(listCopy);
        }
    }
}
