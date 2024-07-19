package com.example.msexamen.examen.Controllers;

import com.example.msexamen.examen.DTO.ExamenDTO;
import com.example.msexamen.examen.services.IExamenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/examens")
@Slf4j
@RequiredArgsConstructor
@RefreshScope
public class ExamenController {
    private final IExamenService examenservice;

    @GetMapping
    public List<ExamenDTO> getAllExamens() {
        return examenservice.findAllExamens();
    }

    @GetMapping("/{id}")
    public ExamenDTO getExamenById(@PathVariable Long id)
    {return examenservice.getExamenById(id);}

    @PostMapping
    public ExamenDTO createExamen(@RequestBody ExamenDTO examenDTO) {
        return examenservice.saveExamen(examenDTO);
    }

    @PutMapping("/{id}")
    public ExamenDTO updateExamen( @RequestBody ExamenDTO examenDTO) {
        return examenservice.saveExamen(examenDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteExamen(@PathVariable Long id) {
        examenservice.deleteExamenById(id);
        }

}
