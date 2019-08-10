package com.prashanth.recipeapp.dependencyInjection;

import com.prashanth.recipeapp.ui.MainActivity;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {NetworkDaggerModule.class, ApplicationModule.class, PresenterModule.class})
public interface AppDaggerGraph {

    void inject(MainActivity activity);

}