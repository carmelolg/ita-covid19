package it.carmelolagamba.mongo.filter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author lagamba
 *
 */
public class AggregatorItem {

	Aggregator aggregatorItem;

	Item item;

	/**
	 * Questa struttura dati per essere più adattabile a più aggregatori potrà
	 * diventare una matrice 3D - (l'idea da condividere a voce).
	 * 
	 * Naturalmente alla classe vanno aggiunte tutte le funzioni per accedere ai
	 * dati con facilità
	 */
	// TODO per ora supportiamo un facet per volta
	List<Item> items = new ArrayList<>();

	public AggregatorItem() {
	}

	public AggregatorItem(Aggregator aggregatorItem, String key) {

		if (aggregatorItem == null)
			this.aggregatorItem = Aggregator.MATCH;
		else
			this.aggregatorItem = aggregatorItem;

		this.item = new Item(this).withKey(key);
	}

	public AggregatorItem(Aggregator aggregatorItem, Object value) {

		if (aggregatorItem == null)
			this.aggregatorItem = Aggregator.MATCH;
		else
			this.aggregatorItem = aggregatorItem;

		this.item = new Item(this).withValue(value);
	}

	public AggregatorItem(Aggregator aggregatorItem, String key, Object value) {

		if (aggregatorItem == null)
			this.aggregatorItem = Aggregator.MATCH;
		else
			this.aggregatorItem = aggregatorItem;

		this.item = new Item(this, key, value);
	}

	public AggregatorItem(Aggregator aggregatorItem, String key,
			Pair<String, Object>... values) {

		if (aggregatorItem == null)
			this.aggregatorItem = Aggregator.MATCH;
		else
			this.aggregatorItem = aggregatorItem;

		this.item = new Item(this).withKey(key);
		this.items = Arrays.asList(values).stream().map(pair -> new Item(this, pair.getLeft(), pair.getRight()))
				.collect(Collectors.toList());
	}

	public Aggregator getAggregatorItem() {
		return aggregatorItem;
	}

	public void setAggregatorItem(Aggregator aggregatorItem) {
		if (aggregatorItem == null)
			this.aggregatorItem = Aggregator.MATCH;
		else
			this.aggregatorItem = aggregatorItem;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@JsonIgnore
	public boolean isMatchAggregator() {
		return this.aggregatorItem.equals(Aggregator.MATCH);
	}

	@JsonIgnore
	public boolean isSortAggregator() {
		return this.aggregatorItem.equals(Aggregator.SORT);
	}

	@Override
	public String toString() {
		return aggregatorItem.toString() + "(" + item.getKey() + ": " + item.getValue().toString() + ")";
	}

	public class Item {

		AggregatorItem parent;

		String key;
		Object value;

		public Item(AggregatorItem parent) {
			this.parent = parent;
		}

		public Item(AggregatorItem parent, String key, Object value) {
			this.parent = parent;
			this.setKey(key);
			this.setValue(value);
		}

		public Item withKey(String key) {
			this.setKey(key);
			return this;
		}

		public Item withValue(Object value) {
			this.setValue(value);
			return this;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			if (parent.isSortAggregator()) {
				this.value = getSortOrder(value);
			} else {
				this.value = value;
			}
		}

		@JsonIgnore
		private Integer getSortOrder(Object value) {
			try {
				return (int) value.getClass().getMethod("getValue", null).invoke(value, null);
			} catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException
					| InvocationTargetException e) {
				return 1;
			}
		}
	}

}
