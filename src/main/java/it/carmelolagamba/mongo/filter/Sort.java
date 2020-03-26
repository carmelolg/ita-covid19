package it.carmelolagamba.mongo.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Sort {

	SortOperator operator;
	String key;

	public Sort() {
	}

	public Sort(String key, SortOperator operator) {
		this.key = key;

		if (operator == null)
			this.operator = SortOperator.ASC;
		else
			this.operator = operator;

	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public SortOperator getOperator() {
		return operator;
	}

	public void setOperator(SortOperator operator) {
		if (operator == null)
			this.operator = SortOperator.ASC;
		else
			this.operator = operator;
	}

	@JsonIgnore
	public boolean isAscFilter() {
		return this.operator.equals(SortOperator.ASC);
	}

	@JsonIgnore
	public boolean isDescFilter() {
		return this.operator.equals(SortOperator.DESC);
	}

	@Override
	public String toString() {
		return "(" + key + " , " + operator + ")";
	}

}