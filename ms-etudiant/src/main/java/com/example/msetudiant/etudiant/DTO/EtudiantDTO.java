package com.example.msetudiant.etudiant.DTO;

import com.example.msetudiant.etudiant.Mapper.EtudiantMapper;
import com.example.msetudiant.etudiant.Model.Etudiant;
import lombok.Builder;

@Builder
public record EtudiantDTO(String etudiantId, String nom, String prenom, String cin) {

    public static EtudiantDTO mapFromEntity(Etudiant etudiant) {
        return EtudiantMapper.INSTANCE.toDto(etudiant);
    }

    public static Etudiant mapToEntity(EtudiantDTO etudiantDTO) {
        return EtudiantMapper.INSTANCE.toEntity(etudiantDTO);
    }

}
