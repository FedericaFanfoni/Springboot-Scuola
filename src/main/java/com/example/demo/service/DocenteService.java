package com.example.demo.service;

import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DocenteRepository;
import com.example.demo.utils.CorsoMapper;
import com.example.demo.utils.DocenteMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocenteService {

    private final DocenteRepository docenteRepository;
    private final CorsoRepository corsoRepository;
    private final DocenteMapper docenteMapper;
    private final CorsoMapper corsoMapper;

    public List<DocenteDTO> findAll() {
        return docenteRepository.findAll().stream()
                .map(docenteMapper::toDTO)
                .collect(Collectors.toList());
    }
    public DocenteDTO findById(Integer id) {
      Docente docente = docenteRepository.findById(id).orElseThrow(() ->
              new EntityNotFoundException("Docente Not Found"));
      return docenteMapper.toDTO(docente);
    }
    public DocenteDTO insert(DocenteDTO docenteDTO){
        Docente docente = docenteMapper.toEntity(docenteDTO);
        docenteRepository.save(docente);
        return docenteMapper.toDTO(docente);
    }
    @Transactional
    public DocenteDTO update(Integer id, DocenteDTO docenteDTO) {
        Docente docente = docenteRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Docente Not Found"));

        docente.setNome(docenteDTO.getNome());
        docente.setCognome(docenteDTO.getCognome());
        docente.getCorsi().clear();

        List<Corso> corsi = docenteDTO.getCorsiDTO().stream()
                .map(corso ->
                        corsoRepository.findById(corso.getId())
                                .orElseThrow(() -> new EntityNotFoundException("Corso Not Found")))
                .toList();

        for(Corso corso : corsi){
            corso.setDocente(docente);
            docente.getCorsi().add(corso);
        }

        docenteRepository.save(docente);
        return docenteMapper.toDTO(docente);
    }
    public DocenteDTO delete(Integer id) {
        Docente docente = docenteRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Docente Not Found"));

        DocenteDTO docenteDTO = docenteMapper.toDTO(docente);
        docenteRepository.delete(docente);
        return docenteDTO;
    }

}
