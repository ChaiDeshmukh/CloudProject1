package com.filezila.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.filezila.model.User;


@Repository
public interface UserDao  extends JpaRepository<User,Integer>{
	
	@Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(:email)")
    public User findUserByEmail(@Param("email") String email);

}
