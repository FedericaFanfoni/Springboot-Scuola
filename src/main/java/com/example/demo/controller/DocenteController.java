package com.example.demo.controller;

import com.example.demo.DTO.DocenteDTO;
import com.example.demo.service.DocenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docenti")
@RequiredArgsConstructor
public class DocenteController {

    private final DocenteService docenteService;

    @GetMapping
    public List<DocenteDTO> findAll() {
        return docenteService.findAll();
    }

    @GetMapping("/{id}")
    public DocenteDTO findById(@PathVariable Integer id) {
        return docenteService.findById(id);
    }

    @PostMapping("/insert")
    public DocenteDTO insert(@RequestBody DocenteDTO docenteDTO) {
       return docenteService.insert(docenteDTO);
    }

    @PutMapping("/update/{id}")
    public DocenteDTO update(@PathVariable Integer id, @RequestBody DocenteDTO docenteDTO) {
        return docenteService.update(id, docenteDTO);
    }

    @DeleteMapping("/delete/{id}")
    public DocenteDTO delete(@PathVariable Integer id) {
        return docenteService.delete(id);
    }

}
