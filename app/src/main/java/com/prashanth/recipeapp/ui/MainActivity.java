package com.prashanth.recipeapp.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.contentful.java.cda.CDAArray;
import com.contentful.java.cda.CDAClient;
import com.contentful.java.cda.CDAEntry;
import com.contentful.java.cda.CDAResource;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prashanth.recipeapp.R;
import com.prashanth.recipeapp.RecipeApplication;
import com.prashanth.recipeapp.adapter.RecipeListAdapter;
import com.prashanth.recipeapp.model.Assets;
import com.prashanth.recipeapp.model.Item;
import com.prashanth.recipeapp.model.RecipeResponse;
import com.prashanth.recipeapp.network.RecipeAPI;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private static final String SPACE_ID = "kk2bw5ojx476";

    private static final String ACCESS_TOKEN = "7ac531648a1b5e1dab6c18b0979f822a5aad0fe5f1109829b8a197eb2be4b84c";

    @Inject
    RecipeAPI api;

    @Inject
    RecipeListAdapter adapter;

    @Inject
    LinearLayoutManager linearLayoutManager;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<Item> itemList = new ArrayList<>();

    private List<Assets> assetsList = new ArrayList<>();

    private List<Item> tagsAndChefsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecipeApplication.component.inject(this);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        Disposable disposable = api.getRecipes(SPACE_ID,
                ACCESS_TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<RecipeResponse>() {
                    @Override
                    public void onNext(RecipeResponse response) {
                        Timber.d("Recipe response %s", response.toString());
                        //get items
                        if (response.getItems() != null && !response.getItems().isEmpty()) {
                            for (Item item : response.getItems()) {
                                if (item.getFields().getTitle() != null) {
                                    itemList.add(item);
                                } else {
                                    tagsAndChefsList.add(item);
                                }
                            }
                        }

                        //get assets
                        if (response.getInclude() != null && response.getInclude().getAssets() != null && !response.getInclude().getAssets().isEmpty()) {
                            assetsList = response.getInclude().getAssets();
                        }

                        adapter.update(itemList, assetsList, tagsAndChefsList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "error");
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

    private String getPhotoUrl(String id) {
        if (id != null) {
            for (Assets asset : assetsList) {
                if (asset.getSys().getId().equals(id)) {
                    return asset.getFields().getFile().getUrl();
                }
            }
        }
        return null;
    }

    private void contentFulSDKAPI() {
        CDAClient client = CDAClient.builder()
                .setSpace(SPACE_ID)
                .setToken(ACCESS_TOKEN)
                .build();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        client.observe(CDAEntry.class)
                .where("content_type", "tag")
                .all()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((new DisposableSubscriber<CDAArray>() {
                    CDAArray result;

                    @Override
                    public void onError(Throwable error) {
                        Timber.e(error, "could not request entry");
                    }

                    @Override
                    public void onComplete() {
                        for (CDAResource resource : result.items()) {
                            CDAEntry entry = (CDAEntry) resource;
//                            Timber.d(entry.getField("productName").toString());
                        }
                    }

                    @Override
                    public void onNext(CDAArray cdaArray) {
                        result = cdaArray;
                    }
                }));
    }
}
