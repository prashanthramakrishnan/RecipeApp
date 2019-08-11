package com.prashanth.recipeapp.util;

import com.google.gson.internal.LinkedTreeMap;
import com.prashanth.recipeapp.model.Assets;
import com.prashanth.recipeapp.model.Fields;
import com.prashanth.recipeapp.model.Item;
import java.util.ArrayList;

public class Utils {

    public static final String TITLE = "TITLE";

    public static final String RECIPE_PHOTO_URL = "RECIPE_PHOTO_URL";

    public static final String CHEF_NAME = "CHEF_NAME";

    public static final String DESCRIPTION = "DESCRIPTION";

    public static final String TAGS = "TAGS";

    public static ArrayList<String> getTags(Fields fields, ArrayList<Item> tagsAndChefsList) {
        ArrayList<String> tags = new ArrayList<>();
        if (fields.getTags() != null) {
            for (Object sys : fields.getTags()) {
                for (Item item : tagsAndChefsList) {
                    if (((LinkedTreeMap) ((LinkedTreeMap) sys).get("sys")).get("id").equals(item.getSys().getId())) {
                        tags.add(item.getFields().getName());
                    }
                }
            }
        }
        return tags;
    }

    public static String getChefName(Fields fields, ArrayList<Item> tagsAndChefsList) {
        if (fields.getChef() != null) {
            for (Item item : tagsAndChefsList) {
                if (item.getSys().getId().equals(fields.getChef().getSys().getId())) {
                    return item.getFields().getName();
                }
            }
        }
        return null;
    }

    public static String getPhotoUrl(String id, ArrayList<Assets> assetsList) {
        if (id != null) {
            for (Assets asset : assetsList) {
                if (asset.getSys().getId().equals(id)) {
                    return "https:" + asset.getFields().getFile().getUrl();
                }
            }
        }
        return null;
    }

}
