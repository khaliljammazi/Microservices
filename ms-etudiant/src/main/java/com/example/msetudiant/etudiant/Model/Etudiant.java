package com.example.msetudiant.etudiant.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "etudiant")
public class Etudiant {
    @Id
    private String id;
    private String nom;
    private  String prenom;
    private String cin;


}

