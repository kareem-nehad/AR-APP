package views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;

import com.app.helloar.R;
import com.squareup.picasso.Picasso;

public class SelectedNews extends AppCompatActivity {

    TextView title, subTitle, body;
    ImageView back, image;

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

        // Getting Data from the Selected News
        Intent intent = getIntent();

        // Binding the data from Selected News to the corresponding field it belongs to in this page.
        Picasso.get().load(intent.getStringExtra("image")).into(image);
        title.setText(intent.getStringExtra("title"));
        subTitle.setText(intent.getStringExtra("subTitle"));
        body.setText(intent.getStringExtra("body"));

    }

    private void getViews() {
        title = findViewById(R.id.selectedNews_Title);
        subTitle = findViewById(R.id.selectedNews_SubTitle);
        body = findViewById(R.id.selectedNews_Body);
        back = findViewById(R.id.selectedNews_back);
        image = findViewById(R.id.selectedNews_image);
    }
}