package it.carmelolagamba.mongo.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Facet;
import com.mongodb.client.model.Field;

import it.carmelolagamba.mongo.filter.AggregatorItem;
import it.carmelolagamba.mongo.filter.QueryFilter;

public class AggregatorObjectUtils {

	private static Logger log = LoggerFactory.getLogger(AggregatorObjectUtils.class);

	private static final String FIELD_OPERATOR = "$";

	public static List<Bson> createQuery(QueryFilter filters) {
		return getAggregatorObjects(filters.getAggregators());
	}

	public static Bson getAggregatorObject(AggregatorItem aggregatorItem) {

		if (aggregatorItem.getItem() == null && aggregatorItem.getItems().isEmpty()) {
			throw new RuntimeException("Aggregatore non impostato correttamente: [item or items missed].");
		}

		BasicDBObject dbObject = new BasicDBObject(aggregatorItem.getItem().getKey(),
				aggregatorItem.getItem().getValue());
		switch (aggregatorItem.getAggregatorItem()) {
		case LIKE:
			BasicDBObject likeObject = new BasicDBObject(aggregatorItem.getItem().getKey(),
					new BasicDBObject("$regex", aggregatorItem.getItem().getValue()).append("$options", 'i'));
			return Aggregates.match(likeObject);
		case MATCH:
			return Aggregates.match(dbObject);
		case SORT:
			return Aggregates.sort(dbObject);
		case FACET:
			List<Bson> items = aggregatorItem.getItems().stream().map(i -> new BasicDBObject(i.getKey(), i.getValue()))
					.collect(Collectors.toList());
			return Aggregates.facet(new Facet(aggregatorItem.getItem().getKey(), items));
		case PROJECT:
			return Aggregates.project(dbObject);
		case LIMIT:
			return Aggregates.limit(getInteger(aggregatorItem.getItem().getValue()));
		case SKIP:
			return Aggregates.skip(getInteger(aggregatorItem.getItem().getValue()));
		case UNWIND:
			return Aggregates.unwind(buildStringField(aggregatorItem.getItem().getKey()));
		case ADD_FIELDS:
			List<Field<?>> fields = aggregatorItem.getItems().stream()
					.map(field -> new Field<>(field.getKey(), buildStringField(field.getValue())))
					.collect(Collectors.toList());
			return Aggregates.addFields(fields);
		default:
			log.warn("Aggregatore non impostato.");
			return Aggregates.match(dbObject);
		}
	}

	public static List<Bson> getAggregatorObjects(List<AggregatorItem> items) {
		return items.stream().map(AggregatorObjectUtils::getAggregatorObject).collect(Collectors.toList());
	}

	private static Integer getInteger(Object value) {
		try {
			return Integer.valueOf((int) value);
		} catch (ClassCastException | NullPointerException e) {
			log.error("Il tipo inserito non è corretto per il filtro associato: ", e);
			throw e;
		}
	}

	private static String buildStringField(Object value) {
		if (value instanceof String && !((String) value).startsWith(FIELD_OPERATOR)) {
			return String.join("", FIELD_OPERATOR, (String) value);
		}

		return (String) value;
	}

}
