package it.carmelolagamba.ita.covid19.view;

public class NazioneStatsDto {

    private Double currentRateOfGrowth;
    private Double currentNewPositiveRateOfGrowth;
    private Double currentPositivePercentageBasedOnTests;

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
}
