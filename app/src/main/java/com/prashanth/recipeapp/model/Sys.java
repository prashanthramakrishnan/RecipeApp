package com.prashanth.recipeapp.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sys {

    @SerializedName("space")
    private Space space;

    @SerializedName("id")
    private String id;

    @SerializedName("type")
    private String type;

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("updatedAt")
    private String updatedAt;

    @SerializedName("environment")
    private Environment environment;

    @SerializedName("revision")
    private int revision;

    @SerializedName("contentType")
    private ContentType contentType;

    @SerializedName("locale")
    private String locale;

    @Override
    public String toString() {
        return "Sys{" +
                "space=" + space +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", environment=" + environment +
                ", revision=" + revision +
                ", contentType=" + contentType +
                ", locale='" + locale + '\'' +
                '}';
    }
}
