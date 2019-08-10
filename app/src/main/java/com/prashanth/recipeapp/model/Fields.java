package com.prashanth.recipeapp.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class Fields {

    @SerializedName("name")
    private String name;

    @SerializedName("country")
    private String country;

    @SerializedName("brand")
    private String brand;

    @SerializedName("slug")
    private String slug;

    @SerializedName("javascript")
    private String javascript;

    @SerializedName("css")
    private String css;

    @SerializedName("body")
    private String body;

    @SerializedName("title")
    private String title;

    @SerializedName("photo")
    private Photo photo;

    @SerializedName("calories")
    private int calories;

    @SerializedName("description")
    private String description;

    @SerializedName("chef")
    private Chef chef;

    @SerializedName("tags")
    private List<Object> tags;

    @SerializedName("file")
    private File file;

    @NotNull
    @Override
    public String toString() {
        return "Fields{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", photo=" + photo +
                ", calories=" + calories +
                ", description='" + description + '\'' +
                ", chef=" + chef +
                ", tags=" + tags +
                ", file=" + file +
                '}';
    }
}
