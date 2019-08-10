package com.prashanth.recipeapp.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class File {

    @SerializedName("url")
    private String url;

    @SerializedName("details")
    private Details details;

    @SerializedName("fileName")
    private String fileName;

    @SerializedName("contentType")
    private String contentType;

}
