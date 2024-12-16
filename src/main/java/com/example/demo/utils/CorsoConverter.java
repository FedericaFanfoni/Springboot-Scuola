package com.example.demo.utils;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.entity.Corso;

import java.util.ArrayList;
import java.util.List;

public class CorsoConverter {

    public static Corso convertToCorso(CorsoDTO corsoDTO) {

        Corso corso = new Corso();
        corso.setNomeCorso(corsoDTO.getNomeCorso());
        corso.setDataInizio(corsoDTO.getDataInizio());
        corso.setDurata(corsoDTO.getDurata());

        return corso;
    }
    public static List<CorsoDTO> convertToCorsiDTO(List<Corso> corsi) {
        List<CorsoDTO> corsiDTO = new ArrayList<>();

        for(Corso corso : corsi) {
            CorsoDTO corsoDTO = new CorsoDTO(
                    corso.getId(),
                    corso.getNomeCorso(),
                    corso.getDataInizio(),
                    corso.getDurata()
            );
            corsiDTO.add(corsoDTO);
        }

        return corsiDTO;
    }
    public static List<Corso> convertToCorsi(List<CorsoDTO> corsiDTO) {

        List<Corso> corsi = new ArrayList<>();

        for(CorsoDTO corsoDTO : corsiDTO) {
            Corso corso = convertToCorso(corsoDTO);
            corsi.add(corso);
        }

        return corsi;
    }
    public static CorsoDTO convertToCorsoDTOWithDocenteDTO(Corso corso) {
        return new CorsoDTO(
                corso.getId(),
                corso.getNomeCorso(),
                corso.getDataInizio(),
                corso.getDurata(),
                DocenteConverter.convertToDocenteDTO(corso.getDocente())
        );
    }
    public static List<CorsoDTO> convertToCorsiDTOWithDocenteDTO(List<Corso> corsi) {
        List<CorsoDTO> corsiDTO = new ArrayList<>();

        for(Corso corso : corsi) {
            CorsoDTO corsoDTO = new CorsoDTO(
                    corso.getId(),
                    corso.getNomeCorso(),
                    corso.getDataInizio(),
                    corso.getDurata(),
                    DocenteConverter.convertToDocenteDTOWithoutCorsiDTO(corso.getDocente()),
                    DiscenteConverter.convertToDiscentiDTOWithoutCorsiDTO(corso.getDiscenti())
            );

            corsiDTO.add(corsoDTO);
        }

        return corsiDTO;
    }
}
