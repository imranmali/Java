package com.springmvc.fileupload.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author imranali
 *
 */
@Entity
@Table(name = "storage")
public class Storage implements java.io.Serializable {

	private int id;
	private String filename;
	private String extension;
	private Long filesize;
	private String mimetype;
	private String lobtype;
	private byte[] binarylob;
	private String characterlob;

	public Storage() {
	}

	public Storage(int id) {
		this.id = id;
	}

	public Storage(int id, String filename, String extension, Long filesize,
			String mimetype, String lobtype, byte[] binarylob,
			String characterlob) {
		this.id = id;
		this.filename = filename;
		this.extension = extension;
		this.filesize = filesize;
		this.mimetype = mimetype;
		this.lobtype = lobtype;
		this.binarylob = binarylob;
		this.characterlob = characterlob;
	}
	
	@Id
	@Column(name = "id")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "filename")
	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name = "extension", length = 50)
	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	@Column(name = "filesize", precision = 18, scale = 0)
	public Long getFilesize() {
		return this.filesize;
	}

	public void setFilesize(Long filesize) {
		this.filesize = filesize;
	}

	@Column(name = "mimetype", length = 100)
	public String getMimetype() {
		return this.mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	@Column(name = "lobtype", length = 1)
	public String getLobtype() {
		return this.lobtype;
	}

	public void setLobtype(String lobtype) {
		this.lobtype = lobtype;
	}

	@Column(name = "binarylob")
	public byte[] getBinarylob() {
		return this.binarylob;
	}

	public void setBinarylob(byte[] binarylob) {
		this.binarylob = binarylob;
	}

	@Column(name = "characterlob")
	public String getCharacterlob() {
		return this.characterlob;
	}

	public void setCharacterlob(String characterlob) {
		this.characterlob = characterlob;
	}

}
