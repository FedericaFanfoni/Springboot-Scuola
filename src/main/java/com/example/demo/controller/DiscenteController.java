package com.example.demo.controller;

import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.service.DiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discenti")
public class DiscenteController {

    private final DiscenteService discenteService;
    @Autowired
    public DiscenteController(DiscenteService discenteService) {
        this.discenteService = discenteService;
    }

    @GetMapping
    public List<DiscenteDTO> findAll() {
        return discenteService.findAll();
    }

    @GetMapping("/getDiscenteById/{idDiscente}")
    public DiscenteDTO getDiscenteById(@PathVariable(name = "idDiscente") Integer idDiscente) {
        return discenteService.getDiscenteById(idDiscente);
    }

    @PostMapping("/insertDiscente")
    public DiscenteDTO insertDiscente(@RequestBody DiscenteDTO discenteDTO) {
        return discenteService.insertDiscente(discenteDTO);
    }

    @PostMapping("/{idDiscente}/corsi/{idCorso}")
    public DiscenteDTO addCorso(@PathVariable(name = "idDiscente") Integer idDiscente, @PathVariable(name = "idCorso") Integer idCorso) {
        return discenteService.addCorso(idCorso, idDiscente);
    }

    @PutMapping("/updateDiscente/{idDiscente}")
    public DiscenteDTO updateDiscente(@PathVariable(name = "idDiscente") Integer idDiscente, @RequestBody DiscenteDTO discenteDTO) {
        return discenteService.updateDiscente(idDiscente, discenteDTO);
    }

    @DeleteMapping("/deleteDiscente/{idDiscente}")
    public DiscenteDTO deleteDiscente(@PathVariable(name = "idDiscente") Integer id) {
        return discenteService.deleteDiscente(id);
    }
}
