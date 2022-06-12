package data;

import java.util.UUID;

public class DataGenerator implements Runnable, DataObservable{

    private final long waitingTime;

    public DataGenerator(long waitingTime){
        this.waitingTime =waitingTime;
    }

    private DataObserver observer;

    @Override
    public void run() {
        while (true) {
            try {
                //Simulate sample frequency
                Thread.sleep(waitingTime);
                if (this.observer != null) {
                    //Some random data:
                    DataDTO dataDTO = new DataDTO();
                    dataDTO.setSomeID((int) Math.random() * 1000);
                    dataDTO.setSomeData(UUID.randomUUID().toString());
                    //System.out.println("Generated some data: " + dataDTO);
                    observer.handle(dataDTO);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void registerObserver(DataObserver observer) {
        this.observer = observer;

    }
}
