package com.prashanth.recipeapp.dependencyInjection;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.prashanth.recipeapp.adapter.RecipeListAdapter;
import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    private Context context;

    public PresenterModule(Context context) {
        this.context = context;
    }

    @Provides
    public RecipeListAdapter provideRecyclerViewAdapter() {
        return new RecipeListAdapter(context);
    }

    @Provides
    public LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(context);
    }

}
