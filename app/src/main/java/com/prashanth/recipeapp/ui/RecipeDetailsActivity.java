package com.prashanth.recipeapp.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.prashanth.recipeapp.R;
import com.prashanth.recipeapp.RecipeApplication;
import com.prashanth.recipeapp.util.Utils;
import com.yydcdut.markdown.MarkdownConfiguration;
import com.yydcdut.markdown.MarkdownProcessor;
import com.yydcdut.markdown.callback.OnLinkClickCallback;
import com.yydcdut.markdown.syntax.text.TextFactory;
import java.util.ArrayList;
import javax.inject.Inject;

public class RecipeDetailsActivity extends AppCompatActivity implements OnLinkClickCallback {

    @Inject
    MarkdownProcessor markdownProcessor;

    @Inject
    MarkdownConfiguration.Builder builder;

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
        RecipeApplication.component.inject(this);
        ButterKnife.bind(this);
        setUpMarkdownConverter();

        String chefNameText = getIntent().getStringExtra(Utils.CHEF_NAME);
        ArrayList<String> tagsList = getIntent().getStringArrayListExtra(Utils.TAGS);
        recipeDescription.setMovementMethod(LinkMovementMethod.getInstance());

        title.setText(getIntent().getStringExtra(Utils.TITLE));

        if (chefNameText != null) {
            chefName.setText(chefNameText);
        }

        if (!tagsList.isEmpty()) {
            tags.setText(tagsList.toString());
        }

        recipeDescription.setText(markdownProcessor.parse(getIntent().getStringExtra(Utils.DESCRIPTION)));

        Glide.with(this)
                .load(getIntent().getStringExtra(Utils.RECIPE_PHOTO_URL))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(recipePhotoImageView);
    }

    @Override
    public void onLinkClicked(View view, String link) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
    }

    private void setUpMarkdownConverter() {
        markdownProcessor.factory(TextFactory.create());
        MarkdownConfiguration configuration = builder.setOnLinkClickCallback(this).build();
        markdownProcessor.config(configuration);
    }
}
