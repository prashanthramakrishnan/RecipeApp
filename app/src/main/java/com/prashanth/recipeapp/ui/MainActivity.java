package com.prashanth.recipeapp.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.material.snackbar.Snackbar;
import com.prashanth.recipeapp.R;
import com.prashanth.recipeapp.RecipeApplication;
import com.prashanth.recipeapp.adapter.RecipeListAdapter;
import com.prashanth.recipeapp.contract.APIContract;
import com.prashanth.recipeapp.model.Assets;
import com.prashanth.recipeapp.model.Item;
import com.prashanth.recipeapp.network.RecipeAPI;
import com.prashanth.recipeapp.presenter.RecipeListPresenter;
import java.util.ArrayList;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements APIContract.RecipeListView {

    private static final String SPACE_ID = "kk2bw5ojx476";

    private static final String ACCESS_TOKEN = "7ac531648a1b5e1dab6c18b0979f822a5aad0fe5f1109829b8a197eb2be4b84c";

    @Inject
    RecipeAPI api;

    @Inject
    RecipeListAdapter adapter;

    @Inject
    LinearLayoutManager linearLayoutManager;

    @Inject
    RecipeListPresenter presenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecipeApplication.component.inject(this);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        presenter.fetchRecipe(SPACE_ID, ACCESS_TOKEN, this);

    }

    @Override
    public void callStarted() {
        showProgressDialog();
    }

    @Override
    public void callComplete() {
        dismissProgressDialog();
    }

    @Override
    public void callFailed(Throwable throwable) {
        dismissProgressDialog();
        showSnackBarError();
    }

    @Override
    public void onResponseRecipeList(ArrayList<Item> itemsList, ArrayList<Assets> assetsList, ArrayList<Item> chefsAndTagsList) {
        adapter.update(itemsList, assetsList, chefsAndTagsList);
    }

    private void showProgressDialog() {
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.setMessage(getString(R.string.loading));
            progressDialog.show();
            progressDialog.setCancelable(false);
        }
    }

    private void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    private void showSnackBarError() {
        Snackbar.make(findViewById(android.R.id.content), getString(R.string.error), Snackbar.LENGTH_LONG)
                .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                .show();
    }
}
