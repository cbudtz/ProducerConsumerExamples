package x_data;

import java.util.UUID;

public class DataGenerator implements Runnable, DataObservable{


    private DataObserver observer;

    @Override
    public void run() {
        try {
            //Simulate sample frequency
            Thread.sleep(200);
            if (this.observer!=null){
                //Some random data:
                DataDTO dataDTO = new DataDTO();
                dataDTO.setSomeID((int) Math.random()*1000);
                dataDTO.setSomeData(UUID.randomUUID().toString());
                observer.handle(dataDTO);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void registerObserver(DataObserver observer) {
        this.observer = observer;

    }
}
