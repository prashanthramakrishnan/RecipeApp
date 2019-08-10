package com.prashanth.recipeapp.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Environment {

    @SerializedName("sys")
    private InnerSys sys;

    @Override
    public String toString() {
        return "Environment{" +
                "sys=" + sys +
                '}';
    }
}
