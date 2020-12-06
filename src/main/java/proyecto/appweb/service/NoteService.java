/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.appweb.service;

import com.mongodb.client.result.UpdateResult;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.appweb.model.Note;
import proyecto.appweb.repository.NoteRepository;

/**
 *
 * @author Paul
 */
@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public List<Note> listAllNotes() {
        return noteRepository.getAllNotes();
    }

    public List<Note> listNote(String id) {
        return noteRepository.getAllNote(id);
    }

    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    public UpdateResult save(String id, Note note) {
        return noteRepository.addNote(id, note);
    }

    public Note findNoteById(String id) {
        return noteRepository.findById(id);
    }

    public void deleteNote(Note note) {
        noteRepository.delete(note);
    }

    public void removeNote(String id_student, Note note) {
        noteRepository.removeNote(id_student, note);
    }

}
