/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.appweb.repository;

import com.mongodb.client.result.UpdateResult;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import proyecto.appweb.model.Note;
import proyecto.appweb.model.Student;

/**
 *
 * @author Paul
 */
@Repository
public class NoteRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Note save(Note note) {
        return mongoTemplate.save(note);
    }

    public List<Note> getAllNotes() {
        return mongoTemplate.findAll(Note.class);
    }

    public UpdateResult addNote(String id, Note note) {
        return mongoTemplate.updateFirst(
                new Query().addCriteria(Criteria.where("id").is(id)),
                new Update().addToSet("notes", note),
                Student.class);
    }

    public List<Note> getAllNote(String id) {

        return mongoTemplate.findDistinct(new Query().addCriteria(Criteria.where("id").is(id)), "notes", Student.class, Note.class);

    }

    public Note findById(String id) {
        return mongoTemplate.findOne(new Query().addCriteria(Criteria.where("id").is(id)), Note.class);
    }

    public void delete(Note note) {
        mongoTemplate.remove(note);
    }

    public UpdateResult removeNote(String id_student, Note note) {
        return mongoTemplate.updateFirst(
                new Query().addCriteria(Criteria.where("id").is(id_student)),
                new Update().pull("notes", note),
                Student.class);
    }

}
