package com.android.shopback.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dev on 15/04/17.
 */

public class Movie implements Serializable{

    @SerializedName("poster_path")       public String imagePath;
    @SerializedName("adult")             public boolean isForAdult;
    @SerializedName("release_date")      public String releaseDate;
    @SerializedName("original_title")    public String orignalTitle;
    @SerializedName("original_language") public String language;
    @SerializedName("genres") public List<Genres> genreList;
    @SerializedName("spoken_languages") public List<Language> languageList;
    @SerializedName("id")       public String movieId;
    @SerializedName("runtime") public String duration="";
    public String overview;
    public String title;
    public String popularity;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isForAdult() {
        return isForAdult;
    }

    public void setForAdult(boolean forAdult) {
        isForAdult = forAdult;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOrignalTitle() {
        return orignalTitle;
    }

    public void setOrignalTitle(String orignalTitle) {
        this.orignalTitle = orignalTitle;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public List<Genres> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genres> genreList) {
        this.genreList = genreList;
    }

    public List<Language> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<Language> languageList) {
        this.languageList = languageList;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;

    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
}
