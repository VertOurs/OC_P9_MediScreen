package fr.vertours.assessms.proxy;

import fr.vertours.assessms.Bean.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="NoteMS", url="${proxy.note}")
public interface NoteProxy {

    @GetMapping("/api/note/all/{id}")
    List<NoteBean> allNotes(@PathVariable long id);
}
