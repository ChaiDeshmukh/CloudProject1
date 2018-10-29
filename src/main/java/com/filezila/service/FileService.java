package com.filezila.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filezila.dao.FileDao;
import com.filezila.model.FileDetails;

@Service
public class FileService {
	
	@Autowired
	private FileDao fileDao;
	
	public void addFileDetails(FileDetails file)
	{
		fileDao.save(file);
	}
	
	public List<FileDetails> findAllFileDetails()	{
		return fileDao.findAll();
	}
	
	
	public List<FileDetails> findAllFileDetailsByEmail(String email)	{
		return fileDao.findByEmail(email);
	}
	
	public void deleteFileByFid(int fileid) {
		fileDao.delete(fileid);
	}

}
