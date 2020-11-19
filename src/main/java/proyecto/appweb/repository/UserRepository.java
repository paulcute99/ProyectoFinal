/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.appweb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import proyecto.appweb.model.User;

/**
 *
 * @author Paul
 */
public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);
}
