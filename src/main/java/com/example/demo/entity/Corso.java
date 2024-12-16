package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "corsi")
public class Corso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nome_corso")
    private String nomeCorso;
    @Column(name = "data_inizio")
    private LocalDate dataInizio;
    private Integer durata;
    @ManyToOne
    @JoinColumn(name = "id_docente")
    private Docente docente;
    @ManyToMany
    @JoinTable(
            name = "corsidiscenti",
            joinColumns = @JoinColumn(name = "id_corso"),
            inverseJoinColumns = @JoinColumn(name = "id_discente"))
    private List<Discente> discenti;

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

    public Docente getDocente(){
        return  this.docente;
    }

    public List<Discente> getDiscenti(){
        return this.discenti;
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

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public void setDiscenti(List<Discente> discenti){
        this.discenti = discenti;
    }

}
