package com.prashanth.recipeapp.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {

    @SerializedName("sys")
    private Sys sys;

    @SerializedName("fields")
    private Fields fields;

    @Override
    public String toString() {
        return "Item{" +
                "sys=" + sys +
                ", fields=" + fields +
                '}';
    }
}
