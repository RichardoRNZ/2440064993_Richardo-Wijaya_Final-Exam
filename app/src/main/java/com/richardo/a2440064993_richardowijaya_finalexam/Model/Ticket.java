package com.richardo.a2440064993_richardowijaya_finalexam.Model;

public class Ticket {
    private String ID;
    private String MovieTitle;
    private String CinemaName;
    private String MoviePoster;

    public Ticket(String ID, String movieTitle, String cinemaName, String moviePoster) {
        this.ID = ID;
        MovieTitle = movieTitle;
        CinemaName = cinemaName;
        MoviePoster = moviePoster;
    }

    public Ticket() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMovieTitle() {
        return MovieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        MovieTitle = movieTitle;
    }

    public String getCinemaName() {
        return CinemaName;
    }

    public void setCinemaName(String cinemaName) {
        CinemaName = cinemaName;
    }

    public String getMoviePoster() {
        return MoviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        MoviePoster = moviePoster;
    }
}
