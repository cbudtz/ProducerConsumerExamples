package data;

public class DataDTO {
    private String someData;
    private int someID;

    @Override
    public String toString() {
        return "DataDTO{" +
                "someData='" + someData + '\'' +
                ", someID=" + someID +
                '}';
    }

    public int getSomeID() {
        return someID;
    }

    public void setSomeID(int someID) {
        this.someID = someID;
    }

    public String getSomeData() {
        return someData;
    }

    public void setSomeData(String someData) {
        this.someData = someData;
    }
}
