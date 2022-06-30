package fr.vertours.webui.controller;

import fr.vertours.assessms.model.Assessment;
import fr.vertours.webui.dto.HomeDTO;
import fr.vertours.webui.proxy.AssessProxy;
import fr.vertours.webui.proxy.PatientProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {

    private final PatientProxy  patientProxy;
    private final AssessProxy assessProxy;

    public HomeController(PatientProxy patientProxy, AssessProxy assessProxy) {
        this.patientProxy = patientProxy;
        this.assessProxy = assessProxy;
    }

    @GetMapping("/home")
    public String home(Model model) {
        HomeDTO dto = new HomeDTO();
        dto.setPatients(patientProxy.allPatients());
        model.addAttribute("dto", dto);
        System.out.println(dto);
        return "home/home";
    }

//    @PostMapping("/home")
//    public String home(@ModelAttribute("dto") HomeDTO dto, BindingResult bd) {
//        System.out.println("POST " + dto);
//        Assessment assess = assessProxy.getAssessById(dto.getPatient().getId());
//        return "redirect:/home/home";
//    }

    @PostMapping("/home")
    public ModelAndView home(@ModelAttribute("dto") HomeDTO dto, BindingResult br) {
        if (br.hasErrors()) {
            return new ModelAndView("index");
        }
        try {
            assessProxy.getAssessById(dto.getPatient().getId());
        } catch (RuntimeException e) {
            br.rejectValue(
                    "patient", "", e.getMessage());
            return new ModelAndView("index");
        }

        return new ModelAndView(new RedirectView("/home/home"));
    }

}
