package com.example.demo.service;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import com.example.demo.utils.DiscenteMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiscenteService {

    private final DiscenteRepository discenteRepository;
    private final CorsoRepository corsoRepository;
    private final DiscenteMapper discenteMapper;

    public List<DiscenteDTO> findAll() {
        return discenteRepository.findAll().stream()
                .map(discenteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DiscenteDTO findById(Integer id) {
        Discente discente = discenteRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Discente Not Found"));
        return discenteMapper.toDTO(discente);
    }

    public List<DiscenteDTO> filterForCorso(Integer idCorso) {
        return discenteRepository.filterForCorso(idCorso).stream()
                .map(discenteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public DiscenteDTO insert(DiscenteDTO discenteDTO) {
        Discente discente = discenteMapper.toEntity(discenteDTO);
        if(!discenteDTO.getCorsiDTO().isEmpty()){
            for(CorsoDTO corsoDTO : discenteDTO.getCorsiDTO()){
             Corso corso =  corsoRepository.findById(corsoDTO.getId()).orElseThrow(() ->
                        new EntityNotFoundException("Corso Not Found"));
             corso.getDiscenti().add(discente);
            }
        }
        discenteRepository.save(discente);
        return discenteMapper.toDTO(discente);
    }

    @Transactional
    public DiscenteDTO update(Integer id, DiscenteDTO discenteDTO) {
        Discente discente = discenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Discente Not Found"));

        discente.setNome(discenteDTO.getNome());
        discente.setCognome(discenteDTO.getCognome());
        discente.setDataNascita(discenteDTO.getDataNascita());
        discente.setMatricola(discenteDTO.getMatricola());

        List<Corso> corsi = new ArrayList<>();

        for (Corso corso : discente.getCorsi()) {
            corso.getDiscenti().remove(discente);
        }
        for(CorsoDTO corsoDTO : discenteDTO.getCorsiDTO()){
            Corso corso = corsoRepository.findById(corsoDTO.getId()).orElseThrow();
            corsi.add(corso);
        }

        discente.getCorsi().clear();
        discente.getCorsi().addAll(corsi);

        for (Corso corso : discente.getCorsi()) {
            corso.getDiscenti().add(discente);
        }

        discenteRepository.save(discente);
        return discenteMapper.toDTO(discente);
    }

    @Transactional
    public DiscenteDTO delete(Integer id) {
        Discente discente = discenteRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Discente Not Found"));

        for (Corso corso : discente.getCorsi()) {
            corso.getDiscenti().remove(discente);
        }

        DiscenteDTO discenteDTO = discenteMapper.toDTO(discente);
        discenteRepository.delete(discente);
        return discenteDTO;
    }

    @Transactional
    public DiscenteDTO removeCorso(Integer idDiscente, CorsoDTO corsoDTO) {
        Discente discente = discenteRepository.findById(idDiscente).orElseThrow(() ->
                new EntityNotFoundException("Discente Not Found"));
        Corso corso = corsoRepository.findById(corsoDTO.getId()).orElseThrow(() ->
                new EntityNotFoundException("Corso Not Found"));

        if (discente.getCorsi().contains(corso)) {
            discente.getCorsi().remove(corso);
            corso.getDiscenti().remove(discente);
            discenteRepository.save(discente);
            return discenteMapper.toDTO(discente);
        }
        else { throw new EntityNotFoundException("Corso non trovato"); }
    }

    @Transactional
    public DiscenteDTO addCorso(Integer idDiscente, CorsoDTO corsoDTO) {
        Discente discente = discenteRepository.findById(idDiscente).orElseThrow(() ->
                new EntityNotFoundException("Discente Not Found"));
        Corso corso = corsoRepository.findById(corsoDTO.getId()).orElseThrow(() ->
                new EntityNotFoundException("Corso Not Found"));

        if (!discente.getCorsi().contains(corso)) {
            discente.getCorsi().add(corso);
            corso.getDiscenti().add(discente);
            discenteRepository.save(discente);
            return discenteMapper.toDTO(discente);
        }
        else { throw new EntityNotFoundException("Corso già presente"); }
    }
}
