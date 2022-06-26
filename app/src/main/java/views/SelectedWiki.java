package views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import com.app.helloar.R;
import com.squareup.picasso.Picasso;

import models.WikiModel;
import viewmodels.WikiViewModel;

public class SelectedWiki extends AppCompatActivity {
    ImageView image;
    Button view3D;
    TextView category, body, title;
    ProgressBar progressBar;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_wiki);
        getViews();
        layout.setVisibility(View.INVISIBLE);
        body.setScroller(new Scroller(this));

        Intent intent = getIntent();

        WikiViewModel wikiViewModel = new WikiViewModel();
        wikiViewModel.getSelectedWiki(intent.getStringExtra("id")).observe(this, new Observer<WikiModel>() {
            @Override
            public void onChanged(WikiModel wikiModel) {
                progressBar.setVisibility(View.GONE);
                layout.setVisibility(View.VISIBLE);
                Picasso.get().load("http://143.110.151.110:5100/"+wikiModel.getImage()).into(image);
                category.setText(wikiModel.getCategory());
                title.setText(wikiModel.getTitle());
                body.setText(wikiModel.getBody());
            }
        });
    }

    private void getViews() {
        image = findViewById(R.id.selectedWiki_image);
        view3D = findViewById(R.id.selectedWiki_view);
        category = findViewById(R.id.SelectedWiki_category);
        title = findViewById(R.id.Selectedwiki_title);
        body = findViewById(R.id.selectedWiki_body);
        progressBar = findViewById(R.id.progressBar_wiki);
        layout = findViewById(R.id.selectedWikiLayout);
    }
}