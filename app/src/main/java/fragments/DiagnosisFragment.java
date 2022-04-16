package fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.helloar.R;

import java.util.Calendar;
import java.util.Locale;

public class DiagnosisFragment extends Fragment {

    TextView date;

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

        return view;
    }

    private void getViews(View view) {
        date = view.findViewById(R.id.diagnosis_date);
    }
}