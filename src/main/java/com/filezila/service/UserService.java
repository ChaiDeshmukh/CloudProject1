package com.filezila.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filezila.dao.UserDao;
import com.filezila.model.User;



@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	
	public void addUser(User user)
	{
		
		userDao.save(user);
		
	}
	
	public User findUserByEmail(String email)
	{
		
		User user =userDao.findUserByEmail(email);
		return user;
	}
	
	public User validateUser(String email, String password)
	{
		User user = this.findUserByEmail(email);
		if(user !=null && user.getPassword().equals(password))
		{
			return user;
		}
		return null;
	}

}
