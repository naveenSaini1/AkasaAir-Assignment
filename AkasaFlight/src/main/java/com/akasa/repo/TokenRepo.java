package com.akasa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akasa.models.Token;

public interface TokenRepo extends JpaRepository<Token, String>{

}
