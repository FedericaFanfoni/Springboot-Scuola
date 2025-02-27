package com.example.demo.controller;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.service.DiscenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discenti")
@RequiredArgsConstructor
public class DiscenteController {

    private final DiscenteService discenteService;

    @GetMapping
    public List<DiscenteDTO> findAll() {
        return discenteService.findAll();
    }

    @GetMapping("/{id}")
    public DiscenteDTO findById(@PathVariable Integer id) {
        return discenteService.findById(id);
    }

    @GetMapping("/corso/{idCorso}")
    public List<DiscenteDTO> filterForCorso(@PathVariable Integer idCorso) {
        return discenteService.filterForCorso(idCorso);
    }

    @PostMapping("/insert")
    public DiscenteDTO insert(@RequestBody DiscenteDTO discenteDTO) {
        return discenteService.insert(discenteDTO);
    }

    @PutMapping("/update/{id}")
    public DiscenteDTO update(@PathVariable Integer id, @RequestBody DiscenteDTO discenteDTO) {
        return discenteService.update(id, discenteDTO);
    }

    @DeleteMapping("/delete/{id}")
    public DiscenteDTO delete(@PathVariable Integer id) {
        return discenteService.delete(id);
    }

    @PatchMapping("/{idDiscente}/remove-corso")
    public DiscenteDTO removeCorso(@PathVariable Integer idDiscente, @RequestBody CorsoDTO corsoDTO) {
        return discenteService.removeCorso(idDiscente, corsoDTO);
    }

    @PatchMapping("/{idDiscente}/add-corso")
    public DiscenteDTO addCorso(@PathVariable Integer idDiscente, @RequestBody CorsoDTO corsoDTO) {
        return discenteService.addCorso(idDiscente, corsoDTO);
    }
}
