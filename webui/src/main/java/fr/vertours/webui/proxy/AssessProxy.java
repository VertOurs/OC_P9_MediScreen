package fr.vertours.webui.proxy;


import fr.vertours.webui.bean.AssessBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient( name="AssessMS", url="${proxy.assess}")
public interface AssessProxy {

    @GetMapping("/api/assess/{id}")
    AssessBean getAssessById(@PathVariable long id);



}
