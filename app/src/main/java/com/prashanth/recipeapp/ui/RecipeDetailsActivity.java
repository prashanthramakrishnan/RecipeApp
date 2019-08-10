package com.prashanth.recipeapp.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.prashanth.recipeapp.R;
import com.prashanth.recipeapp.util.Constants;
import java.util.ArrayList;
import timber.log.Timber;

public class RecipeDetailsActivity extends AppCompatActivity {

    @BindView(R.id.recipe_title)
    TextView title;

    @BindView(R.id.chef_name_id_textview)
    TextView chefName;

    @BindView(R.id.description_textview)
    TextView recipeDescription;

    @BindView(R.id.tags_textview)
    TextView tags;

    @BindView(R.id.recipe_photo)
    ImageView recipePhotoImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_details);
        ButterKnife.bind(this);

        String titleText = getIntent().getStringExtra(Constants.TITLE);
        String description = getIntent().getStringExtra(Constants.DESCRIPTION);
        String chefNameText = getIntent().getStringExtra(Constants.CHEF_NAME);
        String recipePhoto = getIntent().getStringExtra(Constants.RECIPE_PHOTO_URL);
        ArrayList<String> tagsList = getIntent().getStringArrayListExtra(Constants.TAGS);

        Timber.d("Title %s description %s chefname %s photo %s", titleText, description, chefNameText, recipePhoto);

        title.setText(titleText);
        if (chefNameText != null) {
            chefName.setText(chefNameText);
        }
        recipeDescription.setText(description);
        if (tagsList != null) {
            tags.setText(tagsList.toString());
        }

        Glide.with(this)
                .load(recipePhoto)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(recipePhotoImageView);
    }
}
