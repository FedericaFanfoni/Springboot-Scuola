package com.example.demo.utils;

import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Discente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {CorsoMapper.class})
public interface DiscenteMapper {

    DiscenteMapper INSTANCE = Mappers.getMapper(DiscenteMapper.class);

    @Mapping(target = "corsiDTO", source = "corsi")
    DiscenteDTO toDTO(Discente discente);

    @Mapping(target = "corsi", source = "corsiDTO")
    Discente toEntity(DiscenteDTO discenteDTO);
}
