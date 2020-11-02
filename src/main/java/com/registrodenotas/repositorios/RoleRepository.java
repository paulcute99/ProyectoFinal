package com.registrodenotas.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.registrodenotas.modelos.Role;

public interface RoleRepository extends MongoRepository<Role, String>{

	Role findByRole(String role);
}
