package business.model;

import java.sql.Date;

public class ShowModel {
    private int id;
    private String title;
    private String genre;
    private String distribution;
    private Date date;
    private int noOfTickets;

    public ShowModel(int id, String title, String genre, String distribution, Date date, int noOfTickets) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.date = date;
        this.distribution = distribution;
        this.noOfTickets = noOfTickets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public int getNoOfTickets() {
        return noOfTickets;
    }

    public void setNoOfTickets(int noOfTickets) {
        this.noOfTickets = noOfTickets;
    }
}
