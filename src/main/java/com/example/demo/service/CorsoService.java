package com.example.demo.service;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import com.example.demo.repository.DocenteRepository;
import com.example.demo.utils.CorsoMapper;
import com.example.demo.utils.DiscenteMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CorsoService {

    private final CorsoRepository corsoRepository;
    private final DocenteRepository docenteRepository;
    private final DiscenteRepository discenteRepository;
    private final CorsoMapper corsoMapper;
    private final DiscenteMapper discenteMapper;

    public List<CorsoDTO> findAll() {
        return corsoRepository.findAll().stream()
                .map(corsoMapper::toDTO)
                .collect(Collectors.toList());
    }
    public CorsoDTO findById(Integer id) {
        Corso corso = corsoRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Corso Not Found"));
        return corsoMapper.toDTO(corso);
    }
    public CorsoDTO insert(CorsoDTO corsoDTO, Integer idDocente ) {
        Docente docente = docenteRepository.findById(idDocente).orElseThrow(() ->
                new EntityNotFoundException("Docente Not Found"));

        Corso corso = corsoMapper.toEntity(corsoDTO);
        corso.setDocente(docente);
        corsoRepository.save(corso);
        return corsoMapper.toDTO(corso);

    }

    @Transactional
    public CorsoDTO update(Integer id, CorsoDTO corsoDTO) {
        Corso corso = corsoRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Corso Not Found"));
        Docente docente = docenteRepository.findById(corsoDTO.getDocenteDTO().getId()).orElseThrow(() ->
                new EntityNotFoundException("Corso Not Found"));

        corso.setNomeCorso(corsoDTO.getNomeCorso());
        corso.setDataInizio(corsoDTO.getDataInizio());
        corso.setDurata(corsoDTO.getDurata());
        corso.setDocente(docente);

        List<Discente> discenti = corsoDTO.getDiscentiDTO().stream()
                        .map(discenteMapper::toEntity).toList();

        corso.setDiscenti(discenti);
        corsoRepository.save(corso);
        return corsoMapper.toDTO(corso);
    }
    @Transactional
    public CorsoDTO delete(Integer id) {
        Corso corso = corsoRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Corso Not Found"));
        Docente docente = docenteRepository.findById(corso.getDocente().getId()).orElseThrow(() ->
                new EntityNotFoundException("Docente Not Found"));
        docente.getCorsi().remove(corso);
        docenteRepository.save(docente); // Salva le modifiche al docente

//        corso.getDiscenti().forEach(d -> {
//            d.getCorsi().remove(corso);
//            discenteRepository.save(d); // Salva le modifiche ai discenti
//        });

        CorsoDTO corsoDTO = corsoMapper.toDTO(corso);
        corsoRepository.delete(corso);

        return corsoDTO;
    }

    @Transactional
    public CorsoDTO addDiscente(Integer idCorso, DiscenteDTO discenteDTO){
        Corso corso = corsoRepository.findById(idCorso).orElseThrow(() ->
                new EntityNotFoundException("Corso Not Found"));
        Discente discente = discenteRepository.findByMatricola(discenteDTO.getMatricola()).orElseThrow(()->
                new EntityNotFoundException("Discente Not Found"));

        corso.getDiscenti().add(discente);
        discente.getCorsi().add(corso);

        corsoRepository.save(corso);
        return corsoMapper.toDTO(corso);

    }

    @Transactional
    public CorsoDTO removeDiscente(Integer idCorso, DiscenteDTO discenteDTO){
        Corso corso = corsoRepository.findById(idCorso).orElseThrow(() ->
                new EntityNotFoundException("Corso Not Found"));
        Discente discente = discenteRepository.findByMatricola(discenteDTO.getMatricola()).orElseThrow(()->
                new EntityNotFoundException("Discente Not Found"));

        corso.getDiscenti().remove(discente);
        discente.getCorsi().remove(corso);

        corsoRepository.save(corso);
        return corsoMapper.toDTO(corso);
    }

    public List<CorsoDTO> findForDocente(Integer idDocente) {
        return corsoRepository.findForDocente(idDocente)
                .stream()
                .map(corsoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<CorsoDTO> findForDiscente(Integer idDocente) {
        return corsoRepository.findForDiscente(idDocente)
                .stream()
                .map(corsoMapper::toDTO)
                .collect(Collectors.toList());
    }
}
