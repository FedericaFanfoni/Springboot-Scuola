package com.example.demo.service;

import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Docente;
import com.example.demo.repository.DocenteRepository;
import com.example.demo.utils.CorsoConverter;
import com.example.demo.utils.DocenteConverter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DocenteService {

    private final DocenteRepository docenteRepository;

    public DocenteService(DocenteRepository docenteRepository) {
        this.docenteRepository = docenteRepository;
    }

    public List<DocenteDTO> findAll() {

        List<Docente> docenti = docenteRepository.findAll();
        List<DocenteDTO> docentiDTO = new ArrayList<>();

        for(Docente docente : docenti) {
            docentiDTO.add(DocenteConverter.convertToDocenteDTO(docente));
        }
        return docentiDTO;
    }
    public DocenteDTO getDocentById(Integer id) {

      Optional<Docente> docente = docenteRepository.findById(id);

      if(docente.isPresent()) {
          return DocenteConverter.convertToDocenteDTO(docente.get());
      }
      else {
          throw new EntityNotFoundException("Docente Not Found");
      }
    }
    public DocenteDTO insertDocente(DocenteDTO docenteDTO){
        Docente docente = DocenteConverter.convertToDocente(docenteDTO);
        docenteRepository.save(docente);
        return DocenteConverter.convertToDocenteDTO(docente);
    }
    public DocenteDTO updateDocente(Integer id, DocenteDTO docenteDTO){
        Optional<Docente> docente = docenteRepository.findById(id);
        if(docente.isPresent()) {
            docente.get().setNome(docenteDTO.getNome());
            docente.get().setCognome(docenteDTO.getCognome());
            docente.get().setCorsi(CorsoConverter.convertToCorsi(docenteDTO.getCorsiDTO()));
            docenteRepository.save(docente.get());
            return DocenteConverter.convertToDocenteDTO(docente.get());
        }
        else {
            throw new EntityNotFoundException("Docente Not Found");
        }
    }
    public DocenteDTO deleteDocente(Integer id) {

        Optional<Docente> docente = docenteRepository.findById(id);

        if(docente.isPresent()) {
            DocenteDTO docenteDTO = DocenteConverter.convertToDocenteDTO(docente.get());
            docenteRepository.deleteById(id);
            return docenteDTO;
        }
        else {
            throw new EntityNotFoundException("Docente Not Found");
        }
    }


}
