package DataConversion;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.tdb.TDBFactory;


public class UsersDataConversion {
	public void user_text_to_rdf(){
		System.out.println("Inside User data conversion");
		
		// Create an empty model
				String directory = "MyDatabases/Users";
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
				Model user_model = dataset.getDefaultModel();
				// Create custom properties to meet the requirements.
				Property City = user_model
						.createProperty("http://utdallas/semclass/vocabulary/user#city");
				Property State = user_model
						.createProperty("http://utdallas/semclass/vocabulary/user#state");
				Property Country = user_model
						.createProperty("http://utdallas/semclass/vocabulary/user#country");
				Property Age = user_model
						.createProperty("http://utdallas/semclass/vocabulary/user#age");
				
				try  {
					BufferedReader br = new BufferedReader(new FileReader("data/User.txt"));
					String line = br.readLine();

					while (line != null) {

						String line_arr[] = line.split(",");
						String line_userid = line_arr[0];
						String line_city = line_arr[1];
						String line_state = line_arr[2];
						String line_country = line_arr[3];
						String line_age = line_arr[4];

						Resource isbn = user_model
								.createResource("http://utdallas/semclass/userid#"
										+ line_userid);

						isbn.addProperty(City, line_city);
						isbn.addProperty(State, line_state);
						isbn.addProperty(Country, line_country);
						isbn.addProperty(Age, line_age);

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
					file = new File("RDF/UserData.xml");
					bw = new BufferedWriter(new FileWriter(file));
					dataset.begin(ReadWrite.WRITE);
					user_model.write(bw, "RDF/XML-ABBREV");
					dataset.commit();

					dataset.end();
				} catch (IOException e) {
					e.printStackTrace();
				}
				dataset.close();
				user_model.close();
				System.out.println("User data upload Completed.");
		
	}// end of function book_text_to_rdf
}
