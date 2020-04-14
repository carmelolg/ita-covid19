package it.carmelolagamba.ita.covid19.view;

import java.util.Date;

public class FileImportedDto {

	private String filename;
    private Date date;

	public FileImportedDto(String filename, Date date) {
		this.filename = filename;
		this.date = date;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
