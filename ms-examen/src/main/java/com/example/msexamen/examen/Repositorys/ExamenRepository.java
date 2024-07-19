package com.example.msexamen.examen.Repositorys;

import com.example.msexamen.examen.Models.Examen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamenRepository extends JpaRepository<Examen, Long> {

    List<Examen> findAllByetudiantId(String etudiantId);
}
