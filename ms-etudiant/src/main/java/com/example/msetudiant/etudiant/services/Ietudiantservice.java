package com.example.msetudiant.etudiant.services;


import com.example.msetudiant.etudiant.DTO.EtudiantDTO;

import java.util.List;

public interface Ietudiantservice {
    List<EtudiantDTO> findAlletudiants();
    EtudiantDTO getEtudiantById(String etudiantId);
    EtudiantDTO saveEtudiant(EtudiantDTO etudiantDTO);
    void deleteEtudiantById(String etudiantId);
    void sendEtudiant(String etudiantId);
}

