package fr.vertours.webui.proxy;

import fr.vertours.assessms.model.Assessment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient( name="AssessMS", url="http://localhost:8083")
public interface AssessProxy {

    @GetMapping("/api/assess/{id}")
    Assessment getAssessById(@PathVariable long id);

}
