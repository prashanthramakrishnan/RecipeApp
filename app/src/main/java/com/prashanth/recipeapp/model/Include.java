package com.prashanth.recipeapp.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Include {

    @SerializedName("Asset")
    private List<Assets> assets;

    @Override
    public String toString() {
        return "Include{" +
                "assets=" + assets +
                '}';
    }
}
