package adapters;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.helloar.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import at.favre.lib.dali.Dali;
import models.WikiModel;

public class WikiAdapter extends RecyclerView.Adapter<WikiAdapter.ViewHolder> {

    private List<WikiModel> wikiList;

    public WikiAdapter() {
        this.wikiList = new ArrayList<>();
    }

    public void getWikiList(List<WikiModel> cList) {
        this.wikiList = cList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wiki_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WikiModel currentItem = wikiList.get(position);
        Picasso.get().load("http://143.110.151.110:5100/"+currentItem.getImage()).into(holder.image);
        holder.title.setText(currentItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return wikiList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_wiki);
            image = itemView.findViewById(R.id.image_wiki);
        }
    }
}
