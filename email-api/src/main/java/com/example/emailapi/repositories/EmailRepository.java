package com.example.emailapi.repositories;

import com.example.emailapi.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository  extends JpaRepository<Email,Integer> {
    Email findById(@Param("id") int id);

}
