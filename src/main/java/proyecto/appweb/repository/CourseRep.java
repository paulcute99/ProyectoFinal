/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.appweb.repository;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import proyecto.appweb.model.Student;

/**
 *
 * @author Paul
 */
@Repository
public class CourseRep {

    @Autowired
    private MongoTemplate mongoTemplate;

    public UpdateResult saveStudentByid(String id, Student student) {
        return mongoTemplate.updateFirst(new Query().addCriteria(Criteria.where("id").is(id)), new Update().addToSet("estudiantes", student), "course");
    }
}
