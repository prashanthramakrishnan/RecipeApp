package com.prashanth.recipeapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.internal.LinkedTreeMap;
import com.prashanth.recipeapp.R;
import com.prashanth.recipeapp.model.Assets;
import com.prashanth.recipeapp.model.Fields;
import com.prashanth.recipeapp.model.Item;
import com.prashanth.recipeapp.ui.RecipeDetailsActivity;
import com.prashanth.recipeapp.util.Constants;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {

    private Context context;

    private List<Assets> assetsList;

    private List<Item> itemList;

    private List<Item> tagsAndChefsList;

    public RecipeListAdapter(Context context) {
        this.context = context;
    }

    @NotNull
    @Override
    public RecipeListAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_preview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = itemList.get(position).getFields().getTitle();
        String recipePhotoUrl = "https:" + getPhotoUrl(itemList.get(position).getFields().getPhoto().getSys().getId());
        String chefName = getChefName(itemList.get(position).getFields());
        String description = itemList.get(position).getFields().getDescription();
        ArrayList<String> tags = getTags(itemList.get(position).getFields());

        holder.recipeTitle.setText(title);
        Glide.with(context.getApplicationContext())
                .load(recipePhotoUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(holder.recipePhoto);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, RecipeDetailsActivity.class);
            intent.putExtra(Constants.TITLE, title);
            intent.putExtra(Constants.RECIPE_PHOTO_URL, recipePhotoUrl);
            intent.putExtra(Constants.CHEF_NAME, chefName);
            intent.putExtra(Constants.DESCRIPTION, description);
            intent.putStringArrayListExtra(Constants.TAGS, tags);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.recipe_photo)
        ImageView recipePhoto;

        @BindView(R.id.recipe_title)
        TextView recipeTitle;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void update(List<Item> itemList, List<Assets> assetsList, List<Item> tagsAndChefsList) {
        this.itemList = itemList;
        this.tagsAndChefsList = tagsAndChefsList;
        this.assetsList = assetsList;
        notifyDataSetChanged();
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

    private String getChefName(Fields fields) {
        if (fields.getChef() != null) {
            for (Item item : tagsAndChefsList) {
                if (item.getSys().getId().equals(fields.getChef().getSys().getId())) {
                    return item.getFields().getName();
                }
            }
        }
        return null;
    }

    private ArrayList<String> getTags(Fields fields) {
        ArrayList<String> tags = new ArrayList<>();
        if (fields.getTags() != null) {
            for (Object sys : fields.getTags()) {
                for (Item item : tagsAndChefsList) {
                    if (((LinkedTreeMap) ((LinkedTreeMap) sys).get("sys")).get("id").equals(item.getSys().getId())) {
                        tags.add(item.getFields().getName());
                    }
                }
            }
        }
        return tags;
    }

}
