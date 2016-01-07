package com.spring.model;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "ageyear")
public class AuthorYear {
	
	
		private String year;
		private String author;
		private String avgRate;
		
		
		public AuthorYear(String year, String author, String avgRate) {
			this.year = year;
			this.author = author;
			this.avgRate = avgRate;
			
		}
		
		@XmlElement
		public String getYear() {
			return year;
		}

		public void setYear(String year) {
			this.year = year;
		}

		@XmlElement
		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		@XmlElement
		public String getAvgRate() {
			return avgRate;
		}

		public void setAvgRate(String avgRate) {
			this.avgRate = avgRate;
		}
		@Override
		public String toString() {
			return "AgeYear [year=" + year + ", author=" + author + ", avgRate="
					+ avgRate + "]";
		}
}
