package DataConversion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.tdb.TDBFactory;


public class RatingsDataConversion {
	public void rate_text_to_rdf(){
		System.out.println("Inside Ratings data conversion");
		
		// Create an empty model
				String directory = "MyDatabases/Ratings";
				Dataset dataset = TDBFactory.createDataset(directory);

				// Delete Data set if it already exists.
				File index = new File(directory);
				if (index.exists()) {
					String[] entries = index.list();
					for (String s : entries) {
						File currentFile = new File(index.getPath(), s);
						currentFile.delete();
					}
					index.delete();
				}
			
				// Get model inside the transaction
				Model Rate_model = dataset.getDefaultModel();
				// Create custom properties to meet the requirements.
				Property Userid = Rate_model
						.createProperty("http://utdallas/semclass/vocabulary/rate#userid");
				Property Isbn = Rate_model
						.createProperty("http://utdallas/semclass/vocabulary/rate#isbn");
				Property Rate = Rate_model
						.createProperty("http://utdallas/semclass/vocabulary/rate#rate");
				
				
				try  {
					BufferedReader br = new BufferedReader(new FileReader("data/Ratings.txt"));
					String line = br.readLine();

					while (line != null) {

						String line_arr[] = line.split(",");
						String line_userid = line_arr[0];
						String line_isbn = line_arr[1];
						String line_rate = line_arr[2];
						String user_isbn = line_userid+"-"+line_isbn;

						Resource userbook = Rate_model
								.createResource("http://utdallas/semclass/userisbn#"
										+ user_isbn);

						userbook.addProperty(Userid, line_userid);
						userbook.addProperty(Isbn, line_isbn);
						userbook.addProperty(Rate,  line_rate );
						

						line = br.readLine();
					}
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Write the model data into files.
				File file;
				BufferedWriter bw = null;
				try {
					file = new File("RDF/RateData.xml");
					bw = new BufferedWriter(new FileWriter(file));
					dataset.begin(ReadWrite.WRITE);
					Rate_model.write(bw, "RDF/XML-ABBREV");
					dataset.commit();

					dataset.end();
				} catch (IOException e) {
					e.printStackTrace();
				}
				dataset.close();
				Rate_model.close();

				System.out.println("Ratings data upload Completed.");
		
	}// end of function book_text_to_rdf
}