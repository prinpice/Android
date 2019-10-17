package com.android.popcorn;

public class MovieListItem {

    private int id;
    private String title_ko;
    private String title_en;
    private String open_year;
    private String genre;
    private String nation;
    private String director;
    private String description;
    private String poster_url;
    private String image_url;

    public MovieListItem(){}

    public MovieListItem(int id, String title_ko, String title_en, String open_year, String genre, String nation, String director, String description, String poster_url, String image_url){
        this.id = id;
        this.title_ko= title_ko;
        this.title_en = title_en;
        this.open_year = open_year;
        this.genre = genre;
        this.nation = nation;
        this.director = director;
        this.description = description;
        this.poster_url = poster_url;
        this.image_url = image_url;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle_ko() {
        return title_ko;
    }

    public void setTitle_ko(String title_ko) {
        this.title_ko = title_ko;
    }

    public String getTitle_en() {
        return title_en;
    }

    public void setTitle_en(String title_en) {
        this.title_en = title_en;
    }

    public String getOpen_year() {
        return open_year;
    }

    public void setOpen_year(String open_year) {
        this.open_year = open_year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster_url() {
        return poster_url;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
