package com.prashanth.recipeapp.network;

import com.prashanth.recipeapp.model.RecipeResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RecipeAPI {

    @GET("spaces/{spaceID}/environments/master/entries")
    Observable<RecipeResponse> getRecipes(@Path("spaceID") String spaceID, @Query("access_token") String accessToken);

}