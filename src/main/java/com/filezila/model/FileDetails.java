package com.filezila.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FileDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int fileID;
	private String fileName;
	private String filePath;
	private String fileDescription;
	private long fileSize;
	private Timestamp createdTime;
	private Timestamp updatedTime;
	private String email;
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public FileDetails()
	{
		
	}
	
	public FileDetails(int fileID, String fileName, String filePath, String fileDescription, long fileSize,
			Timestamp createdTime, Timestamp updatedTime) {
		super();
		this.fileID = fileID;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileDescription = fileDescription;
		this.fileSize = fileSize;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

	public int getFileID() {
		return fileID;
	}

	public void setFileID(int fileID) {
		this.fileID = fileID;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileDescription() {
		return fileDescription;
	}

	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Timestamp getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Override
	public String toString() {
		return "FileDetails [fileID=" + fileID + ", fileName=" + fileName + ", filePath=" + filePath + ", fileDescription="
				+ fileDescription + ", fileSize=" + fileSize + ", createdTime=" + createdTime + ", updatedTime="
				+ updatedTime + "]";
	}

	
	
}
