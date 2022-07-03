package fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.app.helloar.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import viewmodels.DiagnosisViewModel;

public class RetinopathyFragment extends Fragment {

    public RetinopathyFragment() {
        // Required empty public constructor
    }

    TextInputEditText age, systolic, diastolic, cholestrol;
    Button check;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_retinopathy, container, false);
        getViews(v);
//        System.out.println(age.getEditText().getText().toString());

        DiagnosisViewModel diagnosisViewModel = new DiagnosisViewModel();
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(Float.parseFloat(age.getText().toString()));
                ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setTitle("Retinopathy");
                progressDialog.setMessage("Fetching Results");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();

                diagnosisViewModel.getRetinopathy(Float.parseFloat(age.getText().toString()),
                        Float.parseFloat(systolic.getText().toString()),
                        Float.parseFloat(diastolic.getText().toString()),
                        Float.parseFloat(cholestrol.getText().toString())).observe(getViewLifecycleOwner(), new Observer<Double>() {

                    @Override
                    public void onChanged(Double aDouble) {
                        progressDialog.dismiss();
                        int result = (int) (aDouble * 100);
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                        alertDialog.setTitle("Results");
                        alertDialog.setMessage("Retinopathy level: " + result+"%");
                        alertDialog.setPositiveButton("OK",null);
                        alertDialog.show();
                        diagnosisViewModel.restartRetinopathy();
                    }
                });
            }
        });

        return v;
    }

    private void getViews(View v) {
        age = v.findViewById(R.id.ageInput_retinopathy);
        systolic = v.findViewById(R.id.systolicInput_retinopathy);
        diastolic = v.findViewById(R.id.diastolicInput_retinopathy);
        cholestrol = v.findViewById(R.id.cholestrolInput_retinopathy);
        check = v.findViewById(R.id.check_retinopathy);

    }
}