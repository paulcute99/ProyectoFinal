/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.appweb.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import proyecto.appweb.model.Note;
import proyecto.appweb.service.NoteService;

/**
 *
 * @author Paul
 */
@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;

    @RequestMapping(path = "/ver-trimestres/{id}")
    public String listNotes(@PathVariable("id") String id_estudiante, Model model, RedirectAttributes redirectAttrs) {

        Note note = new Note();
        List<Note> notes = noteService.listNote(id_estudiante);

        model.addAttribute("nota", note);
        model.addAttribute("notas", notes);
        model.addAttribute("id_estudiante", id_estudiante);
        return "ver-trimestres-id";
    }

    @RequestMapping(path = "/ver-trimestres")
    public String listAllNotes(Model model, RedirectAttributes redirectAttrs) {

        Note note = new Note();
        List<Note> notes = noteService.listAllNotes();

        model.addAttribute("nota", note);
        model.addAttribute("notas", notes);
        return "ver-trimestres";
    }

    @RequestMapping(path = "/save-note")
    public String saveNote(@ModelAttribute Note note, BindingResult bindingResult, RedirectAttributes redirectAttrs) {

        if (bindingResult.hasErrors()) {
            System.out.print(bindingResult.toString());
            bindingResult
                    .rejectValue("Nota", "error.note",
                            "No se  ha podido guardar la nota del estudiante");
            return "redirect:/ver-trimestres";
        } else {

            noteService.saveNote(note);
        }

        return "redirect:/ver-trimestres";
    }

    @RequestMapping(path = "/save-note/{id_estudiante}")
    public String createNewNote(@PathVariable String id_estudiante, @ModelAttribute Note note, BindingResult bindingResult, RedirectAttributes redirectAttrs) {

        if (bindingResult.hasErrors()) {
            System.out.print(bindingResult.toString());
            bindingResult
                    .rejectValue("Nota", "error.note",
                            "No se  ha podido guardar la nota del estudiante");
            return "redirect:/formularios-curso/students/{id_curso}";
        } else {

            noteService.saveNote(note);
            noteService.save(id_estudiante, note);
        }

        return "redirect:/ver-trimestres/{id_estudiante}";
    }

    @PostMapping(path = "/delete-note/{id_estudiante}")
    public String deleteNote(@PathVariable String id_estudiante, @ModelAttribute Note note, Model model, RedirectAttributes redirectAttrs) {

        redirectAttrs
                .addFlashAttribute("mensaje", "Se ha eleminado correctamente")
                .addFlashAttribute("clase", "warning");
        noteService.removeNote(id_estudiante, note);
        noteService.deleteNote(note);

        return "redirect:/ver-trimestres/{id_estudiante}";
    }

    @PostMapping(path = "/delete-note")
    public String deleteNotes(@ModelAttribute Note note, Model model, RedirectAttributes redirectAttrs) {

        redirectAttrs
                .addFlashAttribute("mensaje", "Se ha eleminado correctamente")
                .addFlashAttribute("clase", "warning");
        noteService.deleteNote(note);

        return "redirect:/ver-trimestres";
    }

    @PostMapping(path = "/edit_note/{id}")
    public String editAndSaveNotes(@PathVariable String id, @ModelAttribute @Valid Note note, Model model, BindingResult bindingResult) {
        System.out.print(id);
        if (bindingResult.hasErrors()) {
            System.out.print(bindingResult.toString());
            bindingResult
                    .rejectValue("Edit", "error.student",
                            "Error al editar la nota");
            return "redirect:/formularios-curso/students/{id_curso}";
        } else {

            noteService.saveNote(note);
        }
        return "redirect:/ver-trimestres";
    }

    @RequestMapping(path = "/edit_note/{id}")
    public String editNotes(@PathVariable("id") String nota, Model model, RedirectAttributes redirectAttrs) {
        Note note = new Note();
        model.addAttribute("nota", note);
        model.addAttribute("id_nota", nota);
        return "editar-notas";
    }
    
       @PostMapping(path = "/edit_notes/{id}")
    public String editAndSaveNotesById(@PathVariable String id, @ModelAttribute @Valid Note note, Model model, BindingResult bindingResult) {
        System.out.print(id);
        if (bindingResult.hasErrors()) {
            System.out.print(bindingResult.toString());
            bindingResult
                    .rejectValue("Edit", "error.student",
                            "Error al editar la nota");
            return "redirect:/formularios-curso/students/{id_curso}";
        } else {

            noteService.saveNote(note);
        }
        return "redirect:/ver-alumnos";
    }

    @RequestMapping(path = "/edit_notes/{id}")
    public String editNotesById(@PathVariable("id") String nota, Model model, RedirectAttributes redirectAttrs) {
        Note note = new Note();
        model.addAttribute("nota", note);
        model.addAttribute("id_nota", nota);
        return "editar-nota-id";
    }
}
