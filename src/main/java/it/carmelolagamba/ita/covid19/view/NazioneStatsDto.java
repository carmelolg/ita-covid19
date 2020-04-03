package it.carmelolagamba.ita.covid19.view;

public class NazioneStatsDto {

    private Double currentRateOfGrowth;
    private Double currentNewPositiveRateOfGrowth;
    private Double currentPositivePercentageBasedOnTests;
    private Double currentRecoveredPercentage;
    private Double currentDeadPercentage;
    private Double currentIntensiveCarePercentage;
    private Double currentHospitalizedPercentage;

    public Double getCurrentRateOfGrowth() {
        return currentRateOfGrowth;
    }

    public void setCurrentRateOfGrowth(Double currentRateOfGrowth) {
        this.currentRateOfGrowth = currentRateOfGrowth;
    }

    public Double getCurrentNewPositiveRateOfGrowth() {
        return currentNewPositiveRateOfGrowth;
    }

    public void setCurrentNewPositiveRateOfGrowth(Double currentNewPositiveRateOfGrowth) {
        this.currentNewPositiveRateOfGrowth = currentNewPositiveRateOfGrowth;
    }

    public Double getCurrentPositivePercentageBasedOnTests() {
        return currentPositivePercentageBasedOnTests;
    }

    public void setCurrentPositivePercentageBasedOnTests(Double currentPositivePercentageBasedOnTests) {
        this.currentPositivePercentageBasedOnTests = currentPositivePercentageBasedOnTests;
    }

    public Double getCurrentRecoveredPercentage() {
        return currentRecoveredPercentage;
    }

    public void setCurrentRecoveredPercentage(Double currentRecoveredPercentage) {
        this.currentRecoveredPercentage = currentRecoveredPercentage;
    }

    public Double getCurrentDeadPercentage() {
        return currentDeadPercentage;
    }

    public void setCurrentDeadPercentage(Double currentDeadPercentage) {
        this.currentDeadPercentage = currentDeadPercentage;
    }

    public Double getCurrentIntensiveCarePercentage() {
        return currentIntensiveCarePercentage;
    }

    public void setCurrentIntensiveCarePercentage(Double currentIntensiveCarePercentage) {
        this.currentIntensiveCarePercentage = currentIntensiveCarePercentage;
    }

    public Double getCurrentHospitalizedPercentage() {
        return currentHospitalizedPercentage;
    }

    public void setCurrentHospitalizedPercentage(Double currentHospitalizedPercentage) {
        this.currentHospitalizedPercentage = currentHospitalizedPercentage;
    }
}
