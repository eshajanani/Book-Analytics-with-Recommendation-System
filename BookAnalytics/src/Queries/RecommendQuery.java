package Queries;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.query.Syntax;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.tdb.TDBFactory;

public class RecommendQuery {

	public RecommendQuery(){
		
	}
	
	
	public void recommendationQueryProcess() throws IOException
	{
		String directory = "MyDatabases/Recommend";
		Dataset dataset = TDBFactory.createDataset(directory);
		Model model = ModelFactory.createDefaultModel();
		File index = new File(directory);
		if (index.exists()) {
			String[] entries = index.list();
			for (String s : entries) {
				File currentFile = new File(index.getPath(), s);
				currentFile.delete();
			}
			index.delete();
		}
	//	InputStream in_stream1 = new FileInputStream(new File("RDF/BookData.xml"));
		InputStream in_stream2 = new FileInputStream(new File("RDF/RateData.xml"));
	//	InputStream in_stream3 = new FileInputStream(new File("RDF/UserData.xml"));
	//	model.read(in_stream1, null);
		model.read(in_stream2, null);
	//	model.read(in_stream3, null);
	//	in_stream1.close();
		in_stream2.close();
	//	in_stream3.close();
		
		String query_string = "PREFIX rat:<http://utdallas/semclass/vocabulary/rate#>"
				+ "select ?uid ?bid ?r "
				+ "where { ?x rat:userid ?uid."
				+ "?x rat:isbn ?bid."
				+ "?x rat:rate ?r.}";
				
		Query query = QueryFactory.create(query_string);
		QueryExecution qexec = QueryExecutionFactory.create(query_string, model);

		ResultSet results = qexec.execSelect();
	//	ResultSetFormatter.out(System.out, results, query);
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("data/rate.csv"));
		RDFNode user,book,ratings;
		
		
		int x= 0;
		while( results.hasNext() ) 
		{
			 ArrayList<String> values =  new ArrayList<String>();
			 QuerySolution querySolution = results.next();
			 user=querySolution.get("uid");
			 book = querySolution.get("bid");
			 ratings=querySolution.get("r");
			 values.add(String.valueOf(user));
			String temp = String.valueOf(book);
			String regex = "^[0-9]+";
			boolean isNum =temp.matches(regex);
			 if(isNum)
			 {
				 
				 values.add(temp);
			 }
			 else
			 {
				//System.out.println(temp);
				continue;
			 }
			 values.add(String.valueOf(ratings));
			 bw.write(values.get(0) + "," + values.get(1) + "," + Integer.parseInt(values.get(2)) + "\n");
			 x++;
			// System.out.println(x);
			
		}// end of while
		
	//	ResultSetFormatter.out(System.out, results, query);
		bw.close();
		qexec.close();
		
		// Close the store
		
		model.close();

		
	}// end of function 
	
	
	
}
