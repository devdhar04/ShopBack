package com.android.shopback.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dev on 16/04/17.
 */

public class Language implements Serializable {

    @SerializedName("iso_639_1")
    public String languageCode;
    @SerializedName("name")
    public String languageName;

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }
}