package com.prashanth.recipeapp.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class InnerSys {

    @SerializedName("type")
    private String type;

    @SerializedName("linkType")
    private String linkType;

    @SerializedName("id")
    private String id;

    @NotNull
    @Override
    public String toString() {
        return "InnerSys{" +
                "type='" + type + '\'' +
                ", linkType='" + linkType + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
