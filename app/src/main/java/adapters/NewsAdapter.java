package adapters;

import android.annotation.SuppressLint;
import android.content.Context;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import at.favre.lib.dali.Dali;
import models.NewsModel;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<NewsModel> newsList;
    private Context context;

    public NewsAdapter(Context context) {
        this.newsList = new ArrayList<>();
        this.context = context;
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        NewsModel currentItem = newsList.get(position);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = null;
                try {
                    bitmap = Picasso.get().load("http://143.110.151.110:5100/"+currentItem.getImage()).get();
                    Dali.create(context).load(bitmap).blurRadius(25).concurrent().into(holder.image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        holder.title.setText(currentItem.getTitle());

        // Date handling
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentItem.getDate());
        holder.date.setText(calendar.get(Calendar.DAY_OF_MONTH) + " "
                + calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " "
                + calendar.get(Calendar.YEAR));

        holder.subTitle.setText(currentItem.getSubTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("clicked " + currentItem.getTitle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, subTitle, date;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.news_title);
            subTitle = itemView.findViewById(R.id.news_subtitle);
            image = itemView.findViewById(R.id.news_image);
            date = itemView.findViewById(R.id.news_item_date);
        }
    }
}
