package com.springboot.first.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.first.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{
    
    public Admin findByEmail(String email);
    
    Admin findByUserName(String username);
    
    public boolean existsByEmail(String email);
    
    public boolean existsByContact(String contact);
    
    public boolean existsByUserName(String username);
    
    
    @Query("SELECT a FROM Admin a WHERE a.userName=:userName AND a.password=:password")
    public Admin findByUsernameAndPassword(String userName,String password);
}
