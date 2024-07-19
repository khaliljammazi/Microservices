package com.example.msetudiant.etudiant.Controllers;

import com.example.msetudiant.etudiant.DTO.EtudiantDTO;
import com.example.msetudiant.etudiant.services.Ietudiantservice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiants")
@Slf4j
@RequiredArgsConstructor
@RefreshScope
public class EtudiantController {
    private final Ietudiantservice etudiantservice;

    @GetMapping
    public List<EtudiantDTO> getAlletudiants() {
        return etudiantservice.findAlletudiants();
    }

    @GetMapping("/{id}")
    public EtudiantDTO getEdtById(@PathVariable String id) {
        return etudiantservice.getEtudiantById(id);
    }

    @PostMapping
    public EtudiantDTO createEdt(@RequestBody EtudiantDTO etudiantDTO) {
        return etudiantservice.saveEtudiant(etudiantDTO);
    }

    @PutMapping("/{id}")
    public EtudiantDTO updateEdt(@PathVariable String id, @RequestBody EtudiantDTO etudiantDTO) {return etudiantservice.saveEtudiant(etudiantDTO);}

    @DeleteMapping("/{id}")
    public void deleteEdt(@PathVariable String id) {
        etudiantservice.deleteEtudiantById(id);
    }

}
