package com.prashanth.recipeapp.contract;

import com.prashanth.recipeapp.model.Assets;
import com.prashanth.recipeapp.model.Item;
import java.util.ArrayList;

public interface APIContract {

    interface View {

        void callStarted();

        void callComplete();

        void callFailed(Throwable throwable);
    }

    interface RecipeListView extends View {

        void onResponseRecipeList(ArrayList<Item> itemsList, ArrayList<Assets> assetsList, ArrayList<Item> chefsAndTagsList);
    }

    interface Presenter {

        void unsubscribe();

        void onDestroy();

        void fetchRecipe(String spaceID, String accessToken, APIContract.RecipeListView view);
    }

}
