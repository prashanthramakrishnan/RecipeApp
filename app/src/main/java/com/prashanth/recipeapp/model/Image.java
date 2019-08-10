package com.prashanth.recipeapp.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Image {

    @SerializedName("width")
    private int width;

    @SerializedName("height")
    private int height;

}
