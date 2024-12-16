package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "discenti")
public class Discente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cognome;
    @Column(name = "data_nascita")
    private LocalDate dataNascita;
    private String matricola;
    @ManyToMany(mappedBy = "discenti")
    private List<Corso> corsi;

    /*public Discente() {}

    public Discente(String nome, String cognome, LocalDate dataNascita, String matricola) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.matricola = matricola;
    }

    public Discente(Integer id, String nome, String cognome, LocalDate dataNascita, String matricola, List<Corso> corsi) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.matricola = matricola;
        this.corsi = corsi;
    }*/

    public Integer getId() {
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

    public List<Corso> getCorsi(){
        return this.corsi;
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

    public void setCorsi(List<Corso> corsi){
        this.corsi =  corsi;
    }


}
