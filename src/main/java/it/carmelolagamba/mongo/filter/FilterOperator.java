package it.carmelolagamba.mongo.filter;

public enum FilterOperator {
	EQUALS("eq"),
    NOT_EQUALS("ne"),
    EXISTS("exists"),
    GREATER_THAN("gt"),
    LESS_THEN("lt"),
    GREATER_THAN_OR_EQUAL("gte"),
    LESS_THEN_OR_EQUAL("lte"),
    IN("in"),
    NOT_IN("nin"),
    LIKE("like");

    String value;

    FilterOperator(String value) {
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
