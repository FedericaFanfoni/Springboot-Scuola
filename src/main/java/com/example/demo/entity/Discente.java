package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "discenti")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Discente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cognome;
    @Column(name = "data_nascita")
    private LocalDate dataNascita;
    private String matricola;
    @ManyToMany(mappedBy = "discenti", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Corso> corsi;

}
