package com.app.helloar;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
                    case R.id.diagnosisFragment:
                        fragment = new DiagnosisFragment();
                        break;
                    case R.id.organVisFragment:
                        fragment = new OrganVisFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentsContainer,fragment).commit();
                return true;
            }
        });


        //Test commit with yehya

        //You can load .sfb files from URLs by using setSource(Context, Uri) on the ModelRenderable.Builder class.

//        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);
//        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
//            @Override
//            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
//                Anchor anchor = hitResult.createAnchor();
//                ModelRenderable.builder()
//                        .setSource(MainActivity.this, Uri.parse("eyeball.sfb"))
//                        .build()
//                        .thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable))
//                        .exceptionally(throwable -> {
//                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                            builder.setMessage(throwable.getMessage()).show();
//                            return null;
//                        });
//            }
//        });

    }

    private void getViews() {
        bottomNavigationView = findViewById(R.id.bottomNavView);
    }

//    private void addModelToScene(Anchor anchor, ModelRenderable modelRenderable) {
//        AnchorNode node = new AnchorNode(anchor);
//        TransformableNode transformableNode = new TransformableNode(arFragment.getTransformationSystem());
//        transformableNode.setParent(node);
//        transformableNode.setRenderable(modelRenderable);
//
//        arFragment.getArSceneView().getScene().addChild(node);
//        transformableNode.select();
//    }
}