package com.prashanth.recipeapp.presenter;

import com.prashanth.recipeapp.RecipeApplication;
import com.prashanth.recipeapp.contract.APIContract;
import com.prashanth.recipeapp.model.Assets;
import com.prashanth.recipeapp.model.Item;
import com.prashanth.recipeapp.model.RecipeResponse;
import com.prashanth.recipeapp.network.RecipeAPI;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import javax.inject.Inject;

public class RecipeListPresenter implements APIContract.Presenter {

    @Inject
    RecipeAPI api;

    private String spaceID;

    private String accessToken;

    private CompositeDisposable compositeDisposable;

    private APIContract.RecipeListView view;

    private ArrayList<Item> itemsList = new ArrayList<>();

    private ArrayList<Assets> assetsList = new ArrayList<>();

    private ArrayList<Item> chefsAndTagsList = new ArrayList<>();

    public RecipeListPresenter() {
        compositeDisposable = new CompositeDisposable();
        RecipeApplication.component.inject(this);
    }

    @Override
    public void subscribe() {
        fetchRecipe(spaceID, accessToken, view);
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void fetchRecipe(String spaceID, String accessToken, APIContract.RecipeListView view) {
        this.view = view;
        this.spaceID = spaceID;
        this.accessToken = accessToken;
        this.view.callStarted();
        Disposable disposable = api.getRecipes(spaceID,
                accessToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<RecipeResponse>() {
                    @Override
                    public void onNext(RecipeResponse response) {
                        getItemsChefAndTags(response);
                        getAssets(response);
                        view.onResponseRecipeList(itemsList, assetsList, chefsAndTagsList);

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.callFailed(e);
                    }

                    @Override
                    public void onComplete() {
                        view.callComplete();
                    }
                });
        compositeDisposable.add(disposable);
    }

    private void getAssets(RecipeResponse response) {
        if (response.getInclude() != null && response.getInclude().getAssets() != null && !response.getInclude().getAssets().isEmpty()) {
            assetsList = response.getInclude().getAssets();
        }
    }

    private void getItemsChefAndTags(RecipeResponse response) {
        if (response.getItems() != null && !response.getItems().isEmpty()) {
            for (Item item : response.getItems()) {
                if (item.getFields().getTitle() != null) {
                    itemsList.add(item);
                } else {
                    chefsAndTagsList.add(item);
                }
            }
        }
    }
}
