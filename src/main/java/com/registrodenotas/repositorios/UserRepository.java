package com.registrodenotas.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.registrodenotas.modelos.User;


public interface UserRepository extends MongoRepository<User, String> {
    
    User findByEmail(String email);

}
