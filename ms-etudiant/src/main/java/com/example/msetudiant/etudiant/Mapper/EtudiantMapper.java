package com.example.msetudiant.etudiant.Mapper;

import com.example.msetudiant.etudiant.DTO.EtudiantDTO;
import com.example.msetudiant.etudiant.Model.Etudiant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EtudiantMapper {
    EtudiantMapper INSTANCE = Mappers.getMapper(EtudiantMapper.class);
    @Mapping(target = "etudiantId", source = "id")
    EtudiantDTO toDto(Etudiant stock);
    @Mapping(target = "id", source = "etudiantId")
    Etudiant toEntity(EtudiantDTO etudiantDTO);
}
