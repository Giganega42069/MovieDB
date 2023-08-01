package sg.edu.rp.c346.id22004686.moviedb;

import java.io.Serializable;

public class Movie implements Serializable {

    private int id;
    private String mtitle;
    private String mgenre;
    private int year;
    private String rating;

    public Movie(int id, String mtitle, String mgenre, int year, String rating) {
        this.id = id;
        this.mtitle = mtitle;
        this.mgenre = mgenre;
        this.year = year;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getMtitle() {
        return mtitle;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public String getMgenre() {
        return mgenre;
    }

    public void setMgenre(String mgenre) {
        this.mgenre = mgenre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
