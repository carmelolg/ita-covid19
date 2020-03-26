package it.carmelolagamba.mongo.filter;

public enum FilterLogicalOperator {
    AND("and"),
    OR("or"),
    NOT("not");

    String value;

    FilterLogicalOperator(String value) {
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
