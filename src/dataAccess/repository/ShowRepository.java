package dataAccess.repository;

public class ShowRepository {
    private int ticketid;
    private int showid;
    private int seatid;
    private boolean sold;

    public int getTicketid() {
        return ticketid;
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

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }
}
