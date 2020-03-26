package it.carmelolagamba.mongo.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Filter {

	FilterOperator operator;
	String key;
	Object value;

	public Filter() {
	}

	public Filter(String key, FilterOperator operator, Object value) {

		if (operator == null)
			this.operator = FilterOperator.EQUALS;
		else
			this.operator = operator;

		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public FilterOperator getOperator() {
		return operator;
	}

	public void setOperator(FilterOperator operator) {
		if (operator == null)
			this.operator = FilterOperator.EQUALS;
		else
			this.operator = operator;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@JsonIgnore
	public boolean isEqualFilter() {
		return this.operator.equals(FilterOperator.EQUALS);
	}

	@JsonIgnore
	public boolean isNotEqualFilter() {
		return this.operator.equals(FilterOperator.NOT_EQUALS);
	}

	@JsonIgnore
	public boolean isExistsFilter() {
		return this.operator.equals(FilterOperator.EXISTS);
	}

	@JsonIgnore
	public boolean isGreaterThanFilter() {
		return this.operator.equals(FilterOperator.GREATER_THAN);
	}

	@JsonIgnore
	public boolean isLessThanFilter() {
		return this.operator.equals(FilterOperator.LESS_THEN);
	}

	@JsonIgnore
	public boolean isInFilter() {
		return this.operator.equals(FilterOperator.IN);
	}

	@JsonIgnore
	public boolean isNotInFilter() {
		return this.operator.equals(FilterOperator.NOT_IN);
	}

	@Override
	public String toString() {
		return "(" + key + " , " + operator.toString() + " , " + value.toString() + ")";
	}
}
