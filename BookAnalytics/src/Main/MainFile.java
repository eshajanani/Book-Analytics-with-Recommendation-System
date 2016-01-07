package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Pattern;

import DataConversion.AnalyticsRateDataConversion;
import DataConversion.BookDataConversion;

import DataConversion.RatingsDataConversion;

import DataConversion.UsersDataConversion;
import Queries.AgeAndYear;
import Queries.RecommendQuery;
import Queries.Top10CountryAndYear;
import Queries.Top10StateAndYear;
import Queries.TopAuthorYear;
import Queries.TopPubisherYear;
import functionality.ItemRecommend;

public class MainFile {
	public static void main(String[] args) throws IOException {
		Scanner scanIn = new Scanner(System.in);
		/*UsersDataConversion uc = new UsersDataConversion();
		uc.user_text_to_rdf();

		RatingsDataConversion rc = new RatingsDataConversion();
		rc.rate_text_to_rdf();
		BookDataConversion bc = new BookDataConversion();
		bc.book_text_to_rdf();
		AnalyticsRateDataConversion arc = new AnalyticsRateDataConversion();
		arc.rate_text_to_rdf();*/
		RecommendQuery rq = new RecommendQuery();
		rq.recommendationQueryProcess();
		ItemRecommend it = new ItemRecommend();
		System.out.println("enter the user id fr recommendation:");
		
		String uid = scanIn.nextLine();
		long userID = Long.parseLong(uid);
		it.recommendItems(userID);
		
	/*	UserDataCleaning udc = new UserDataCleaning();
		udc.cleanUserData();
			
		UsersDataConversion uc = new UsersDataConversion();
		uc.user_text_to_rdf();	

		System.out.println("Enter the year and country :");
		System.out.println("Enter country:");
		String q1country = scanIn.nextLine();
		System.out.println("Enter year:");
		String q1year = scanIn.nextLine();
		Top10CountryAndYear q1 = new Top10CountryAndYear(q1year,q1country);
		q1.top10CountryYearQueryProcess();
	
	
		System.out.println("Enter the year and state :");
		System.out.println("Enter state:");
		String q2state = scanIn.nextLine();
		System.out.println("Enter year:");
		String q2year = scanIn.nextLine();
		Top10StateAndYear q2 = new Top10StateAndYear(q2year,q2state);
		q2.top10StateYearQueryProcess();
			
				System.out.println("Enter year:");
		String q3year = scanIn.nextLine();
		TopPubisherYear q3 = new TopPubisherYear(q3year);
		q3.topPublisherYear();
		
			
			
		System.out.println("Enter year:");
		String q4year = scanIn.nextLine();
		TopAuthorYear q4 = new TopAuthorYear();
		q4.topAuthorYear(q4year);
		AgeAndYear ay = new AgeAndYear();
		ay.ageYearAnalysis();
		ay.cleanDataOutput();
		ay.generateGraph();*/
		scanIn.close();
		System.out.println("Done");

	}

}