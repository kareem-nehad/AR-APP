package views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.app.helloar.R;
import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class OrganVisActivity extends AppCompatActivity {

    ArFragment arFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organ_vis);

        Intent intent = getIntent();

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.organ_vis_fragment);
        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
                Anchor anchor = hitResult.createAnchor();
                if (intent.getStringExtra("organ").equals("heart")) {
                    ModelRenderable.builder()
                            .setSource(OrganVisActivity.this, Uri.parse("heart.sfb"))
                            .build()
                            .thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable))
                            .exceptionally(throwable -> {
                                AlertDialog.Builder builder = new AlertDialog.Builder(OrganVisActivity.this);
                                builder.setMessage(throwable.getMessage()).show();
                                return null;
                            });
                } else if (intent.getStringExtra("organ").equals("eye")) {
                    ModelRenderable.builder()
                            .setSource(OrganVisActivity.this, Uri.parse("eyeball.sfb"))
                            .build()
                            .thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable))
                            .exceptionally(throwable -> {
                                AlertDialog.Builder builder = new AlertDialog.Builder(OrganVisActivity.this);
                                builder.setMessage(throwable.getMessage()).show();
                                return null;
                            });
                }  else if (intent.getStringExtra("organ").equals("brain")) {
                    ModelRenderable.builder()
                            .setSource(OrganVisActivity.this, Uri.parse("brain.sfb"))
                            .build()
                            .thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable))
                            .exceptionally(throwable -> {
                                AlertDialog.Builder builder = new AlertDialog.Builder(OrganVisActivity.this);
                                builder.setMessage(throwable.getMessage()).show();
                                return null;
                            });

                }

            }
        });
    }

    private void addModelToScene(Anchor anchor, ModelRenderable modelRenderable) {
        AnchorNode node = new AnchorNode(anchor);
        TransformableNode transformableNode = new TransformableNode(arFragment.getTransformationSystem());
        transformableNode.setParent(node);
        transformableNode.setRenderable(modelRenderable);

        arFragment.getArSceneView().getScene().addChild(node);
        transformableNode.select();
    }
}