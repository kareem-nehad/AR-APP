package views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import com.app.helloar.R;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import fragments.MuseumFragment;
import models.WikiModel;
import viewmodels.WikiViewModel;

public class SelectedWiki extends AppCompatActivity {
    ImageView image;
    Button view3D;
    TextView category, body, title;
    ProgressBar progressBar;
    ConstraintLayout layout;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_wiki);
        getViews();
        layout.setVisibility(View.INVISIBLE);
        body.setScroller(new Scroller(this));

        Intent intent = getIntent();

        System.out.println(intent.getStringExtra("from"));
        WikiViewModel wikiViewModel = new WikiViewModel();

        if (intent.getStringExtra("from").equals("adapter")) {
            wikiViewModel.getSelectedWiki(intent.getStringExtra("id")).observe(this, new Observer<WikiModel>() {
                @Override
                public void onChanged(WikiModel wikiModel) {
                    progressBar.setVisibility(View.GONE);
                    layout.setVisibility(View.VISIBLE);
                    Picasso.get().load("http://143.110.151.110:5100/"+wikiModel.getImage()).into(image);
                    category.setText(wikiModel.getCategory());
                    title.setText(wikiModel.getTitle());
                    body.setText(wikiModel.getBody());

                    textToSpeech = new TextToSpeech(SelectedWiki.this, new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int i) {
                            textToSpeech.setLanguage(Locale.UK);
                            textToSpeech.speak(title.getText().toString(), TextToSpeech.QUEUE_FLUSH,null,"1");
                        }
                    });


                }
            });
        } else if (intent.getStringExtra("from").equals("museum")) {
            wikiViewModel.getSelectedWiki(intent.getStringExtra("id")).observe(this, new Observer<WikiModel>() {
                @Override
                public void onChanged(WikiModel wikiModel) {
                    progressBar.setVisibility(View.GONE);
                    layout.setVisibility(View.VISIBLE);
                    Picasso.get().load("http://143.110.151.110:5100/"+wikiModel.getImage()).into(image);
                    category.setText(wikiModel.getCategory());
                    title.setText(wikiModel.getTitle());
                    body.setText(wikiModel.getBody());

                    textToSpeech = new TextToSpeech(SelectedWiki.this, new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int i) {
                            textToSpeech.setLanguage(Locale.UK);
                            textToSpeech.speak(title.getText().toString(), TextToSpeech.QUEUE_FLUSH,null,"1");
                        }
                    });


                }

            });

        }

        view3D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (title.getText().toString().equals("Heart")) {
                    Intent intent = new Intent(SelectedWiki.this,OrganVisActivity.class);
                    intent.putExtra("organ", "heart");
                    startActivity(intent);
                } else if (title.getText().toString().equals("Eye")) {
                    Intent intent = new Intent(SelectedWiki.this,OrganVisActivity.class);
                    intent.putExtra("organ", "eye");
                    startActivity(intent);
                } else if (title.getText().toString().equals("Brain")) {
                    Intent intent = new Intent(SelectedWiki.this, OrganVisActivity.class);
                    intent.putExtra("organ", "brain");
                    startActivity(intent);
                }
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getIntent().getStringExtra("from").equals("museum")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            finish();
        }

    }
}