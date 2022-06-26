package fragments;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import com.app.helloar.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.FileNotFoundException;
import java.io.IOException;

import database.Converter;
import models.Note;
import viewmodels.NotesViewModel;

public class AddFragment extends BottomSheetDialogFragment {

    public AddFragment() {
        // Required empty public constructor
    }

    Button choose, save;
    ImageView image;
    EditText text, title;
    private NotesViewModel notesViewModel;
    Bitmap bitmap = null;
    byte[] byteArray;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add, container, false);
        getViews(v);

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        if (getArguments() != null) {
            title.setText(getArguments().getString("title"));
            text.setText(getArguments().getString("text"));
            image.setImageBitmap(Converter.getBitmapFromString(getArguments().getByteArray("image")));
        }

        ActivityResultLauncher<Intent> getImageIntent = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Uri uri = data.getData();
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                                byteArray = Converter.getStringFromBitmap(bitmap);
                                image.setImageBitmap(bitmap);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    getImageIntent.launch(intent);
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (title.getText().toString().isEmpty()) {
                    new AlertDialog.Builder(getContext())
                            .setTitle("Warning!")
                            .setMessage("You must enter a title for the note")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .create().show();
                } else if (text.getText().toString().isEmpty()) {
                    new AlertDialog.Builder(getContext())
                            .setTitle("Warning!")
                            .setMessage("You must enter a body for the note")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .create().show();
                } else {
                    if (getArguments() != null) {
                        Note note = new Note(
                                title.getText().toString().trim(),
                                text.getText().toString().trim(),
                                byteArray == null ? getArguments().getByteArray("image") : byteArray
                        );
                        note.setId(getArguments().getInt("id"));
                        notesViewModel.update(note);
                    } else {
                        notesViewModel.insert(new Note(
                                title.getText().toString().trim(),
                                text.getText().toString().trim(),
                                byteArray == null ? getArguments().getByteArray("image") : byteArray
                        ));
                    }

                    dismiss();
                }
            }
        });

        return v;
    }

    private void getViews(View v) {
        image = v.findViewById(R.id.noteImage);
        title = v.findViewById(R.id.noteTitle);
        text = v.findViewById(R.id.noteText);
        choose = v.findViewById(R.id.addPicture);
        save = v.findViewById(R.id.saveNote);
    }
}