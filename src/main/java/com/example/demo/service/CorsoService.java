package com.example.demo.service;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DocenteRepository;
import com.example.demo.utils.CorsoConverter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CorsoService {

    private final CorsoRepository corsoRepository;
    private final DocenteRepository docenteRepository;

    public CorsoService(CorsoRepository corsoRepository, DocenteRepository docenteRepository) {
        this.corsoRepository = corsoRepository;
        this.docenteRepository = docenteRepository;
    }

    public List<CorsoDTO> findAll() {
        return CorsoConverter.convertToCorsiDTOWithDocenteDTO(corsoRepository.findAll());
    }
    public CorsoDTO getCorsoById(Integer id) {

        Optional<Corso> corso = corsoRepository.findById(id);

        if (corso.isPresent()){
            return CorsoConverter.convertToCorsoDTOWithDocenteDTO(corso.get());
        }
        else {
            throw  new EntityNotFoundException("Corso not found");
        }
    }
    public CorsoDTO insertCorso(CorsoDTO corsoDTO) {
            Optional<Docente> docente = docenteRepository.findById(corsoDTO.getDocenteDTO().getId());

            if(docente.isPresent()){
                Corso corso = CorsoConverter.convertToCorso(corsoDTO);
                corso.setDocente(docente.get());
                corsoRepository.save(corso);
                return CorsoConverter.convertToCorsoDTOWithDocenteDTO(corso);
            }
            else {
                throw  new EntityNotFoundException();
            }

    }
    public CorsoDTO updateCorso(Integer id, CorsoDTO corsoDTO) {
        Optional<Corso> corso = corsoRepository.findById(id);
        if(corso.isPresent()){
            Optional<Docente> docente = docenteRepository.findById(corsoDTO.getDocenteDTO().getId());
            if(docente.isPresent()){
                corso.get().setNomeCorso(corsoDTO.getNomeCorso());
                corso.get().setDataInizio(corsoDTO.getDataInizio());
                corso.get().setDurata(corsoDTO.getDurata());
                corso.get().setDocente(docente.get());
                corsoRepository.save(corso.get());
                return CorsoConverter.convertToCorsoDTOWithDocenteDTO(corso.get());
            }
            else {
                throw  new EntityNotFoundException();
            }
        }
        else {
            throw  new EntityNotFoundException();
        }

    }
    public CorsoDTO deleteCorso(Integer id) {
        Optional<Corso> corso = corsoRepository.findById(id);
        if(corso.isPresent()){
            CorsoDTO corsoDTO = CorsoConverter.convertToCorsoDTOWithDocenteDTO(corso.get());
            corsoRepository.deleteById(id);
            return corsoDTO;
        }
        else {
            throw  new EntityNotFoundException();
        }
    }
}
