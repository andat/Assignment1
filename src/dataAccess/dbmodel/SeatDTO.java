package dataAccess.dbmodel;

public class SeatDTO {
    private int seatid;
    private String row;
    private int number;

    public int getSeatid() {
        return seatid;
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
