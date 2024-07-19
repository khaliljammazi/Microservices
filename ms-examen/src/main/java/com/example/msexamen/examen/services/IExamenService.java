package com.example.msexamen.examen.services;



import com.example.msexamen.examen.DTO.ExamenDTO;

import java.util.List;

public interface IExamenService {
    ExamenDTO getExamenById(Long examenId);
    ExamenDTO saveExamen(ExamenDTO examenDTO);
    List<ExamenDTO> findAllExamens();
    void deleteExamenById(Long examenId);
    void consumeEtudiant(String message);
}

