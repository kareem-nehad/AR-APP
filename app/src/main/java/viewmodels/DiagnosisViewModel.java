package viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import repositories.DiagnosisRepo;

public class DiagnosisViewModel {
    private DiagnosisRepo diagnosisRepo;
    private MutableLiveData<Double> RetinopathyData;
    private MutableLiveData<Double> EstimatorData;

    public DiagnosisViewModel () {
        this.diagnosisRepo = new DiagnosisRepo();
    }

    public LiveData<Double> getRetinopathy(float age, float systolic, float diastolic, float cholestrol) {
        if (RetinopathyData == null) {
            RetinopathyData = diagnosisRepo.Retinopathy(age,systolic,diastolic,cholestrol);
        }

        return RetinopathyData;
    }

    public void restartRetinopathy () {
        RetinopathyData = null;
    }

    public LiveData<Double> getEstimator(float age, float systolic, float diastolic, float cholestrol, float poverty, float race, float redBloodCells, float sedimentation, float albumin, float iron, float magnesium, float protein, float gender, float TIBC, float TS, float whiteBloodCells, float BMI, float pulse) {
        if (EstimatorData == null) {
            EstimatorData = diagnosisRepo.DeathEstimator( age,  systolic,  diastolic,  cholestrol,  poverty,  race,  redBloodCells,  sedimentation,  albumin,  iron,  magnesium,  protein,  gender,  TIBC,  TS,  whiteBloodCells, BMI, pulse);
        }

        return EstimatorData;
    }

    public void restartEstimator () {
        EstimatorData = null;
    }
}
