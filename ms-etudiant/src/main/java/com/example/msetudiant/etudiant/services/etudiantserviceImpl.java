package com.example.msetudiant.etudiant.services;

import com.example.msetudiant.etudiant.DTO.EtudiantDTO;
import com.example.msetudiant.etudiant.Model.Etudiant;
import com.example.msetudiant.etudiant.Repositorys.EtudiantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class etudiantserviceImpl implements Ietudiantservice {
    private EtudiantRepository repository;
    private KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "etudiant-topic";

    public List<EtudiantDTO> findAlletudiants() {
        return repository.findAll().stream()
                .map(EtudiantDTO::mapFromEntity)
                .collect(Collectors.toList());
    }

    public EtudiantDTO getEtudiantById(String etudiantId) {
        Etudiant etudiant = repository.findById(etudiantId).orElse(null);
        return EtudiantDTO.mapFromEntity(etudiant);
    }

    public EtudiantDTO saveEtudiant(EtudiantDTO etudiantDTO) {
        Etudiant etudiant = EtudiantDTO.mapToEntity(etudiantDTO);
        etudiant = repository.save(etudiant);
        return EtudiantDTO.mapFromEntity(etudiant);
    }

    public void deleteEtudiantById(String etudiantId) {
        repository.deleteById(etudiantId);
        sendEtudiant(etudiantId);
    }

    public void sendEtudiant(String etudiantId) {
        kafkaTemplate.send(TOPIC, etudiantId);
        log.info("Produced event: {}", etudiantId);
    }
}

