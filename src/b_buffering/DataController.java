package b_buffering;

import data.DataDAO;
import data.DataDTO;
import data.DataGenerator;
import data.DataObserver;

import java.util.List;

/**
 * More advanced - Buffering until ready to save...
 */
public class DataController implements DataObserver {

    private DataGenerator dataGen;
    private DataConsumer consumer;

    public static void main(String[] args) {
        new DataController().run();
    }

    private void run() {
        dataGen = new DataGenerator(200);
        new Thread(dataGen).start();

        dataGen.registerObserver(this);
        consumer = new DataConsumer();
        new Thread(consumer).start();
    }

    @Override
    public void handle(DataDTO data) {
        consumer.enqueue(data);
    }
}
