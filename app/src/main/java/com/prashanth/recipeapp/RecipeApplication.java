package com.prashanth.recipeapp;

import android.app.Application;
import com.prashanth.recipeapp.dependencyInjection.AppDaggerGraph;
import com.prashanth.recipeapp.dependencyInjection.ApplicationModule;
import com.prashanth.recipeapp.dependencyInjection.DaggerAppDaggerGraph;
import com.prashanth.recipeapp.dependencyInjection.NetworkDaggerModule;
import com.prashanth.recipeapp.dependencyInjection.PresenterModule;
import timber.log.Timber;

public class RecipeApplication extends Application {

    public static AppDaggerGraph component;

    protected DaggerAppDaggerGraph.Builder daggerComponent(RecipeApplication application) {
        return DaggerAppDaggerGraph.builder()
                .networkDaggerModule(new NetworkDaggerModule(BuildConfig.RECIPE_URL))
                .presenterModule(new PresenterModule(application))
                .applicationModule(new ApplicationModule(this));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = daggerComponent(this).build();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
