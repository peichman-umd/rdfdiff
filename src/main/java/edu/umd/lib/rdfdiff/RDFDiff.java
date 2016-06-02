package edu.umd.lib.rdfdiff;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import com.hp.hpl.jena.rdf.model.Model;

public class RDFDiff {

    private Model deleted;
    private Model inserted;
    
    public RDFDiff(Model deleted, Model inserted) {
        this.deleted = deleted;
        this.inserted = inserted;
    }

    public Model getDeleted() {
        return deleted;
    }
    
    public Model getInserted() {
        return inserted;
    }
    
    public String toSPARQLUpdate() throws UnsupportedEncodingException {
        StringBuffer b = new StringBuffer();
        
        ByteArrayOutputStream deletedStream = new ByteArrayOutputStream();
        deleted.write(deletedStream, "TURTLE");
        
        ByteArrayOutputStream insertedStream = new ByteArrayOutputStream();
        inserted.write(insertedStream, "TURTLE");
        
        b.append("DELETE {\n").append(deletedStream.toString("UTF-8")).append("}\n");
        b.append("INSERT {\n").append(insertedStream.toString("UTF-8")).append("}\n");
        b.append("WHERE {}\n");

        return b.toString();
    }
}
