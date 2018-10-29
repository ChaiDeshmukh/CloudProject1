package com.filezila.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.filezila.model.FileDetails;

@Repository
public interface FileDao extends JpaRepository<FileDetails, Integer>{
	
	public List<FileDetails> findByEmail(String email);

}
