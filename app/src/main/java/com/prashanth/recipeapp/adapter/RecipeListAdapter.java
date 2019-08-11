package com.prashanth.recipeapp.adapter;

import static com.prashanth.recipeapp.util.Utils.getChefName;
import static com.prashanth.recipeapp.util.Utils.getPhotoUrl;
import static com.prashanth.recipeapp.util.Utils.getTags;

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
import com.prashanth.recipeapp.R;
import com.prashanth.recipeapp.model.Assets;
import com.prashanth.recipeapp.model.Item;
import com.prashanth.recipeapp.ui.RecipeDetailsActivity;
import com.prashanth.recipeapp.util.Utils;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {

    private Context context;

    private ArrayList<Assets> assetsList;

    private ArrayList<Item> itemList;

    private ArrayList<Item> tagsAndChefsList;

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
        String recipePhotoUrl = getPhotoUrl(itemList.get(position).getFields().getPhoto().getSys().getId(), assetsList);
        String chefName = getChefName(itemList.get(position).getFields(), tagsAndChefsList);
        String description = itemList.get(position).getFields().getDescription();
        ArrayList<String> tags = getTags(itemList.get(position).getFields(), tagsAndChefsList);

        holder.recipeTitle.setText(title);
        Glide.with(context.getApplicationContext())
                .load(recipePhotoUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(holder.recipePhoto);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, RecipeDetailsActivity.class);
            intent.putExtra(Utils.TITLE, title);
            intent.putExtra(Utils.RECIPE_PHOTO_URL, recipePhotoUrl);
            intent.putExtra(Utils.CHEF_NAME, chefName);
            intent.putExtra(Utils.DESCRIPTION, description);
            intent.putStringArrayListExtra(Utils.TAGS, tags);
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

    public void update(ArrayList<Item> itemList, ArrayList<Assets> assetsList, ArrayList<Item> tagsAndChefsList) {
        this.itemList = itemList;
        this.tagsAndChefsList = tagsAndChefsList;
        this.assetsList = assetsList;
        notifyDataSetChanged();
    }

}
