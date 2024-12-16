package com.example.demo.controller;

import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Docente;
import com.example.demo.service.DocenteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docenti")
public class DocenteController {

    private final DocenteService docenteService;

    public DocenteController(DocenteService docenteService) {
        this.docenteService = docenteService;
    }

    @GetMapping
    public List<DocenteDTO> findAll() {
        return docenteService.findAll();
    }

    @GetMapping("/getDocenteById/{idDocente}")
    public DocenteDTO getDocenteById(@PathVariable(name = "idDocente") Integer id) {
        return docenteService.getDocentById(id);
    }

    @PostMapping("/insertDocente")
    public DocenteDTO insertDocente(@RequestBody DocenteDTO docenteDTO) {
       return docenteService.insertDocente(docenteDTO);
    }

    @PutMapping("/updateDocente/{idDocente}")
    public DocenteDTO updateDocente(@PathVariable(name = "idDocente") Integer id, @RequestBody DocenteDTO docenteDTO) {
        return docenteService.updateDocente(id, docenteDTO);
    }

    @DeleteMapping("/deleteDocente/{idDocente}")
    public DocenteDTO deleteDocente(@PathVariable(name = "idDocente") Integer id) {
        return docenteService.deleteDocente(id);
    }




}
