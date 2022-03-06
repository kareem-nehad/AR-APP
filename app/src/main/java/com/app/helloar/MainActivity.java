package com.app.helloar;

import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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