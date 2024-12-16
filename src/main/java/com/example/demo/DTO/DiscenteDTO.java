package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiscenteDTO {

    private Integer id;
    private String nome;
    private String cognome;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascita;
    private String matricola;
    private List<CorsoDTO> corsiDTO;

    public DiscenteDTO() {}

    public DiscenteDTO(Integer id, String nome, String cognome, LocalDate dataNascita, String matricola) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.matricola = matricola;
    }

    public DiscenteDTO(Integer id, String nome, String cognome, LocalDate dataNascita, String matricola, List<CorsoDTO> corsiDTO) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.matricola = matricola;
        this.corsiDTO = corsiDTO;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCognome() {
        return this.cognome;
    }

    public LocalDate getDataNascita(){
        return this.dataNascita;
    }

    public String getMatricola(){
        return this.matricola;
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

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public void setMatricola(String matricola){
        this.matricola = matricola;
    }

    public void setCorsiDTO(List<CorsoDTO> corsiDTO) {
        this.corsiDTO = corsiDTO;
    }

}
