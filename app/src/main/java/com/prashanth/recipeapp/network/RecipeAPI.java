package com.prashanth.recipeapp.network;

import com.prashanth.recipeapp.model.RecipeResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RecipeAPI {

    //kk2bw5ojx476
    //7ac531648a1b5e1dab6c18b0979f822a5aad0fe5f1109829b8a197eb2be4b84c
    @GET("spaces/{spaceID}/environments/master/entries")
    Observable<RecipeResponse> getRecipes(@Path("spaceID") String spaceID, @Query("access_token") String accessToken);

}