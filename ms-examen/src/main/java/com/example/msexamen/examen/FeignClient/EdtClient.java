package com.example.msexamen.examen.FeignClient;

import com.example.msexamen.examen.DTO.EtudiantDTO;
import com.example.msexamen.examen.FeignClient.FeignConfig;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MS-Etudiant", configuration = FeignConfig.class)
public interface EdtClient {

    @GetMapping("/etudiants/{id}")
    @CircuitBreaker(name="ms-etudiant",fallbackMethod ="fallbackgetEdtById")
    EtudiantDTO getEdtById(@PathVariable("id") String id);



}
