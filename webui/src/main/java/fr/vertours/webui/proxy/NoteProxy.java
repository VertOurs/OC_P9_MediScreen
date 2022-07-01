package fr.vertours.webui.proxy;

import fr.vertours.webui.bean.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="NoteMS", url="http://localhost:8082")
public interface NoteProxy {

    @GetMapping("/api/note/all/{id}")
    List<NoteBean> allNotes(@PathVariable long id);
}
