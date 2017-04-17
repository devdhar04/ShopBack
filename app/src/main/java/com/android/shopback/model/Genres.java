package com.android.shopback.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dev on 16/04/17.
 */

public class Genres implements Serializable{

    @SerializedName("id")             public String id;
    @SerializedName("name")      public String genresName;

    public String getIs() {
        return id;
    }

    public void setIs(String is) {
        this.id = is;
    }

    public String getGenresName() {
        return genresName;
    }

    public void setGenresName(String genresName) {
        this.genresName = genresName;
    }
}
