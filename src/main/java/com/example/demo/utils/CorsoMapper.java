package com.example.demo.utils;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.entity.Corso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CorsoMapper {
    CorsoMapper INSTANCE = Mappers.getMapper(CorsoMapper.class);

    @Mapping(target = "docenteDTO", source = "docente")
    @Mapping(target = "discentiDTO", source = "discenti")
    CorsoDTO toDTO(Corso corso);

    @Mapping(target = "docente", source = "docenteDTO")
    @Mapping(target = "discenti", source = "discentiDTO")
    Corso toEntity(CorsoDTO corsoDTO);
}
