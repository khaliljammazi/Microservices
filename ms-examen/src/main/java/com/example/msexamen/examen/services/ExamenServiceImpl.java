package com.example.msexamen.examen.services;

import com.example.msexamen.examen.Models.Examen;
import com.example.msexamen.examen.DTO.EtudiantDTO;
import com.example.msexamen.examen.DTO.ExamenDTO;
import com.example.msexamen.examen.FeignClient.EdtClient;
import com.example.msexamen.examen.Mappers.ExamenMapper;
import com.example.msexamen.examen.Repositorys.ExamenRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class ExamenServiceImpl implements IExamenService {
    private ExamenRepository examenRepository;
    private EdtClient edtClient;
    private ExamenMapper examenMapper;
    private RestTemplate restTemplate;
    private static final String ETUDIANT_SERVICE_URL = "http://MS-Etudiant/etudiants/";

    public ExamenDTO getExamenById(Long id) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String token = jwt.getTokenValue();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return examenRepository.findById(id).map(examen -> {
            ResponseEntity<EtudiantDTO> response = restTemplate.exchange(
                    ETUDIANT_SERVICE_URL + examen.getEtudiantId(),
                    HttpMethod.GET,
                    entity,
                    EtudiantDTO.class
            );
            EtudiantDTO etudiantDTO = response.getBody();
            ExamenDTO examenDTO = examenMapper.toDto(examen);
            return new ExamenDTO(examenDTO.examenId(),  examenDTO.designation(), examenDTO.note(), examenDTO.etudiantId(), etudiantDTO);
        }).orElseThrow(() -> new IllegalArgumentException("Etudiant not found"));
        }

    public ExamenDTO saveExamen(ExamenDTO examenDTO) {
        EtudiantDTO etudiantDTO = edtClient.getEdtById(examenDTO.etudiantId());
        if(etudiantDTO != null) {
            Examen examen = examenMapper.toEntity(examenDTO);
            examenRepository.save(examen);
            return new ExamenDTO(examenDTO.examenId(), examenDTO.designation(), examenDTO.note(), examenDTO.etudiantId(), etudiantDTO);
        }
        else throw new IllegalArgumentException("Examen not found");
    }

    public List<ExamenDTO> findAllExamens() {
        return examenRepository.findAll().stream()
                .map(examen -> {
                    ExamenDTO examenDTO = examenMapper.toDto(examen);
                    EtudiantDTO etudiantDTO = edtClient.getEdtById(examenDTO.etudiantId());
                    return new ExamenDTO(examenDTO.examenId(), examenDTO.designation(), examenDTO.note(), examenDTO.etudiantId(), etudiantDTO);
                })
                .collect(Collectors.toList());
    }

    public void deleteExamenById(Long id) {
        examenRepository.deleteById(id);
    }

    @KafkaListener(topics = "etudiant-topic", groupId = "group_id")
    public void consumeEtudiant(String idEtudiant) {
       examenRepository.findAllByetudiantId(idEtudiant).forEach(
               examen -> deleteExamenById(examen.getId())
       );
        log.info("Consumed event: {}", idEtudiant);
    }

}

