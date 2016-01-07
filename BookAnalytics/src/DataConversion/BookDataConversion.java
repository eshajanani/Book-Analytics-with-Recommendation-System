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



public class BookDataConversion {
	
	public void book_text_to_rdf(){
		System.out.println("Inside book data conversion");
		
		// Create an empty model
				String directory = "MyDatabases/Book";
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
				Model book_model = dataset.getDefaultModel();
				// Create custom properties to meet the requirements.
				Property Title = book_model
						.createProperty("http://utdallas/semclass/vocabulary/book#title");
				Property Author = book_model
						.createProperty("http://utdallas/semclass/vocabulary/book#author");
				Property Year = book_model
						.createProperty("http://utdallas/semclass/vocabulary/book#year");
				Property Publisher = book_model
						.createProperty("http://utdallas/semclass/vocabulary/book#publisher");
				
				try  {
					BufferedReader br = new BufferedReader(new FileReader("data/book.txt"));
					String line = br.readLine();

					while (line != null) {

						String line_arr[] = line.split(",");
						String line_isbn = line_arr[0];
						String line_title = line_arr[1];
						String line_author = line_arr[2];
						String line_year = line_arr[3];
						String line_publisher = line_arr[4];

						Resource isbn = book_model
								.createResource("http://utdallas/semclass/isbn#"
										+ line_isbn);

						isbn.addProperty(Title, line_title);
						isbn.addProperty(Author, line_author);
						isbn.addProperty(Year, line_year);
						isbn.addProperty(Publisher, line_publisher);

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
					file = new File("RDF/BookData.xml");
					bw = new BufferedWriter(new FileWriter(file));
					dataset.begin(ReadWrite.WRITE);
					book_model.write(bw, "RDF/XML-ABBREV");
					dataset.commit();

					dataset.end();
				} catch (IOException e) {
					e.printStackTrace();
				}
				dataset.close();
				book_model.close();
				System.out.println("Book data upload Completed.");
		
	}// end of function book_text_to_rdf

}// end of class BookDataConversion
