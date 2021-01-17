package it.carmelolagamba.ita.covid19.view;

public class GenericStatsDto {

	private int currentTotalCases;
	private int currentPositives;
	private int currentDead;
	private int currentRecovered;
	private int currentTests;
	private int currentHospedalized;
	private int currentIntesiveCare;
	private int currentHomeIsolation;

	private Double currentRateOfGrowth;
	private Double currentNewPositiveRateOfGrowth;
	private Double currentPositivePercentageBasedOnTests;
	private Double currentRecoveredPercentage;
	private Double currentDeadPercentage;
	private Double currentIntensiveCarePercentage;
	private Double currentHospitalizedPercentage;

	private int newPositives;
	private int variationNewPositives;
	private int testsToday;

	public int getVariationNewPositives() {
		return variationNewPositives;
	}

	public void setVariationNewPositives(int variationNewPositives) {
		this.variationNewPositives = variationNewPositives;
	}

	public int getNewPositives() {
		return newPositives;
	}

	public void setNewPositives(int newPositives) {
		this.newPositives = newPositives;
	}

	public int getCurrentTotalCases() {
		return currentTotalCases;
	}

	public void setCurrentTotalCases(int currentTotalCases) {
		this.currentTotalCases = currentTotalCases;
	}

	public int getCurrentPositives() {
		return currentPositives;
	}

	public void setCurrentPositives(int currentPositives) {
		this.currentPositives = currentPositives;
	}

	public int getCurrentDead() {
		return currentDead;
	}

	public void setCurrentDead(int currentDead) {
		this.currentDead = currentDead;
	}

	public int getCurrentRecovered() {
		return currentRecovered;
	}

	public void setCurrentRecovered(int currentRecovered) {
		this.currentRecovered = currentRecovered;
	}

	public int getCurrentTests() {
		return currentTests;
	}

	public void setCurrentTests(int currentTests) {
		this.currentTests = currentTests;
	}

	public int getCurrentHospedalized() {
		return currentHospedalized;
	}

	public void setCurrentHospedalized(int currentHospedalized) {
		this.currentHospedalized = currentHospedalized;
	}

	public int getCurrentIntesiveCare() {
		return currentIntesiveCare;
	}

	public void setCurrentIntesiveCare(int currentIntesiveCare) {
		this.currentIntesiveCare = currentIntesiveCare;
	}

	public int getCurrentHomeIsolation() {
		return currentHomeIsolation;
	}

	public void setCurrentHomeIsolation(int currentHomeIsolation) {
		this.currentHomeIsolation = currentHomeIsolation;
	}

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

	public int getTestsToday() {
		return testsToday;
	}

	public void setTestsToday(int testsToday) {
		this.testsToday = testsToday;
	}

}
