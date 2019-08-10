package com.prashanth.recipeapp.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class Include {

    @SerializedName("Asset")
    private List<Assets> assets;

    @NotNull
    @Override
    public String toString() {
        return "Include{" +
                "assets=" + assets +
                '}';
    }
}
