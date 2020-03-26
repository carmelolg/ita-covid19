package it.carmelolagamba.mongo.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lagamba on 08/11/2018.
 */
public class QueryFilter {

	List<Filter> filters = new ArrayList<>();
	List<Sort> sortFilters = new ArrayList<>();
	List<AggregatorItem> aggregators = new ArrayList<>();

	public QueryFilter() {
	}

	public QueryFilter(List<Filter> filters, List<Sort> sortFilters, List<AggregatorItem> aggregators) {
		this.filters = filters;
		this.sortFilters = sortFilters;
		this.aggregators = aggregators;
	}

	public QueryFilter withAggregators(List<AggregatorItem> aggregators) {
		this.setAggregators(aggregators);
		return this;
	}

	public QueryFilter withFilters(List<Filter> filters) {
		this.setFilters(filters);
		return this;
	}
	
	public QueryFilter withSortFilters(List<Sort> sortFilters) {
		this.setSortFilters(sortFilters);
		return this;
	}
		
	public List<Filter> getFilters() {
		return filters;
	}

	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}

	public List<Sort> getSortFilters() {
		return sortFilters;
	}

	public void setSortFilters(List<Sort> sortFilters) {
		this.sortFilters = sortFilters;
	}

	public List<AggregatorItem> getAggregators() {
		return aggregators;
	}

	public void setAggregators(List<AggregatorItem> aggregators) {
		this.aggregators = aggregators;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		if (filters != null) {
			builder.append("filters: [");
			filters.stream().forEach(f -> builder.append(f.toString()));
			builder.append("] ");
		}
		if (sortFilters != null) {
			builder.append("sort filters: [");
			sortFilters.stream().forEach(f -> builder.append(f.toString()));
			builder.append("]");
		}

		if (aggregators != null) {
			builder.append("aggregator filters: [");
			aggregators.stream().forEach(f -> builder.append(f.toString()));
			builder.append("]");
		}
		return builder.toString();
	}
}
