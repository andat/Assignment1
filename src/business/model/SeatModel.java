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

    @Override
    public String toString() {
        return row + number;
    }

    public int getId() {
        return this.id;
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
