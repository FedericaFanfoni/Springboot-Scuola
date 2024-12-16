package com.example.demo.service;

import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import com.example.demo.utils.DiscenteConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscenteService {

    private final DiscenteRepository discenteRepository;
    private final CorsoRepository corsoRepository;
   // @PersistenceContext
    private EntityManager entityManager;

    public DiscenteService(
            DiscenteRepository discenteRepository,
            CorsoRepository corsoRepository,
            EntityManager entityManager) {
        this.discenteRepository = discenteRepository;
        this.corsoRepository = corsoRepository;
       // this.entityManager = entityManager;
    }

    public List<DiscenteDTO> findAll() {
        return DiscenteConverter.convertToDiscentiDTOWithCorsiDTO(discenteRepository.findAll());
    }
    public DiscenteDTO getDiscenteById(Integer id) {
        Optional<Discente> discente = discenteRepository.findById(id);

        if (discente.isPresent()) {
            return DiscenteConverter.convertToDTOWithCorsiDTO(discente.get());
        }
        else {
            throw new EntityNotFoundException("Discente not found");
        }
    }
    public DiscenteDTO insertDiscente(DiscenteDTO discenteDTO) {
        Discente discente = DiscenteConverter.convertToDiscente(discenteDTO);
        discenteRepository.save(discente);
        return DiscenteConverter.convertToDTOWithCorsiDTO(discente);
    }
    public DiscenteDTO updateDiscente(Integer id, DiscenteDTO discenteDTO) {
        Optional<Discente> discente = discenteRepository.findById(id);
        if(discente.isPresent()){
            discente.get().setNome(discenteDTO.getNome());
            discente.get().setCognome(discenteDTO.getCognome());
            discente.get().setDataNascita(discenteDTO.getDataNascita());
            discente.get().setMatricola(discenteDTO.getMatricola());
            discenteRepository.save(discente.get());
            return DiscenteConverter.convertToDTOWithCorsiDTO(discente.get());
        }
        else {
            throw  new EntityNotFoundException();
        }

    }
    @Transactional // Assicura che i metodi siano eseguiti all’interno di una transazione
    public DiscenteDTO deleteDiscente(Integer id) {
    Optional<Discente> discente = discenteRepository.findById(id);
    if (discente.isPresent()) {
        DiscenteDTO discenteDTO = DiscenteConverter.convertToDTOWithCorsiDTO(discente.get());

        for (Corso corso : discente.get().getCorsi()) {
            corso.getDiscenti().remove(discente.get());
            //entityManager.merge(corso);
        }
        discente.get().getCorsi().clear();

        discenteRepository.deleteById(id);
        return discenteDTO;
    }
    else {
        throw new EntityNotFoundException("Discente not found");
    }
}
    @Transactional
    public DiscenteDTO addCorso(Integer idCorso, Integer idDiscente) {
        Optional<Corso> corso = corsoRepository.findById(idCorso);
        Optional<Discente> discente = discenteRepository.findById(idDiscente);
        if(corso.isPresent() && discente.isPresent()){
            if(!discente.get().getCorsi().contains(corso.get())){
                discente.get().getCorsi().add(corso.get());
                corso.get().getDiscenti().add(discente.get());
                discenteRepository.save(discente.get());
                return DiscenteConverter.convertToDTOWithCorsiDTO(discente.get());
            }
            else {
                throw  new EntityNotFoundException("Corso già presente");
            }
        }
        else {
            throw new EntityNotFoundException("Corso not found");
        }
    }
}
