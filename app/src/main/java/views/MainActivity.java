package views;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.app.helloar.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import fragments.DiagnosisFragment;
import fragments.HomeFragment;
import fragments.MuseumFragment;
import fragments.NewsFragment;
import fragments.WikiFragment;

public class MainActivity extends AppCompatActivity {

    public static BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);
        getViews();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentsContainer,new HomeFragment()).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.homeFragment:
                        fragment = new HomeFragment();
                        break;
                    case R.id.newsFragment:
                        fragment = new NewsFragment();
                        break;
                    case R.id.wikiFragment:
                        fragment = new WikiFragment();
                        break;
                    case R.id.diagnosisFragment:
                        fragment = new DiagnosisFragment();
                        break;
                    case R.id.museumFragment:
                        fragment = new MuseumFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentsContainer,fragment).commit();
                return true;
            }
        });

    }

    private void getViews() {
        bottomNavigationView = findViewById(R.id.bottomNavView);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }
}