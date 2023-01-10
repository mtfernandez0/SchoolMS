package com.mati.springschoolms.controllers;

import com.mati.springschoolms.entities.Classroom;
import com.mati.springschoolms.services.ClassroomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClassroomController {
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping("/classroom")
    public String classroomTable(Model model){
        model.addAttribute("classrooms", classroomService.findAll());

        return "classroomList";
    }

    @PostMapping("/classroom")
    public String addClassroom(@ModelAttribute Classroom classroom){
        try {
            classroomService.save(classroom);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/classroom";
    }

    @RequestMapping("/classroom/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        model.addAttribute("toEdit", id);
        model.addAttribute("classrooms", classroomService.findAll());
        model.addAttribute("enableEdit", true);

        return "classroomList";
    }

    @PutMapping("/classroom/{oldId}")
    public String updateClassroom(@PathVariable String oldId, @ModelAttribute Classroom classroom){
        try {
            classroomService.update(oldId, classroom);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "redirect:/classroom";
    }

    @RequestMapping(value = "/classroom/delete/{id}")
    public String deleteClassroom(@PathVariable String id){
        try {
            classroomService.deleteById(id);
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        return "redirect:/classroom";
    }

    private ClassroomService classroomService;
}
