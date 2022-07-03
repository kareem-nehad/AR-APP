package fragments;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.app.helloar.R;
import com.google.ar.core.AugmentedImage;
import com.google.ar.core.AugmentedImageDatabase;
import com.google.ar.core.Config;
import com.google.ar.core.Frame;
import com.google.ar.core.Session;
import com.google.ar.core.TrackingState;
import com.google.ar.core.exceptions.CameraNotAvailableException;
import com.google.ar.core.exceptions.UnavailableApkTooOldException;
import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException;
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException;
import com.google.ar.core.exceptions.UnavailableSdkTooOldException;
import com.google.ar.sceneform.ArSceneView;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.Scene;

import java.util.Collection;

import kotlinx.coroutines.GlobalScope;
import models.WikiModel;
import viewmodels.WikiViewModel;
import views.SelectedWiki;

public class MuseumFragment extends Fragment {

    public MuseumFragment() {
        // Required empty public constructor
    }

    ArSceneView arView;
    Session session;
    boolean shouldConfigureSession = false;
    boolean startActivity = true;
    WikiViewModel wikiViewModel;
    String heartId = "629b8f29b12d24818d2781c7";
    String brainId = "629a2769b12d24818d2781c6";
    String liverId = "625dd60eb12d24818d2781bc";
    String eyeId = "62bb45feb12d24818d2781c9";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_museum, container, false);
        getViews(v);

        if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            getActivity().requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
        } else {
            setupSession();
        }

        initSceneView();

        wikiViewModel = new WikiViewModel();

        return v;
    }

    private void setupSession() {
        if (session == null) {
            try {
                session = new Session(getContext());
            } catch (UnavailableDeviceNotCompatibleException e) {
                e.printStackTrace();
            } catch (UnavailableSdkTooOldException e) {
                e.printStackTrace();
            } catch (UnavailableArcoreNotInstalledException e) {
                e.printStackTrace();
            } catch (UnavailableApkTooOldException e) {
                e.printStackTrace();
            }

            shouldConfigureSession = true;
        }

        if (shouldConfigureSession) {
            configSession();
            shouldConfigureSession = false;
            arView.setupSession(session);
        }

        try {
            session.resume();
            arView.resume();
        } catch (CameraNotAvailableException e) {
            e.printStackTrace();
            session = null;
            return;
        }
    }

    private void configSession() {
        Config config = session.getConfig();
        if (!buildDatabase(config)) {
            config.setUpdateMode(Config.UpdateMode.LATEST_CAMERA_IMAGE);
        }
//        config.setFocusMode(Config.FocusMode.AUTO);
//        config.setDepthMode(Config.DepthMode.AUTOMATIC);
        session.configure(config);
    }

    private boolean buildDatabase(Config config) {

        AugmentedImageDatabase augmentedImageDatabase;
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.heart_img);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.brain_img);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.liver_img);
        Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(), R.drawable.eye_img);
        if (bitmap1 == null) {
            return false;
        }
        augmentedImageDatabase = new AugmentedImageDatabase(session);
        augmentedImageDatabase.addImage("heart", bitmap1);
        augmentedImageDatabase.addImage("brain", bitmap2);
        augmentedImageDatabase.addImage("liver", bitmap3);
        augmentedImageDatabase.addImage("eye", bitmap4);
        config.setAugmentedImageDatabase(augmentedImageDatabase);


//        new LoadImages(session,config,augmentedImageDatabase).execute();

        return false;
    }

    private void initSceneView() {
        arView.getScene().addOnUpdateListener(new Scene.OnUpdateListener() {
            @Override
            public void onUpdate(FrameTime frameTime) {
                Frame frame = arView.getArFrame();
                Collection<AugmentedImage> updateAugmentedImg = frame.getUpdatedTrackables(AugmentedImage.class);
                for (AugmentedImage image : updateAugmentedImg) {
                    if (image.getTrackingState() == TrackingState.TRACKING) {
                        if (image.getName().equals("heart")) {
                            System.out.println("I can see a Heart");
                            Intent intent;
                            intent = new Intent(getActivity(), SelectedWiki.class);
                            intent.putExtra("from", "museum");
                            intent.putExtra("id", heartId);
                            getParentFragmentManager().beginTransaction().remove(MuseumFragment.this).commit();
                            getActivity().startActivity(intent);
                        } else if (image.getName().equals("brain")) {
                            System.out.println("I can see a Brain");
                            Intent intent;
                            intent = new Intent(getActivity(), SelectedWiki.class);
                            intent.putExtra("from", "museum");
                            intent.putExtra("id", brainId);
                            getParentFragmentManager().beginTransaction().remove(MuseumFragment.this).commit();
                            getActivity().startActivity(intent);
                        } else if (image.getName().equals("liver")) {
                            System.out.println("I can see a Liver");
                            Intent intent;
                            intent = new Intent(getActivity(), SelectedWiki.class);
                            intent.putExtra("from", "museum");
                            intent.putExtra("id", liverId);
                            getParentFragmentManager().beginTransaction().remove(MuseumFragment.this).commit();
                            getActivity().startActivity(intent);
                        } else if (image.getName().equals("eye")) {
                            System.out.println("I can see an Eye");
                            Intent intent;
                            intent = new Intent(getActivity(), SelectedWiki.class);
                            intent.putExtra("from", "museum");
                            intent.putExtra("id", eyeId);
                            getParentFragmentManager().beginTransaction().remove(MuseumFragment.this).commit();
                            getActivity().startActivity(intent);
                        }
                    }
                }
            }
        });
    }

    private void getViews(View v) {
        arView = v.findViewById(R.id.arView);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            getActivity().requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
        } else {
            setupSession();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if ((session != null)) {

            arView.pause();
            session.pause();
        }
    }

//    private class LoadImages extends AsyncTask<Void,Void,Void>{
//        AugmentedImageDatabase augmentedImageDatabase;
//        Session session;
//        Config config;
//
//        public LoadImages(Session session, Config config, AugmentedImageDatabase augmentedImageDatabase) {
//            this.session = session;
//            this.config = config;
//            this.augmentedImageDatabase = augmentedImageDatabase;
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
////            augmentedImageDatabase = new AugmentedImageDatabase(session);
//            Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.heart_img);
//            Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.brain_img);
//            Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.liver_img);
//            Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(), R.drawable.eye_img);
//            augmentedImageDatabase.addImage("heart", bitmap1);
//            augmentedImageDatabase.addImage("brain", bitmap2);
//            augmentedImageDatabase.addImage("liver", bitmap3);
//            augmentedImageDatabase.addImage("eye", bitmap4);
//            config.setAugmentedImageDatabase(augmentedImageDatabase);
//            return null;
//        }
//    }

}