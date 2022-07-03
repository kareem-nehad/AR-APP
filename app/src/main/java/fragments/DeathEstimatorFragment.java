package fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.helloar.R;
import com.google.android.material.textfield.TextInputEditText;

import viewmodels.DiagnosisViewModel;

public class DeathEstimatorFragment extends Fragment {

    public DeathEstimatorFragment() {
        // Required empty public constructor
    }

    TextInputEditText age,
    systolic,
    diastolic,
    cholestrol,
    poverty,
    race,
    redBloodCells,
    sedimentation,
    albumin,
    iron,
    magnesium,
    protein,
    gender,
    TIBC,
    TS,
    whiteBloodCells,
    BMI,
    pulse;
    Button check;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_death_estimator, container, false);
        getViews(view);

        DiagnosisViewModel diagnosisViewModel = new DiagnosisViewModel();
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setTitle("10-Year Death Estimator");
                progressDialog.setMessage("Fetching Results");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();

                diagnosisViewModel.getEstimator(Float.parseFloat(age.getText().toString()),
                        Float.parseFloat(systolic.getText().toString()),
                        Float.parseFloat(diastolic.getText().toString()),
                        Float.parseFloat(cholestrol.getText().toString()),
                        Float.parseFloat(poverty.getText().toString()),
                        Float.parseFloat(race.getText().toString()),
                        Float.parseFloat(redBloodCells.getText().toString()),
                        Float.parseFloat(sedimentation.getText().toString()),
                        Float.parseFloat(albumin.getText().toString()),
                        Float.parseFloat(iron.getText().toString()),
                        Float.parseFloat(magnesium.getText().toString()),
                        Float.parseFloat(protein.getText().toString()),
                        Float.parseFloat(gender.getText().toString()),
                        Float.parseFloat(TIBC.getText().toString()),
                        Float.parseFloat(TS.getText().toString()),
                        Float.parseFloat(whiteBloodCells.getText().toString()),
                        Float.parseFloat(BMI.getText().toString()),
                        Float.parseFloat(pulse.getText().toString())).observe(getViewLifecycleOwner(), new Observer<Double>() {
                    @Override
                    public void onChanged(Double aDouble) {
                        progressDialog.dismiss();
                        int result = (int) (aDouble * 100);
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                        alertDialog.setTitle("Results");
                        alertDialog.setMessage("Death Estimation: " + result+"%");
                        alertDialog.setPositiveButton("OK",null);
                        alertDialog.show();
                        diagnosisViewModel.restartEstimator();
                    }
                });
            }
        });

        return view;
    }

    private void getViews(View view) {
        age = view.findViewById(R.id.ageInput_estimator);
        systolic = view.findViewById(R.id.systolicInput_estimator);
        diastolic = view.findViewById(R.id.diastolicInput_estimator);
        cholestrol = view.findViewById(R.id.cholestrolInput_estimator);
        poverty = view.findViewById(R.id.povertyInput_estimator);
        race = view.findViewById(R.id.raceInput_estimator);
        redBloodCells = view.findViewById(R.id.rbcInput_estimator);
        sedimentation = view.findViewById(R.id.sedimentationInput_estimator);
        albumin = view.findViewById(R.id.albuminInput_estimator);
        iron = view.findViewById(R.id.ironInput_estimator);
        magnesium = view.findViewById(R.id.magnesiumInput_estimator);
        protein = view.findViewById(R.id.proteinInput_estimator);
        gender = view.findViewById(R.id.genderInput_estimator);
        TIBC = view.findViewById(R.id.tibcInput_estimator);
        TS = view.findViewById(R.id.tsInput_estimator);
        whiteBloodCells = view.findViewById(R.id.whiteBloodCellsInput_estimator);
        BMI = view.findViewById(R.id.BMIInput_estimator);
        pulse = view.findViewById(R.id.pulsePressureInput_estimator);
        check = view.findViewById(R.id.check_estimator);
    }
}