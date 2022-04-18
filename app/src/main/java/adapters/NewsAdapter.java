package adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.helloar.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.blurry.Blurry;
import models.NewsModel;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<NewsModel> newsList;

    public NewsAdapter() {
        this.newsList = new ArrayList<>();
    }

    public void getNewsList(List<NewsModel> cList) {
        this.newsList = cList;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        NewsModel currentItem = newsList.get(position);
        try {
            Bitmap bitmap = Picasso.get().load("http://143.110.151.110:5100/"+currentItem.getImage()).get();
            Blurry.with(holder.itemView.getContext()).from(bitmap).into(holder.image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        holder.title.setText(currentItem.getTitle());
        holder.subTitle.setText(currentItem.getSubTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("clicked "+currentItem.getTitle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, subTitle;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.news_title);
            subTitle = itemView.findViewById(R.id.news_subtitle);
            image = itemView.findViewById(R.id.news_image);
        }
    }
}
