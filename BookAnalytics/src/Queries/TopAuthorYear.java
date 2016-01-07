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
public class TopAuthorYear {

String year;
	
	public TopAuthorYear()
	{
		
	}
	
	public ArrayList<String> topAuthorYear(String year) throws IOException
	{
		String directory = "MyDatabases/TopAuthorYear";
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
		InputStream in_stream1 = new FileInputStream(new File("RDF/BookData.xml"));
		InputStream in_stream2 = new FileInputStream(new File("RDF/AnalRateData.xml"));
		InputStream in_stream3 = new FileInputStream(new File("RDF/UserData.xml"));
		model.read(in_stream1, null);
		model.read(in_stream2, null);
		model.read(in_stream3, null);
		in_stream1.close();
		in_stream2.close();
		in_stream3.close();
	
		String query_string = "PREFIX rat:<http://utdallas/semclass/vocabulary/rate#> "
				+ "PREFIX usr:<http://utdallas/semclass/vocabulary/user#> "
				+ "PREFIX bk:<http://utdallas/semclass/vocabulary/book#> "
				+"select ?byear ?bauthor ?avg "
				+ "where { "
				+ "?book bk:year ?byear. "
				+ "{ select ?byear ?bauthor (AVG(?r) as ?avg) "
				+ "where { "
				+ "?book bk:author ?bauthor. "
				+ "?book bk:year ?byear. "
				+ "?rate rat:isbn ?book."
				+ "?rate rat:rate ?r.} "
				+ "group by ?byear ?bauthor }}"
				+ "order by desc(?avg) "
				+ "limit 1";
				
				
	
				
			
				
				
		
		System.out.println(query_string);
				System.out.println("Executing query 1");
		Query query = QueryFactory.create(query_string);
		QueryExecution qexec = QueryExecutionFactory.create(query_string, model);
		System.out.println("Done Executing query 1");
		ResultSet results = qexec.execSelect();
	//ResultSetFormatter.out(System.out, results, query);
		
	BufferedWriter bw = new BufferedWriter(new FileWriter("data/authoryear.csv"));
		RDFNode pub,year1,ratings;
		int x =0;
		 ArrayList<String> values =  new ArrayList<String>();
		while( results.hasNext() ) 
		{
			
			 QuerySolution querySolution = results.next();
			 pub=querySolution.get("bauthor");
			 values.add(String.valueOf(pub));
			 year1=querySolution.get("byear");
			 values.add(String.valueOf(year1));
			 ratings=querySolution.get("avg");
			 String temp = String.valueOf(ratings);
			 temp = temp.substring(0,2);
			 System.out.println("rate:"+temp);
			 values.add(temp);
			 bw.write(values.get(0) + "," +values.get(1) + "," + values.get(2) + "\n");
			x++;
			 System.out.println(x);
			
		}// end of while
		
	//	ResultSetFormatter.out(System.out, results, query);
		bw.close(); 
		qexec.close();
		
		// Close the store
		
		model.close();

		return values;
	}// end of function 

	
}
