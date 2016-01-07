package Main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class UserDataCleaning {
	
	 public void cleanUserData() throws IOException {

			
			BufferedReader br = null;
			BufferedWriter bw =null;
			String line = "";
			String cvsSplitBy = ",";

			try {
				
				br = new BufferedReader(new FileReader("data/Users1.csv"));
				 bw= new BufferedWriter(new FileWriter("data/User.csv"));
				while ((line = br.readLine()) != null) {

				        // use comma as separator
					String[] user = line.split(",");

					ArrayList<String> values =  new ArrayList<String>();
					String regex = "[a-zA-Z\\s]*";
					boolean a1= user[1].trim().matches(regex);
					boolean a2= user[2].trim().matches(regex);
					boolean a3= user[3].trim().matches(regex);
					System.out.println(a1+"," +a2 +","+a3);
					if(a1 && a2 && a3){
						bw.write(user[0]+","+user[1]+","+user[2]+","+user[3]+","+user[4]+ "\n");
						System.out.println(user[0]+","+user[1]+","+user[2]+","+user[3]+","+user[4]);
					}
					else{
						continue;
					}

				}
			
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			br.close();
			bw.close();
			System.out.println("Done");
		  }

}
