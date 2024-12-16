package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public class CorsoDTO {

    private Integer id;
    private String nomeCorso;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataInizio;
    private Integer durata;
    private DocenteDTO docenteDTO;
    private List<DiscenteDTO> discentiDTO;

    public CorsoDTO() {}

    public CorsoDTO(Integer id, String nomeCorso, LocalDate dataInizio, Integer durata) {
        this.id = id;
        this.nomeCorso = nomeCorso;
        this.dataInizio = dataInizio;
        this.durata = durata;
    }

    public CorsoDTO(Integer id, String nomeCorso, LocalDate dataInizio, Integer durata, DocenteDTO docenteDTO ) {
        this.id = id;
        this.nomeCorso = nomeCorso;
        this.dataInizio = dataInizio;
        this.durata = durata;
        this.docenteDTO = docenteDTO;

    }

    public CorsoDTO(Integer id, String nomeCorso, LocalDate dataInizio, Integer durata, DocenteDTO docenteDTO, List<DiscenteDTO> discentiDTO ) {
        this.id = id;
        this.nomeCorso = nomeCorso;
        this.dataInizio = dataInizio;
        this.durata = durata;
        this.docenteDTO = docenteDTO;
        this.discentiDTO = discentiDTO;

    }

    public Integer getId(){
        return this.id;
    }

    public String getNomeCorso(){
        return this.nomeCorso;
    }

    public LocalDate getDataInizio(){
        return this.dataInizio;
    }

    public Integer getDurata(){
        return this.durata;
    }

    public DocenteDTO getDocenteDTO(){
        return this.docenteDTO;
    }

    public List<DiscenteDTO> getDiscentiDTO(){
        return this.discentiDTO;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void setNomeCorso(String nomeCorso){
        this.nomeCorso = nomeCorso;
    }

    public void setDataInizio(LocalDate dataInizio){
        this.dataInizio = dataInizio;
    }

    public void setDurata(Integer durata){
        this.durata = durata;
    }

    public void setDocenteDTO(DocenteDTO docenteDTO){
        this.docenteDTO = docenteDTO;
    }

    public void setDiscentiDTO(List<DiscenteDTO> discentiDTO){
        this.discentiDTO = discentiDTO;
    }


}
