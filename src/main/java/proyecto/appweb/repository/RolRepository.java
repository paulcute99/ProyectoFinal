/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.appweb.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import proyecto.appweb.model.Rol;

/**
 *
 * @author Paul
 */
public interface RolRepository extends MongoRepository<Rol, String> {

    Rol findByRole(String role);
}
