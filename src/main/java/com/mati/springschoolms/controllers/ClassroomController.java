package com.mati.springschoolms.controllers;

import com.mati.springschoolms.entities.Classroom;
import com.mati.springschoolms.services.ClassroomService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class ClassroomController {
    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @PreAuthorize("hasAnyAuthority('Director', 'Professor', 'Student', 'Other')")
    @GetMapping("/classroom")
    public String classroomTable(Model model){
        model.addAttribute("classrooms", classroomService.findAll());

        Collection<GrantedAuthority> authorities =
                (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        boolean isDirector = authorities.stream().map(Object::toString).toList().contains("Director");

        model.addAttribute("isDirector", isDirector);

        return "classroomList";
    }

    @PreAuthorize("hasAuthority('Director')")
    @PostMapping("/classroom")
    public String addClassroom(@ModelAttribute Classroom classroom){
        try {
            classroomService.save(classroom);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/classroom";
    }
    @PreAuthorize("hasAuthority('Director')")
    @RequestMapping("/classroom/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        model.addAttribute("toEdit", id);
        model.addAttribute("classrooms", classroomService.findAll());
        model.addAttribute("enableEdit", true);

        return "classroomList";
    }

    @PreAuthorize("hasAuthority('Director')")
    @PutMapping("/classroom/{oldId}")
    public String updateClassroom(@PathVariable String oldId, @ModelAttribute Classroom classroom){
        try {
            classroomService.update(oldId, classroom);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "redirect:/classroom";
    }

    @PreAuthorize("hasAuthority('Director')")
    @DeleteMapping(value = "/classroom/delete")
    public String deleteClassroom(@RequestParam("id") String id){
        try {
            classroomService.deleteById(id);
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        return "redirect:/classroom";
    }
}
