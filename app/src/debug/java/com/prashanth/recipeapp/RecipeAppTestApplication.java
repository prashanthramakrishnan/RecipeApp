package com.prashanth.recipeapp;

import com.prashanth.recipeapp.dependencyInjection.DaggerAppDaggerGraph;
import com.prashanth.recipeapp.dependencyInjection.NetworkDaggerModule;

public class RecipeAppTestApplication extends RecipeApplication {

    protected DaggerAppDaggerGraph.Builder daggerComponent(RecipeApplication application) {
        return super.daggerComponent(this)
                .networkDaggerModule(new NetworkDaggerModule("http://localhost:8080/"));
    }

}