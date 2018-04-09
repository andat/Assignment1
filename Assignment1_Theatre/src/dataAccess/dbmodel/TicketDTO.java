package dataAccess.dbmodel;

public class TicketDTO {
    private int ticketId;
    private int showId;
    private int seatId;
    private boolean booked;

    public TicketDTO(int ticketId, int showId, int seatId, boolean booked) {
        this.ticketId = ticketId;
        this.showId = showId;
        this.seatId = seatId;
        this.booked = booked;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getShowId() {
        return showId;

    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }
}
