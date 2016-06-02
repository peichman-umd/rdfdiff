package edu.umd.lib.rdfdiff;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;


/**
 * RDF Differ
 *
 */
public class App {

    /**
     * 
     * @param args {fileA, fileB} RDF files to compare
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 2) {
            System.err.println("Usage: rdfdiff <file1> <file2> [baseURI]");
            System.exit(1);
        }
        
        String baseURI = args.length > 2 ? args[2] : null;
        
        RDFDiffer differ = new RDFDiffer(baseURI);
        RDFDiff diff = differ.diff(args[0], args[1]);

        try {
            System.out.print(diff.toSPARQLUpdate());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
