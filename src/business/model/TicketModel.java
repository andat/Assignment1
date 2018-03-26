package business.model;

public class TicketModel {

    private int id;
    private int showid;
    private int seatid;
    private boolean booked;

    public TicketModel(int id, int showid, int seatid, boolean booked) {
        this.id = id;
        this.showid = showid;
        this.seatid = seatid;
        this.booked = booked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShowid() {
        return showid;
    }

    public void setShowid(int showid) {
        this.showid = showid;
    }

    public int getSeatid() {
        return seatid;
    }

    public void setSeatid(int seatid) {
        this.seatid = seatid;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

}
