package com.springboot.first.repository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.first.dto.FindUserIdAndNames;
import com.springboot.first.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
	//Custom query to get userId and userName
	@Query("select new com.springboot.first.dto.FindUserIdAndNames(u.userId,u.userName)FROM Users u")
	List<FindUserIdAndNames> findIdandName();
	
	
	//Generate Random Password String
	static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    static final int RANDOM_STRING_LENGTH = 8;
    default void generateUserPassword(Users users) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
            builder.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        users.setPassword(builder.toString());
    }
    
    Optional<Users> findByEmail(String email);
    
    Users findByUserName(String username);

}
