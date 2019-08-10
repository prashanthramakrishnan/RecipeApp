package com.prashanth.recipeapp.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class Item {

    @SerializedName("sys")
    private Sys sys;

    @SerializedName("fields")
    private Fields fields;

    @NotNull
    @Override
    public String toString() {
        return "Item{" +
                "sys=" + sys +
                ", fields=" + fields +
                '}';
    }
}
