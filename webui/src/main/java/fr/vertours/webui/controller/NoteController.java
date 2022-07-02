package fr.vertours.webui.controller;

import fr.vertours.webui.bean.NoteBean;
import fr.vertours.webui.bean.PatientBean;
import fr.vertours.webui.dto.NoteDTO;
import fr.vertours.webui.dto.NotePageDTO;

import fr.vertours.webui.dto.PatientDTO;
import fr.vertours.webui.proxy.NoteProxy;
import fr.vertours.webui.proxy.PatientProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class NoteController {

    private final PatientProxy patientProxy;
    private final NoteProxy noteProxy;

    public NoteController(PatientProxy patientProxy, NoteProxy noteProxy) {
        this.patientProxy = patientProxy;
        this.noteProxy = noteProxy;
    }

    @GetMapping("/note/{id}")
    public String notePage(@PathVariable long id, Model model) {

        PatientBean patient = patientProxy.getPatientById(id);
        List<NoteBean> notes = noteProxy.allNotes(id);

        NotePageDTO dto = new NotePageDTO(
                patient.getId(),
                patient.getFirstName(),
                patient.getLastName(),
                notes
        );

        model.addAttribute("dto", dto);
        return "note/note";
    }

    @GetMapping("/note/newNote/{id}")
    public String getForm(@PathVariable long id, Model model) {
        PatientBean patient = patientProxy.getPatientById(id);
        NoteDTO dto = new NoteDTO();
        dto.setPatientId(id);
        model.addAttribute("dto", dto);
        return "note/add";
    }
    @PostMapping("/createNote")
    public String postForm(@ModelAttribute("dto") NoteDTO dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "note/add";
        }
        noteProxy.saveNote(NoteDTO.convertToBBean(dto));

        return "redirect:note/"+dto.getPatientId();
    }
    @GetMapping("/noteUpdate/{id}")
    public String getForm(@PathVariable String id, Model model) {
        NoteBean note = noteProxy.getNoteById(id);
        NoteDTO dto = new NoteDTO();
        dto.setPatientId(note.getPatientId());
        dto.setId(id);
        dto.setRecommendation(note.getRecommendation());
        model.addAttribute("dto", dto);
        return "note/update";
    }
    @PostMapping("/updateNote")
    public String postUpDateForm(@ModelAttribute("dto") NoteDTO dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "patient/update";
        }
        noteProxy.updateNote(dto.getId(), NoteDTO.convertToBBean(dto));
        return "redirect:note/"+dto.getPatientId();
    }
}