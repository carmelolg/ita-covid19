package it.carmelolagamba.mongo.service.crud;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;

import com.google.common.collect.Lists;
import com.mongodb.client.MongoCollection;

import it.carmelolagamba.mongo.filter.AggregatorItem;
import it.carmelolagamba.mongo.service.CollectionService;
import it.carmelolagamba.mongo.utils.AggregatorObjectUtils;

interface MongoAggregateOperationInterface {

	public CollectionService getCollectionService();

	public Logger getLogger();

	default public <T> List<T> aggregate(MongoCollection<T> collection, AggregatorItem aggregator) {
		return Lists.newArrayList(
				collection.aggregate(Arrays.asList(AggregatorObjectUtils.getAggregatorObject(aggregator))));
	}

	default public <T> List<T> aggregate(MongoCollection<T> collection, List<AggregatorItem> aggregatorList) {
		return Lists.newArrayList(collection.aggregate(AggregatorObjectUtils.getAggregatorObjects(aggregatorList)));
	}
}
