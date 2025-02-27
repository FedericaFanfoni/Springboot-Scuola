package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocenteDTO {

    private Integer id;
    private String nome;
    private String cognome;
    private List<CorsoDTO> corsiDTO;

}
