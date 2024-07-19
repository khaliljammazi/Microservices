package com.example.msexamen.examen.DTO;

import lombok.Builder;

@Builder
public record EtudiantDTO(String etudiantId, String nom, String prenom, String cin) {
}