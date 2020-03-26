package it.carmelolagamba.mongo.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific.
 * <p>
 * Properties are configured in the application.yml file. See
 * {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "mongo", ignoreUnknownFields = false)
public class MongoProperties {

	List<String> hosts;
	String clusterHost;
	String port, dbName, user, password;
	Boolean auth;

	public String getClusterHost() {
		return clusterHost;
	}

	public void setClusterHost(String clusterHost) {
		this.clusterHost = clusterHost;
	}

	public Boolean getAuth() {
		return auth;
	}

	public void setAuth(Boolean auth) {
		this.auth = auth;
	}

	public List<String> getHosts() {
		return hosts;
	}

	public void setHosts(List<String> hosts) {
		this.hosts = hosts;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAuth() {
		return auth;
	}

	public void setAuth(boolean auth) {
		this.auth = auth;
	}

}
