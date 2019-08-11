package com.prashanth.recipeapp.dependencyInjection;

import android.content.Context;
import android.graphics.Color;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.prashanth.recipeapp.adapter.RecipeListAdapter;
import com.prashanth.recipeapp.presenter.RecipeListPresenter;
import com.yydcdut.markdown.MarkdownConfiguration;
import com.yydcdut.markdown.MarkdownProcessor;
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

    @Provides
    public RecipeListPresenter provideRecipeListPresenter() {
        return new RecipeListPresenter();
    }

    @Provides
    public MarkdownProcessor provideMarkdownProcessor() {
        return new MarkdownProcessor(context);
    }

    @Provides
    public MarkdownConfiguration.Builder provideMarkdownConfigurationBuilder() {
        return new MarkdownConfiguration.Builder(context)
                .setLinkFontColor(Color.BLACK);
    }

}
