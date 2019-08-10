package com.prashanth.recipeapp.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tags {

    @SerializedName("sys")
    private List<InnerSys> sys;

}
