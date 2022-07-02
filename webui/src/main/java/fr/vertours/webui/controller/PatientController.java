package fr.vertours.webui.controller;

import fr.vertours.webui.dto.PatientDTO;
import fr.vertours.webui.proxy.PatientProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class PatientController {

    private final PatientProxy patientProxy;

    public PatientController(PatientProxy patientProxy) {
        this.patientProxy = patientProxy;
    }

    @GetMapping("/patient")
    public String getForm(Model model) {
        PatientDTO dto = new PatientDTO();
        model.addAttribute("dto", dto);

        return "patient/add";
    }

    @PostMapping("/patient")
    public String postForm(@ModelAttribute("dto") PatientDTO dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "patient/add";
        }
        patientProxy.savePatient(PatientDTO.converterToBean(dto));
        return "redirect:home";
    }

    @GetMapping("/patient/{id}")
    public String getUpDateForm(@PathVariable long id, Model model) {
        PatientDTO dto = PatientDTO.converterToDTO(
                patientProxy.getPatientById(id));
        model.addAttribute("dto", dto);
        return "patient/update";

    }

    @PostMapping("/patientUpdate")
    public String postUpDateForm(@ModelAttribute("dto") PatientDTO dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "patient/update";
        }
        try {
            patientProxy.updatePatient(dto.getId(), PatientDTO.converterToBean(dto));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "redirect:home";
    }
}
