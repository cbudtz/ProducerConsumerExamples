package x_data;

public class DataDAO {

    public void save(DataDTO data){
        //Simulate a slow save
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getClass() + " - Saved some data!" );
    }
}
