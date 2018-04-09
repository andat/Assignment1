package dataAccess.dbmodel;

public class SeatDTO {
    private int seatId;
    private String row;
    private int number;

    public SeatDTO(int seatId, String row, int number) {
        this.seatId = seatId;
        this.row = row;
        this.number = number;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
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
