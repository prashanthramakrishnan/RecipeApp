package com.prashanth.recipeapp.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Details {

    @SerializedName("size")
    private int size;

    @SerializedName("image")
    private Image image;

}
