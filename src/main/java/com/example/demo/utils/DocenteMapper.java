package com.example.demo.utils;

import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Docente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DocenteMapper {
    DocenteMapper INSTANCE = Mappers.getMapper(DocenteMapper.class);

    @Mapping(target = "corsiDTO", source = "corsi")
    DocenteDTO toDTO(Docente docente);

    @Mapping(target = "corsi", source = "corsiDTO")
    Docente toEntity(DocenteDTO docenteDTO);

}
