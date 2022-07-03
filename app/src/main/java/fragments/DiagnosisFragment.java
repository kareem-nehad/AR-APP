package fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.app.helloar.R;

import java.util.Calendar;
import java.util.Locale;

public class DiagnosisFragment extends Fragment {

    TextView date;
    CardView retinotherapy, deathEstimator;

    public DiagnosisFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diagnosis, container, false);
        getViews(view);

        // Date
        Calendar calendar = Calendar.getInstance();
        date.setText(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " " + Calendar.getInstance().get(Calendar.YEAR));

        // Retinotherapy
        retinotherapy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragmentsContainer, new RetinopathyFragment()).commit();
            }
        });

        // Death Estimator
        deathEstimator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragmentsContainer, new DeathEstimatorFragment()).commit();
            }
        });

        return view;
    }

    private void getViews(View view) {
        date = view.findViewById(R.id.diagnosis_date);
        retinotherapy = view.findViewById(R.id.retinotherapyCard);
        deathEstimator = view.findViewById(R.id.estimatorCard);
    }
}