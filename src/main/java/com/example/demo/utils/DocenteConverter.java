package com.example.demo.utils;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Docente;

import java.util.ArrayList;
import java.util.List;

public class DocenteConverter {

    public static Docente convertToDocente(DocenteDTO docenteDTO) {

        Docente docente = new Docente();
        docente.setNome(docenteDTO.getNome());
        docente.setCognome(docenteDTO.getCognome());

        if(docenteDTO.getCorsiDTO() != null) {
            docente.setCorsi(CorsoConverter.convertToCorsi(docenteDTO.getCorsiDTO()));

        }
        else {
            docente.setCorsi(new ArrayList<>());
        }

        return docente;

    }
    public static DocenteDTO convertToDocenteDTO(Docente docente) {

        List<CorsoDTO> corsiDTO = CorsoConverter.convertToCorsiDTO(docente.getCorsi());

        return new DocenteDTO(
                docente.getId(),
                docente.getNome(),
                docente.getCognome(),
                corsiDTO
        );
    }

    public static DocenteDTO convertToDocenteDTOWithoutCorsiDTO(Docente docente){
        return new DocenteDTO(
                docente.getId(),
                docente.getNome(),
                docente.getCognome()
        );
    }
}
