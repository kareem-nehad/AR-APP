package views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Scroller;
import android.widget.TextView;

import com.app.helloar.R;
import com.squareup.picasso.Picasso;

import models.NewsModel;
import viewmodels.NewsViewModel;

public class SelectedNews extends AppCompatActivity {

    String from;
    String id;
    TextView title, subTitle, body;
    ImageView back, image;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_news);
        getViews();

        // Back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Getting Data from the Selected News from Home
        Intent intent = getIntent();
        from = intent.getStringExtra("from");
        System.out.println("Opened from: "+from);
        if (from.equals("home")) {
            progressBar.setVisibility(View.GONE);
            progressBar.invalidate();
            // Binding the data from Selected News to the corresponding field it belongs to in this page.
            Picasso.get().load(intent.getStringExtra("image")).into(image);
            title.setText(intent.getStringExtra("title"));
            subTitle.setText(intent.getStringExtra("subTitle"));
            body.setText(intent.getStringExtra("body"));
        } else {
            id = intent.getStringExtra("id");
            NewsViewModel newsViewModel = new NewsViewModel();
            newsViewModel.getSelectedNews(id).observe(this, new Observer<NewsModel>() {
                @Override
                public void onChanged(NewsModel newsModel) {
                    progressBar.setVisibility(View.GONE);
                    Picasso.get().load("http://143.110.151.110:5100/"+newsModel.getImage()).into(image);
                    title.setText(newsModel.getTitle());
                    subTitle.setText(newsModel.getSubTitle());
                    body.setText(newsModel.getBody());
                }
            });
        }

    }

    private void getViews() {
        title = findViewById(R.id.selectedNews_Title);
        subTitle = findViewById(R.id.selectedNews_SubTitle);
        body = findViewById(R.id.selectedNews_Body);
        back = findViewById(R.id.selectedNews_back);
        image = findViewById(R.id.selectedNews_image);
        progressBar = findViewById(R.id.selectedNews_Progress);
    }
}