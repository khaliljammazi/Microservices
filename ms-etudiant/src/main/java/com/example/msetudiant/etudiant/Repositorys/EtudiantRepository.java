package com.example.msetudiant.etudiant.Repositorys;

import com.example.msetudiant.etudiant.Model.Etudiant;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface EtudiantRepository extends MongoRepository<Etudiant, String> {
}
