package com.example.msexamen.examen.Mappers;

import com.example.msexamen.examen.Models.Examen;
import com.example.msexamen.examen.DTO.ExamenDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface ExamenMapper {
    ExamenMapper INSTANCE = Mappers.getMapper(ExamenMapper.class);
    @Mapping(source = "id", target = "examenId")
    ExamenDTO toDto(Examen examen);
    @Mapping(source = "examenId", target = "id")
    Examen toEntity(ExamenDTO examenDTO);

}
