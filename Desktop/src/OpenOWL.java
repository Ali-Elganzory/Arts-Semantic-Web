// Package 


//Imports
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.ModelFactory;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Saidi Ali-(+216) 22790538 or (+216) 50 590 538 -Tunisia
 */
//Class OpenOWL
class OpenOWL {

    static String s;

    // Connection
    static OntModel openConnectOWL(String filepath) {
        OntModel mode;
        mode = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);
        try {
            InputStream in = new FileInputStream(filepath);
            return (OntModel) mode.read(in, "");
        } catch (Exception e) {
            throw new IllegalArgumentException("No ontology file at the specified path.");
        }
    }

    // Gets results as a Jena ResultSet
    static ResultSet execSparQl(String query, OntModel model) {
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();
        return results;
    }

    // Gets results as a string
    static String getResultAsString(String query, OntModel model) {
        try {
            QueryExecution qe = QueryExecutionFactory.create(query, model);
            ResultSet results = qe.execSelect();
            if (results.hasNext()) {
                ByteArrayOutputStream go = new ByteArrayOutputStream();
                ResultSetFormatter.out((OutputStream) go, results);
                s = new String(go.toByteArray(), "UTF-8");
            } else {
                s = "No results.";
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(OpenOWL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

}
