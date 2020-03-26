package it.carmelolagamba.mongo.utils;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MongoStatusConnection {
	@JsonIgnore
	private String name;
	private Status status;
	private String message;
	private Map<String, MongoStatusConnection> servicesDetail;;

	/** The time elapsed for call in ms */
	private long timeInMillis;

	public MongoStatusConnection() {
	}

	public MongoStatusConnection(String name, Status status, String message) {
		this.name = name;
		this.status = status;
		this.message = message;
	}

	@Override
	public String toString() {
		return "La connessione con " + name + " risulta " + status + " [ " + message + " ]";
	}

	public void addDetail(MongoStatusConnection statusConnection) {
		if (servicesDetail == null)
			servicesDetail = new HashMap<>();
		servicesDetail.put(statusConnection.getName(), statusConnection);
	}

	public String getName() {
		return name;
	}

	public MongoStatusConnection setName(String name) {
		this.name = name;
		return this;
	}

	public Status getStatus() {
		return status;
	}

	public MongoStatusConnection setStatus(Status status) {
		this.status = status;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public MongoStatusConnection setMessage(String message) {
		this.message = message;
		return this;
	}

	public Map<String, MongoStatusConnection> getServicesDetail() {
		return servicesDetail;
	}

	public MongoStatusConnection setServicesDetail(Map<String, MongoStatusConnection> servicesDetail) {
		this.servicesDetail = servicesDetail;
		return this;
	}

	public long getTimeInMillis() {
		return timeInMillis;
	}

	public MongoStatusConnection setTimeInMillis(long timeInMillis) {
		this.timeInMillis = timeInMillis;
		return this;
	}

	public enum Status {
		OK("OK"), KO("KO");

		private String value;

		private Status(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public enum Services {
		MONGODB("MONGODB"), MONGO_REPLICA_SET("MONGO_REPLICA_SET"), MONGO_DB_STATS("MONGO_DB_STATS"),
		MONGO_COLLECTIONS_STATS("MONGO_COLLECTIONS_STATS");

		private String value;

		private Services(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
}
