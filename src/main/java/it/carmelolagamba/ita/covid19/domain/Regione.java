package it.carmelolagamba.ita.covid19.domain;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

public class Regione {

	@BsonId
	private ObjectId idProperty;

	String nome;
	String code;

	public ObjectId getIdProperty() {
		return idProperty;
	}

	public void setIdProperty(ObjectId idProperty) {
		this.idProperty = idProperty;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
