package it.carmelolagamba.mongo.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

import it.carmelolagamba.mongo.filter.Filter;
import it.carmelolagamba.mongo.filter.FilterLogicalOperator;
import it.carmelolagamba.mongo.filter.QueryFilter;

public class BasicObjectUtils {

	/**
	 * Return a filter object as a tree object eg. { "a" : { "b" : { "c" : { "d" : {
	 * "$gt" : "value of d" } } } } } The last filter is ("d" : { "$gt" : "value of
	 * d" }) the others are filters used only for the key.
	 * 
	 * @param filters
	 * @return
	 */
	public static BasicDBObject getTreeFilterObject(List<Filter> filters) {
		if (filters == null)
			return new BasicDBObject();
		else if (filters.size() == 1)
			return getFilterObject(head(filters));
		else
			return new BasicDBObject(head(filters).getKey(), getTreeFilterObject(tail(filters)));
	}

	/**
	 * Create a single filter mongo object
	 * 
	 * @param filter
	 * @param preExistentFilterObject {@link BasicDBObject}
	 * @return {@link BasicDBObject}
	 */
	public static BasicDBObject getFilterObject(Filter filter, BasicDBObject preExistentFilterObject) {
		return getInnerFilterObject(filter, preExistentFilterObject);
	}

	/**
	 * Create a single filter mongo object
	 * 
	 * @param filter
	 * @return {@link BasicDBObject}
	 */
	public static BasicDBObject getFilterObject(Filter filter) {
		return getInnerFilterObject(filter, null);
	}

	@SafeVarargs
	public static DBObject createQueryWithTreeFilterObject(List<Filter>... listOfFilters) {

		QueryBuilder builder = QueryBuilder.start();

		if (listOfFilters.length == 0)
			return builder.get();

		for (List<Filter> list : listOfFilters) {
			builder.and(getTreeFilterObject(list));
		}

		return builder.get();
	}

	@SafeVarargs
	public static DBObject createQueryWithTreeFilterObject(QueryFilter... queryFilterList) {

		List<QueryFilter> qfl = Arrays.asList(queryFilterList);

		QueryBuilder builder = QueryBuilder.start();

		if (qfl.size() == 0)
			return builder.get();

		for (QueryFilter qf : qfl) {
			builder.and(getTreeFilterObject(qf.getFilters()));
		}

		return builder.get();
	}
	
	/**
	 * Return a BasicDbObject that combines a certain number di filter objects using a LOGICAL OPERATOR:
	 * - if the operator is AND - returns a conjuction of all the filters
	 * - if the operator is OR - returns a disjunction of all the filters
	 * - if the operator is NOT - returns a negation of just the first filter (any other filter will be ignored)
	 * 
	 * @param operator logical operator that will combine the filters
	 * @param firstFilter first mandatory filter
	 * @param otherFilters other optional filters to combine
	 * @return
	 */
	public static BasicDBObject combineFilters(FilterLogicalOperator operator, Filter firstFilter, Filter ... otherFilters) {
		List<Filter> filterList = Arrays.asList(otherFilters);
		
		BasicDBList bdbList = new BasicDBList();
		bdbList.add(getInnerFilterObject(firstFilter, null));
		
		for (Filter item : filterList) {
			bdbList.add(getInnerFilterObject(item, null));
		}
		switch (operator) {
			case AND:
				return getAndOperation(bdbList);
			case OR:
				return getOrOperation(bdbList);
			case NOT:
				return getNotOperation(getInnerFilterObject(firstFilter, null));
			default:
				return new BasicDBObject();
		}
	}

	/**
	 * Return a BasicDbObject that combines a certain number BasicDbObjects using a LOGICAL OPERATOR:
	 * - if the operator is AND - returns a conjuction of all the BasicDbObjects
	 * - if the operator is OR - returns a disjunction of all the BasicDbObjects
	 * - if the operator is NOT - returns a negation of just the first BasicDbObjects (any other BasicDbObjects will be ignored)
	 * 
	 * @param operator logical operator that will combine the filters
	 * @param firstObjectFilter first mandatory BasicDbObject
	 * @param otherObjectFilters other optional BasicDbObjects to combine
	 * @return
	 */
	public static BasicDBObject combineBasicDBObject(FilterLogicalOperator operator, BasicDBObject firstObjectFilter, BasicDBObject ... otherObjectFilters) {
		List<BasicDBObject> ObjectList = Arrays.asList(otherObjectFilters);
		
		BasicDBList bdbList = new BasicDBList();
		bdbList.add(firstObjectFilter);
		
		for (BasicDBObject item : ObjectList) {
			bdbList.add(item);
		}
		switch (operator) {
			case AND:
				return getAndOperation(bdbList);
			case OR:
				return getOrOperation(bdbList);
			case NOT:
				return getNotOperation(firstObjectFilter);
			default:
				return new BasicDBObject();
		}
	}

	/**
	 * Create a mongo query with all filters in and operator
	 * 
	 * @param filters
	 * @return {@link DBObject}
	 */
	public static DBObject createQuery(List<Filter> filters) {

		QueryBuilder builder = QueryBuilder.start();

		for (Filter filter : filters) {
			builder.and(getFilterObject(filter));
		}

		return builder.get();
	}

	/*
	 * Esiste anche Filters di MongoDb per gestire i filtri, ma ho preferito
	 * wrapparli in modo tale da rendere parlante la query fuori dal mongo-connector
	 * Un miglioramento potrebbe essere usare dentro questa classe i Filters
	 * ufficiali invece di creare a mano i BasicDbObject (solo per prendersi
	 * miglioramenti ufficiali futuri in caso)
	 */
	private static BasicDBObject getInnerFilterObject(Filter filter, BasicDBObject preExistentFilterObject) {
		switch (filter.getOperator()) {
		case EQUALS:
			return getEqualFilter(filter.getKey(), filter.getValue(), preExistentFilterObject);
		case NOT_EQUALS:
			return getNotEqualFilter(filter.getKey(), filter.getValue(), preExistentFilterObject);
		case EXISTS:
			return getExistsFilter(filter.getKey(), filter.getValue(), preExistentFilterObject);
		case GREATER_THAN:
			return getGreaterThanFilter(filter.getKey(), filter.getValue(), preExistentFilterObject);
		case LESS_THEN:
			return getLessThanFilter(filter.getKey(), filter.getValue(), preExistentFilterObject);
		case GREATER_THAN_OR_EQUAL:
			return getGreaterThanOrEqualFilter(filter.getKey(), filter.getValue(), preExistentFilterObject);
		case LESS_THEN_OR_EQUAL:
			return getLessThanOrEqualFilter(filter.getKey(), filter.getValue(), preExistentFilterObject);
		case IN:
			if (filter.getValue() instanceof List) {
				return getInFilter(filter.getKey(), filter.getValue(), preExistentFilterObject);
			} else {
				return getInFilter(filter.getKey(), Arrays.asList(filter.getValue()), preExistentFilterObject);
			}
		case NOT_IN:
			if (filter.getValue() instanceof List) {
				return getNotInFilter(filter.getKey(), filter.getValue(), preExistentFilterObject);
			} else {
				return getNotInFilter(filter.getKey(), Arrays.asList(filter.getValue()), preExistentFilterObject);
			}
		case LIKE:
			return getLikeFilter(filter.getKey(), filter.getValue(), preExistentFilterObject);

		default:
			return new BasicDBObject();
		}
	}

	private static Filter head(List<Filter> list) {
		return list.get(0);
	}

	private static List<Filter> tail(List<Filter> list) {
		if (list.size() == 1)
			return null;
		return list.subList(1, list.size());
	}

	private static BasicDBObject getEqualFilter(String key, Object value, BasicDBObject preExistentFilter) {
		if (preExistentFilter != null) {
			preExistentFilter.put(key, value);
			return preExistentFilter;
		} else
			return new BasicDBObject(key, value);
	}

	private static BasicDBObject getNotEqualFilter(String key, Object value, BasicDBObject preExistentFilter) {
		return generateFilter(key, "$ne", value, preExistentFilter);
	}

	private static BasicDBObject getExistsFilter(String key, Object value, BasicDBObject preExistentFilter) {
		return generateFilter(key, "$exists", value, preExistentFilter);
	}

	private static BasicDBObject getGreaterThanFilter(String key, Object value, BasicDBObject preExistentFilter) {
		return generateFilter(key, "$gt", value, preExistentFilter);
	}

	private static BasicDBObject getLessThanFilter(String key, Object value, BasicDBObject preExistentFilter) {
		return generateFilter(key, "$lt", value, preExistentFilter);
	}

	private static BasicDBObject getGreaterThanOrEqualFilter(String key, Object value, BasicDBObject preExistentFilter) {
		return generateFilter(key, "$gte", value, preExistentFilter);
	}

	private static BasicDBObject getLessThanOrEqualFilter(String key, Object value, BasicDBObject preExistentFilter) {
		return generateFilter(key, "$lte", value, preExistentFilter);
	}

	private static BasicDBObject getInFilter(String key, Object value, BasicDBObject preExistentFilter) {
		return generateFilter(key, "$in", value, preExistentFilter);
	}

	private static BasicDBObject getNotInFilter(String key, Object value, BasicDBObject preExistentFilter) {
		return generateFilter(key, "$nin", value, preExistentFilter);
	}

	private static BasicDBObject getLikeFilter(String key, Object value, BasicDBObject preExistentFilter) {
		Map<String, String> options = new HashMap<>();
		options.put("$options","i");
		return generateFilter(key, "$regex", value, options, preExistentFilter);
	}
	
	private static BasicDBObject getAndOperation(BasicDBList filterList) {
		return new BasicDBObject("$and", filterList);
	}

	private static BasicDBObject getOrOperation(BasicDBList filterList) {
		return new BasicDBObject("$or", filterList);
	}

	private static BasicDBObject getNotOperation(BasicDBObject filter) {
		return new BasicDBObject("$not", filter);
	}

	private static BasicDBObject generateFilter(String key, String operator, Object value) {
		return new BasicDBObject(key, new BasicDBObject(operator, value));
	}

	private static BasicDBObject generateFilter(String key, String operator, Object value,
			BasicDBObject preExistentFilter) {

		if (preExistentFilter != null) {
			preExistentFilter.put(key, new BasicDBObject(operator, value));
			return preExistentFilter;
		}

		return generateFilter(key, operator, value);
	}

	private static BasicDBObject generateFilter(String key, String operator, Object value, Map<String, String> options,
												BasicDBObject preExistentFilter) {

		if (options != null) {
			for (Map.Entry<String, String> entry : options.entrySet()) {
				if (preExistentFilter != null) {
					preExistentFilter.put(key, new BasicDBObject(operator, value).append(entry.getKey(), entry.getValue()));
					return preExistentFilter;
				}

				return generateFilter(key, operator, value);
			}
		}

		if (preExistentFilter != null) {
			preExistentFilter.put(key, new BasicDBObject(operator, value));
			return preExistentFilter;
		}

		return generateFilter(key, operator, value);
	}


	
}
