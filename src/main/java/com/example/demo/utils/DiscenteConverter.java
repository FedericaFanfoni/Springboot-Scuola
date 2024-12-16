package com.example.demo.utils;

import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Discente;

import java.util.ArrayList;
import java.util.List;

public class DiscenteConverter {

    public static Discente convertToDiscente(DiscenteDTO discenteDTO) {
        Discente discente = new Discente();
        discente.setNome(discenteDTO.getNome());
        discente.setCognome(discenteDTO.getCognome());
        discente.setDataNascita(discenteDTO.getDataNascita());
        discente.setMatricola(discenteDTO.getMatricola());

        if(discenteDTO.getCorsiDTO() != null){
            discente.setCorsi(CorsoConverter.convertToCorsi(discenteDTO.getCorsiDTO()));
        }
        else {
            discente.setCorsi(new ArrayList<>());
        }

        return discente;
    }
    public static DiscenteDTO convertToDTOWithCorsiDTO(Discente discente){
        return new DiscenteDTO(
                discente.getId(),
                discente.getNome(),
                discente.getCognome(),
                discente.getDataNascita(),
                discente.getMatricola(),
                CorsoConverter.convertToCorsiDTOWithDocenteDTO(discente.getCorsi())
        );
    }
    public static DiscenteDTO convertToDTOWithoutCorsiDTO(Discente discente){
        return new DiscenteDTO(
                discente.getId(),
                discente.getNome(),
                discente.getCognome(),
                discente.getDataNascita(),
                discente.getMatricola()
        );
    }

    public static List<DiscenteDTO> convertToDiscentiDTOWithCorsiDTO(List<Discente> discentes){
        List<DiscenteDTO> discentiDTO = new ArrayList<>();
        for(Discente discente : discentes){
            discentiDTO.add(convertToDTOWithCorsiDTO(discente));
        }
        return discentiDTO;
    }

    public static List<DiscenteDTO> convertToDiscentiDTOWithoutCorsiDTO(List<Discente> discentes){
        List<DiscenteDTO> discentiDTO = new ArrayList<>();
        for(Discente discente : discentes){
            discentiDTO.add(convertToDTOWithoutCorsiDTO(discente));
        }
        return discentiDTO;
    }


}
