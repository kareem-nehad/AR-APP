package fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.helloar.R;
import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import views.OrganNoteActivity;

public class OrganVisFragment extends Fragment {

    ArFragment arFragment;
    Button takeNote;

    public OrganVisFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_organ_vis, container, false);
        getViews(v);

        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
                Anchor anchor = hitResult.createAnchor();

                ModelRenderable.builder()
                        .setSource(getContext(), Uri.parse("eyeball.sfb"))
                        .build()
                        .thenAccept(modelRenderable -> addModelToScene(anchor,modelRenderable))
                        .exceptionally(throwable -> {
                            AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
                            builder.setMessage(throwable.getMessage())
                                    .show();
                            return null;
                        });
            }
        });

        takeNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), OrganNoteActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

    private void addModelToScene(Anchor anchor, ModelRenderable modelRenderable) {
        AnchorNode anchorNode = new AnchorNode(anchor);
        TransformableNode transformableNode = new TransformableNode(arFragment.getTransformationSystem());
        transformableNode.setParent(anchorNode);
        transformableNode.setRenderable(modelRenderable);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
        transformableNode.select();
    }

    private void getViews(View v) {
        arFragment = (ArFragment) getChildFragmentManager().findFragmentById(R.id.organ_vis_fragment);
    }
}