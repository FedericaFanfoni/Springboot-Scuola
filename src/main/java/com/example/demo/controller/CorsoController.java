package com.example.demo.controller;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.service.CorsoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/corsi")

public class CorsoController {

    private final CorsoService corsoService;

    public CorsoController(CorsoService corsoService) {
        this.corsoService = corsoService;
    }

    @GetMapping
    public List<CorsoDTO> findAll() {
        return corsoService.findAll();
    }

    @GetMapping("/getCorsoById/{idCorso}")
    public CorsoDTO getCorsoById(@PathVariable(name = "idCorso") Integer id){
        return corsoService.getCorsoById(id);
    }

    @PostMapping("/insertCorso")
    public CorsoDTO insertCorso(@RequestBody CorsoDTO corsoDTO) {
        return corsoService.insertCorso(corsoDTO);
    }

    @PutMapping("/updateCorso/{idCorso}")
    public CorsoDTO updateCorso(@PathVariable(name = "idCorso") Integer id,  @RequestBody CorsoDTO corsoDTO) {
        return corsoService.updateCorso(id,corsoDTO);
    }
    @DeleteMapping("/deleteCorso/{idCorso}")
    public CorsoDTO deleteCorso(@PathVariable(name = "idCorso") Integer id) {
        return corsoService.deleteCorso(id);
    }


}
