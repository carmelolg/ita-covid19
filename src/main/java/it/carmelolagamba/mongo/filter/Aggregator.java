package it.carmelolagamba.mongo.filter;

public enum Aggregator {
	MATCH("$match"), 
	SORT("$sort"), 
	LIMIT("$limit"), 
	SKIP("$skip"), 
	FACET("$facet"), 
	UNWIND("$unwind"),
	ADD_FIELDS("$addFields"), 
	PROJECT("$project"),
	LIKE("$regex");

	String value;

	Aggregator(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return getValue();
	}
}
