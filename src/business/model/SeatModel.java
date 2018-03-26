package business.model;

public class SeatModel {
    private int id;
    private String row;
    private int number;

    public SeatModel(int id, String row, int number) {
        this.id = id;
        this.row = row;
        this.number = number;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
