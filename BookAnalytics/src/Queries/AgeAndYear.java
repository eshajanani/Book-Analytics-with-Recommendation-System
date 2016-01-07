package Queries;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.tdb.TDBFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartUtilities;

public class AgeAndYear {

	HashMap<String,Integer> data = new HashMap<String,Integer>();
		
	HashMap<String,Integer> cleandata = new HashMap<String,Integer>();
	
	
	public void ageYearAnalysis() throws IOException
	{
		Query query;
		QueryExecution qexec ;
		String directory = "MyDatabases/AgeandYear";
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
	String[] yr = new String[]{"2000","2001","2002","2003","2004"};
	BufferedWriter bw = new BufferedWriter(new FileWriter("data/dataAgeYear.csv"));
	for(String y:yr){
		String query_string = "PREFIX rat:<http://utdallas/semclass/vocabulary/rate#> "
				+ "PREFIX usr:<http://utdallas/semclass/vocabulary/user#> "
				+ "PREFIX bk:<http://utdallas/semclass/vocabulary/book#> "
				+ "select ?byear ?uage  (count(*) as ?cnt) "
				+ "where { "
				+ "?book bk:year ?byear. filter regex(str(?byear),'"+y+"','i')"
				+ "?user usr:age ?uage. "				
				+ "?rate rat:isbn ?book."
				+ "?rate rat:userid ?user."
				+ "?rate rat:rate ?r.} "
				+ "group by  ?byear ?uage ";
				
				
				
				
				
		
		System.out.println(query_string);
				System.out.println("Executing query 1");
		query = QueryFactory.create(query_string);
		 qexec = QueryExecutionFactory.create(query_string, model);
		System.out.println("Done Executing query 1");
		ResultSet results = qexec.execSelect();
	//ResultSetFormatter.out(System.out, results, query);
		
	
		RDFNode age,year,count;
	
	
		while( results.hasNext() ) 
		{
			 ArrayList<String> values =  new ArrayList<String>();
			 QuerySolution querySolution = results.next();
			 age=querySolution.get("uage");
			 values.add(String.valueOf(age));
			 year=querySolution.get("byear");
			 values.add(String.valueOf(year));
				count=querySolution.get("cnt");
				String temp1 = String.valueOf(count);
				temp1 = temp1.substring(0, temp1.indexOf("^"));
				 int num = Integer.parseInt(temp1);
			
			
			 bw.write(values.get(0) + "," +values.get(1) + "," + num + "\n");
			
		}// end of while
		
	qexec.close();
	}
	//	ResultSetFormatter.out(System.out, results, query);
	
	bw.close();
		
		// Close the store
	
		model.close();

		
	}// end of function 
	
	public void cleanDataOutput(){
		
		System.out.println("Inside cleanDataOutput");
		try  {
			BufferedReader br = new BufferedReader(new FileReader("data/dataAgeYear.csv"));
			String line = br.readLine();
			
			while (line != null) {
			//	System.out.println("inside while");

				String line_arr[] = line.split(",");
				
				int age = Integer.parseInt(line_arr[0]);
				
				int year = Integer.parseInt(line_arr[1]);
				int count =Integer.parseInt(line_arr[2]);
				System.out.println("age"+age);
	        	if(age >20 && age<=30){
	        		String ageGroup ="20 to 30";
	        		String key1 =ageGroup+":"+year;
	        		if(cleandata.containsKey(key1)){
	        		int temp =	cleandata.get(key1);
	        		int val = temp+count;
	        		cleandata.put(key1, val);
	        		}
	        		else
	        			cleandata.put(key1,count);
	        	}
	        	else if(age >30 && age<=40){
	        		
	        		String ageGroup ="30 to 40";
	        		String key1 =ageGroup+":"+year;
	        		if(cleandata.containsKey(key1)){
	        		int temp =	cleandata.get(key1);
	        		int val = temp+count;
	        		cleandata.put(key1, val);
	        		}
	        		else
	        			cleandata.put(key1,count);
	        	}
	        	else if(age >40 && age<=50){
	        	
	        		String ageGroup ="40 to 50";
	        		String key1 =ageGroup+":"+year;
	        		if(cleandata.containsKey(key1)){
	        		int temp =	cleandata.get(key1);
	        		int val = temp+count;
	        		cleandata.put(key1, val);
	        		}
	        		else
	        			cleandata.put(key1,count);
	        	}
	        	else
	        		{
	        		
	        		line = br.readLine();
	        		continue;
	        		}
	   
	        	
	        	line = br.readLine();
			}
			
			br.close();
	}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println("Outside cleanDataOutput");
	}// end of function
	
	
	public void generateGraph() throws IOException
	{
		System.out.println("Inside generateGraph");
		 final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		 Iterator it = cleandata.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        String key= (String) pair.getKey();
		        Integer count =  (Integer) pair.getValue();
		        System.out.println(key+", "+count);
		        String temp[] = key.split(":");
		        String ageGroup = temp[0];
		        String year = temp[1];
		        dataset.addValue(count, year, ageGroup);
		        
		    }// end of while
		    
		    JFreeChart barChart = ChartFactory.createBarChart(
		            "Book's trend over years and age group", 
		            "Category", "Count", 
		            dataset,PlotOrientation.VERTICAL, 
		            true, true, false);
		    int width = 640; /* Width of the image */
		      int height = 480; /* Height of the image */ 
		      File BarChart = new File( "data/BarChart.jpeg" ); 
		      ChartUtilities.saveChartAsJPEG( BarChart , barChart , width , height );
		    System.out.println("Outside generateGraph");
	}// end of function
	
	
	
}
