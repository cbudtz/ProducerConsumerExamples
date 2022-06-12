package c_waitonempty;

import data.DataDTO;
import data.DataGenerator;
import data.DataObserver;

/**
 * Even More advanced - Consumer waiting if buffer is empty
 */
public class DataController implements DataObserver {

    private DataGenerator dataGen;
    private DataConsumer consumer;

    public static void main(String[] args) {
        new DataController().run();
    }

    private void run() {
        dataGen = new DataGenerator(1500);
        new Thread(dataGen).start();

        dataGen.registerObserver(this);
        consumer = new DataConsumer();
        new Thread(consumer).start();
    }

    @Override
    public void handle(DataDTO data) {
        consumer.enqueue(data);
        //This wakes up the consumer to save data
        consumer.notifyOnEmpty();
    }
}
