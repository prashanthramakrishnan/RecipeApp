package com.prashanth.recipeapp.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentType {

    @SerializedName("sys")
    private InnerSys innerSys;

    @Override
    public String toString() {
        return "ContentType{" +
                "innerSys=" + innerSys +
                '}';
    }
}
