package dataAccess.dbmodel;

import java.sql.Date;

public class ShowDTO {
    private int showId;
    private String title;
    private String genre;
    private String distribution;
    private Date date;
    private int noOfTickets;

    public ShowDTO(int showId, String title, String genre, String distribution, Date date, int noOfTickets) {
        this.showId = showId;
        this.title = title;
        this.genre = genre;
        this.distribution = distribution;
        this.date = date;
        this.noOfTickets = noOfTickets;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNoOfTickets() {
        return noOfTickets;
    }

    public void setNoOfTickets(int noOfTickets) {
        this.noOfTickets = noOfTickets;
    }
}

