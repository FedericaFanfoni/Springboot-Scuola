package com.example.demo.controller;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.service.CorsoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/corsi")
@RequiredArgsConstructor
public class CorsoController {

    private final CorsoService corsoService;

    @GetMapping
    public List<CorsoDTO> findAll() {
        return corsoService.findAll();
    }

    @GetMapping("/{id}")
    public CorsoDTO findById(@PathVariable Integer id){
        return corsoService.findById(id);
    }

    @GetMapping("available-docente/{idDocente}")
    public List<CorsoDTO> findForDocente(@PathVariable Integer idDocente) {
        return corsoService.findForDocente(idDocente);
    }

    @GetMapping("available-discente/{idDiscente}")
    public List<CorsoDTO> findForDiscente(@PathVariable Integer idDiscente) {
        return corsoService.findForDiscente(idDiscente);
    }

    @PostMapping("/insert/{idDocente}")
    public CorsoDTO insert(@PathVariable Integer idDocente, @RequestBody CorsoDTO corsoDTO) {
        return corsoService.insert(corsoDTO, idDocente);
    }

    @PutMapping("/update/{id}")
    public CorsoDTO update(@PathVariable Integer id,  @RequestBody CorsoDTO corsoDTO) {
        return corsoService.update(id,corsoDTO);
    }

    @DeleteMapping("/delete/{id}")
    public CorsoDTO delete(@PathVariable Integer id) {
        return corsoService.delete(id);
    }

    @PatchMapping("{idCorso}/add-discente")
    public CorsoDTO addDiscente(@PathVariable Integer idCorso, @RequestBody DiscenteDTO discenteDTO) {
        return corsoService.addDiscente(idCorso, discenteDTO);
    }

    @PatchMapping("{idCorso}/remove-discente")
    public CorsoDTO removeDiscente(@PathVariable Integer idCorso, @RequestBody DiscenteDTO discenteDTO) {
        return corsoService.removeDiscente(idCorso, discenteDTO);
    }



}
