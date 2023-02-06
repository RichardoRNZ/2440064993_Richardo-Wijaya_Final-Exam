package com.richardo.a2440064993_richardowijaya_finalexam.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private String title;
    private String poster_path;
    private int MovieID;
    private String[] genre;
    private String overview;
    private String backdrop_path;
    private String release_date;


    public Movie(String title, String poster_path, int movieID, String[] genre, String overview, String backdrop_path, String release_date) {
        this.title = title;
        this.poster_path = poster_path;
        MovieID = movieID;
        this.genre = genre;
        this.overview = overview;
        this.backdrop_path = backdrop_path;
        this.release_date = release_date;
    }

    protected Movie(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        MovieID = in.readInt();
        overview = in.readString();
        backdrop_path = in.readString();
        release_date = in.readString();


    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public int getMovieID() {
        return MovieID;
    }

    public void setMovieID(int movieID) {
        MovieID = movieID;
    }

    public String[] getGenre() {
        return genre;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(poster_path);
        parcel.writeInt(MovieID);
        parcel.writeString(overview);
        parcel.writeString(backdrop_path);
        parcel.writeString(release_date);

    }
}
