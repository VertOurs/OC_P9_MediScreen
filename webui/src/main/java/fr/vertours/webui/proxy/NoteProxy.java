package fr.vertours.webui.proxy;

import fr.vertours.webui.bean.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name="NoteMS", url="${proxy.note}")
public interface NoteProxy {

    @GetMapping("/api/note/all/{id}")
    List<NoteBean> allNotes(@PathVariable long id);

    @GetMapping("api/note/{id}")
    NoteBean getNoteById(@PathVariable String id);


    @PostMapping("api/note/create")
    NoteBean saveNote(@RequestBody @Valid NoteBean note);

    @PutMapping("api/note/update/{id}")
    NoteBean updateNote(@PathVariable String id, @RequestBody @Valid NoteBean note);
}
