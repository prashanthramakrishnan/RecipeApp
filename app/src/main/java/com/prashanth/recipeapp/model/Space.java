package com.prashanth.recipeapp.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class Space {

    @SerializedName("sys")
    private Sys sys;

    @NotNull
    @Override
    public String toString() {
        return "Space{" +
                "sys=" + sys +
                '}';
    }
}
