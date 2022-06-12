package data;

import java.util.List;

public class DataDAO {

    public void save(DataDTO data){
        //Simulate a slow save
        try {
            Thread.sleep(500);
            System.out.println("Data Saved: " + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void save(List<DataDTO> dataList){
        //Simulate a slow save
        try {
            Thread.sleep(500);
            System.out.println("Data Saved: " + dataList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
