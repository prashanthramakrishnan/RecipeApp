package com.prashanth.recipeapp.dependencyInjection;

import com.prashanth.recipeapp.presenter.RecipeListPresenter;
import com.prashanth.recipeapp.ui.MainActivity;
import com.prashanth.recipeapp.ui.RecipeDetailsActivity;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {NetworkDaggerModule.class, ApplicationModule.class, PresenterModule.class})
public interface AppDaggerGraph {

    void inject(MainActivity activity);

    void inject(RecipeListPresenter presenter);

    void inject(RecipeDetailsActivity activity);
}