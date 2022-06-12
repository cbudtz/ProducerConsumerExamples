package a_simplesolution;

import data.*;

/**
 * Naive solution - Saving delays sampling
 */
public class DataController implements DataObserver {

    private DataGenerator dataGen;
    private DataDAO dataDao;

    public static void main(String[] args) {
        new DataController().run();
    }

    private void run() {
        dataGen = new DataGenerator(200);
        new Thread(dataGen).start();

        dataGen.registerObserver(this);
        dataDao = new DataDAO();
    }

    @Override
    public void handle(DataDTO data) {
        dataDao.save(data);
    }
}
