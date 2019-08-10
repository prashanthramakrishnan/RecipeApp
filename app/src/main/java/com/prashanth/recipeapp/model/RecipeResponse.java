package com.prashanth.recipeapp.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class RecipeResponse {

    @SerializedName("sys")
    private Sys sys;

    @SerializedName("total")
    private int total;

    @SerializedName("items")
    private List<Item> items;

    @SerializedName("includes")
    private Include include;

    @NotNull
    @Override
    public String toString() {
        return "RecipeResponse{" +
                "total=" + total +
                ", items=" + items +
                '}';
    }
}
