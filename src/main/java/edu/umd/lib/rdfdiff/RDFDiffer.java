package edu.umd.lib.rdfdiff;

import java.io.FileNotFoundException;
import java.io.InputStream;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

public class RDFDiffer {
    
    private String baseURI;
    
    public RDFDiffer() {
        this.baseURI = null;
    }
    
    public RDFDiffer(String baseURI) {
        this.baseURI = baseURI;
    }

    private static String inferTypeFromExtension(String filename) {
        if (filename.endsWith(".ttl")) {
            return "TURTLE";
        }
        if (filename.endsWith(".rdf") || filename.endsWith(".xml")) {
            return "RDF/XML";
        }
        if (filename.endsWith(".nt")) {
            return "N-TRIPLE";
        }
        if (filename.endsWith(".n3")) {
            return "N3";
        }
        if (filename.endsWith(".jsonld") || filename.endsWith(".json")) {
            return "JSON-LD";
        }
        throw new RuntimeException("Unknown extension");
    }
    
    public RDFDiff diff(String filenameA, String filenameB) throws FileNotFoundException {
        String typeA = inferTypeFromExtension(filenameA);
        InputStream inA = FileManager.get().open(filenameA);
        if (inA == null) {
            throw new FileNotFoundException();
        }
        
        String typeB = inferTypeFromExtension(filenameB);
        InputStream inB = FileManager.get().open(filenameB);
        if (inB == null) {
            throw new FileNotFoundException();
        }
        
        Model modelA = ModelFactory.createDefaultModel();
        modelA.read(inA, baseURI, typeA);
        Model modelB = ModelFactory.createDefaultModel();
        modelB.read(inB, baseURI, typeB);
        
        return diff(modelA, modelB);
    }
    
    public RDFDiff diff(Model modelA, Model modelB) {
        return new RDFDiff(modelA.difference(modelB), modelB.difference(modelA));
    }
}
