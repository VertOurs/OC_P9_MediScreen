package fr.vertours.assessms.controller;


import fr.vertours.assessms.model.Assessment;
import fr.vertours.assessms.service.AssessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("API for risk diabetes")
@RestController
@Slf4j
@RequestMapping("/api/assess")
public class AssessController {

    private final AssessService service;

    public AssessController(AssessService service) {
        this.service = service;
    }

    @ApiOperation(value = "home application")
    @GetMapping("")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok().body("Greetings form AssessMS API !");
    }

    @ApiOperation(value = "Get Triggers by id")
    @GetMapping("/{id}")
    public ResponseEntity<Assessment> getPatientById(@PathVariable long id) {
        Assessment assessment = service.calculateRiskAssessment(id);
        log.debug("controller : get assessment by PatientId : " + id);
        return ResponseEntity.ok().body(assessment);
    }
}
