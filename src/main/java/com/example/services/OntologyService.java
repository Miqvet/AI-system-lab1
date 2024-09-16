package com.example.services;


import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

import java.io.InputStream;

public class OntologyService {
    private final Model model;

    public OntologyService(String ontologyFilePath) {
        // Загружаем онтологию
        model = ModelFactory.createOntologyModel();
        InputStream in = FileManager.get().open(ontologyFilePath);
        if (in == null) {
            throw new IllegalArgumentException("File not found");
        }
        model.read(in, null);
        System.out.println("The model has been successfully loaded. Total triplets: " + model.size());

        // SPARQL запрос на получение всех игр
//        String queryString =
//                "PREFIX ex: <http://www.example.org/ontologies/videogames#>" +
//        "SELECT ?videoGame" +
//        "WHERE {" +
//             "?videoGame a ex:PopularGame.}";
//
//        Query query = QueryFactory.create(queryString);
//        QueryExecution qexec = QueryExecutionFactory.create(query, model);
//
//        try {
//            ResultSet results = qexec.execSelect();
//            ResultSetFormatter.out(System.out, results, query);
//        } finally {
//            qexec.close();
//        }
    }

    public ResultSet queryOntology(String sparqlQuery) {
        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        return qe.execSelect();
    }
}