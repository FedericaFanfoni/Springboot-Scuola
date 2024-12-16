package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)

public class DocenteDTO {

    private Integer id;
    private String nome;
    private String cognome;
    private List<CorsoDTO> corsiDTO;

    public DocenteDTO() {}

    public DocenteDTO(Integer id, String nome, String cognome) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
    }

    public DocenteDTO(Integer id, String nome, String cognome, List<CorsoDTO> corsiDTO) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.corsiDTO = corsiDTO;
    }

    public Integer getId() {
        return this.id;
    }
    public String getNome() {
        return this.nome;
    }
    public String getCognome() {
        return this.cognome;
    }
    public List<CorsoDTO> getCorsiDTO() {
        return this.corsiDTO;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public void setCorsiDTO(List<CorsoDTO> corsiDTO) {
        this.corsiDTO = corsiDTO;
    }

}
