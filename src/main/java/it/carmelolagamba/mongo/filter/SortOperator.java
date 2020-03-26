package it.carmelolagamba.mongo.filter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum SortOperator {
	ASC(1), DESC(-1);

	@JsonProperty("value")
	Integer value;

	SortOperator(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return getValue().toString();
	}
}
