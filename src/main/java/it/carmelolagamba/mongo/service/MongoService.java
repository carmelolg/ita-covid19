package it.carmelolagamba.mongo.service;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.Convention;
import org.bson.codecs.pojo.Conventions;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ReplicaSetStatus;
import com.mongodb.ServerAddress;
import com.mongodb.async.client.MongoClients;
import com.mongodb.client.MongoIterable;

import it.carmelolagamba.mongo.config.MongoProperties;
import it.carmelolagamba.mongo.utils.MongoStatusConnection;
import it.carmelolagamba.mongo.utils.MongoStatusConnection.Status;

@Component
@EnableConfigurationProperties({ MongoProperties.class })
public class MongoService {

	private Logger logger = LoggerFactory.getLogger(MongoService.class);

	@Autowired
	private MongoProperties mongoProperties;

	private MongoClient mongoClientInstance = null;
	private com.mongodb.async.client.MongoClient asyncMongoClientInstance = null;

	public CodecRegistry getCodecRegistry() {

		List<Convention> conventions = new ArrayList<>();
		conventions.addAll(Conventions.DEFAULT_CONVENTIONS);

		return fromRegistries(MongoClient.getDefaultCodecRegistry(),
				fromProviders(PojoCodecProvider.builder().conventions(conventions).automatic(true).build()));
	}

	public MongoClient getMongoClient() {

		if (mongoClientInstance == null) {

			String clusterConnectionString = String.join("", "mongodb+srv://", mongoProperties.getUser(), ":",
					mongoProperties.getPassword(), "@", mongoProperties.getClusterHost(), "/",
					mongoProperties.getDbName());
			
			MongoClientURI uri = new MongoClientURI(clusterConnectionString);
			mongoClientInstance = new MongoClient(uri);

		}

		return mongoClientInstance;
	}

	public com.mongodb.async.client.MongoClient getAsyncMongoClient() {

		if (asyncMongoClientInstance == null) {

			List<String> hostsPrepare = mongoProperties.getHosts().stream()
					.map(h -> h.concat(":" + mongoProperties.getPort())).collect(Collectors.toList());
			String hosts = String.join(",", hostsPrepare);

			ConnectionString connectionString = new ConnectionString(
					"mongodb://" + hosts + "/?authSource=" + mongoProperties.getDbName());

			connectionString = new ConnectionString(
					"mongodb://carmelolg:r1p_oppy@ita-covid19-shard-00-00-beipl.mongodb.net:27017,ita-covid19-shard-00-01-beipl.mongodb.net:27017,ita-covid19-shard-00-02-beipl.mongodb.net:27017/test?ssl=true&replicaSet=ita-covid19-shard-0&authSource=admin&retryWrites=true&w=majority");

			MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString)
					.build();

			// FIXME temporaneo finche ambiente di test non abbia authentication
			if (mongoProperties.isAuth()) {
				MongoCredential credential = MongoCredential.createCredential(mongoProperties.getUser(),
						mongoProperties.getDbName(), mongoProperties.getPassword().toCharArray());

				settings = MongoClientSettings.builder().applyConnectionString(connectionString).credential(credential)
						.build();
			}

			asyncMongoClientInstance = MongoClients.create(settings);

		}

		return asyncMongoClientInstance;
	}

	private void ping() {
		DBObject ping = new BasicDBObject("ping", "1");
		MongoIterable<String> dbList = getMongoClient().listDatabaseNames();
		getMongoClient().getDatabase(dbList.first()).runCommand((Bson) ping);
	}

	private MongoStatusConnection replicaStatus() {

		String message = "";
		MongoStatusConnection replicaStatus = new MongoStatusConnection();
		replicaStatus.setName(MongoStatusConnection.Services.MONGO_REPLICA_SET.getValue());

		try {
			final long startTime = System.currentTimeMillis();
			ReplicaSetStatus replicaSetStatus = getMongoClient().getReplicaSetStatus();

			String[] hostParameters = getMongoClient().getConnectPoint().split(":", 2);
			String hostAddress = hostParameters[0];
			Integer hostPort = Integer.parseInt(hostParameters[1]);
			StringBuilder builder = new StringBuilder();

			if (replicaSetStatus == null) {
				builder.append("I'm not currently connected to the mongo replica set");
				builder.append(" Host: " + hostAddress + " Port: " + hostPort);
			} else {
				boolean isMaster = replicaSetStatus.isMaster(new ServerAddress(hostAddress, hostPort));
				builder.append("I'm currently connected to the mongo replica set: " + replicaSetStatus.getName());
				builder.append(" Host: " + hostAddress + " Port: " + hostPort);
				builder.append(" I'm connected to master: " + isMaster);
			}

			final long elapsedTime = System.currentTimeMillis() - startTime;
			replicaStatus.setMessage(builder.toString());
			replicaStatus.setStatus(Status.OK);
			replicaStatus.setTimeInMillis(elapsedTime);

		} catch (NumberFormatException e) {
			message = "[MongoService]: errore nel recuperare l'informazione dell'host primario.";
			logger.error(message, e);
			replicaStatus.setMessage(message);
			replicaStatus.setStatus(Status.KO);
		} catch (Exception e) {
			message = "[MongoService]: errore nel recuperare l'informazione dell'host primario.";
			logger.error(message, e);
			replicaStatus.setMessage(message);
			replicaStatus.setStatus(Status.KO);
		}

		return replicaStatus;
	}

	public MongoStatusConnection statusInfo() {
		final String statusName = MongoStatusConnection.Services.MONGODB.getValue();
		MongoStatusConnection statusConnection = new MongoStatusConnection();
		try {
			logger.info("Verifica stato connessione {}", MongoStatusConnection.Services.MONGODB.getValue());
			final long startTime = System.currentTimeMillis();
			ping();
			final long elapsedTime = System.currentTimeMillis() - startTime;
			statusConnection.setName(statusName).setStatus(MongoStatusConnection.Status.OK)
					.setMessage("Connessione Attiva").setTimeInMillis(elapsedTime);

			statusConnection.addDetail(replicaStatus());
			logger.info("Status connessione {} - {}", MongoStatusConnection.Services.MONGODB.getValue(),
					statusConnection.toString());
		} catch (Exception ex) {
			logger.error("Status connessione " + MongoStatusConnection.Services.MONGODB.getValue() + " - KO", ex);
			statusConnection.setName(statusName).setStatus(MongoStatusConnection.Status.KO)
					.setMessage("Errore di connessione - " + ex.getMessage());
		}
		return statusConnection;
	}

}
