package com.example.msexamen.examen.DTO;

import com.example.msexamen.examen.DTO.EtudiantDTO;
import lombok.Builder;

@Builder
public record ExamenDTO(Long examenId,String designation ,Float note, String etudiantId, EtudiantDTO etudiantDTO) {


}
