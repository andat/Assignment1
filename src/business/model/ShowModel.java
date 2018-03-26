package business.model;

import java.util.Date;

public class ShowModel {
    private enum Genre {Opera, Theatre, Concert, Ballet }
    private int id;
    private String title;
    private Genre genre;
    private Date date;
    private String distribution;
    private int no_of_tickets;

    public ShowModel(int id, String title, Genre genre, Date date, String distribution, int no_of_tickets) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.date = date;
        this.distribution = distribution;
        this.no_of_tickets = no_of_tickets;
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
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

    public int getNo_of_tickets() {
        return no_of_tickets;
    }

    public void setNo_of_tickets(int no_of_tickets) {
        this.no_of_tickets = no_of_tickets;
    }
}
