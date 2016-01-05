package application;

import javafx.beans.property.*;

//Creates getter and setter methods for each property.
public class BookDataModel {
	
	
	private SimpleStringProperty title;
	private SimpleStringProperty author;
	private SimpleStringProperty numberOfPages;
	private SimpleStringProperty dateStarted;
	private SimpleStringProperty dateFinished;
	private SimpleStringProperty thoughts;
		protected BookDataModel(){
			this.title = new SimpleStringProperty();
			this.author = new SimpleStringProperty();
			this.numberOfPages = new SimpleStringProperty();
			this.dateStarted = new SimpleStringProperty();
			this.dateFinished = new SimpleStringProperty();
			this.thoughts = new SimpleStringProperty();
		}
	
		public String getTitle(){
			return title.get();
		} 
		public void setTitle(String title){ 
			this.title.set(title); 
		}
		public String getAuthor(){
			return author.get();
		}
		public void setAuthor(String author){
			this.author.set(author);
		}
		public String getNumberOfPages(){
			return numberOfPages.get();
		}
		public void setNumberOfPages(String numberOfPages){
			this.numberOfPages.set(numberOfPages);
		}
		public String getDateStarted(){
			return dateStarted.get();
		}
		public void setDateStarted(String dateStarted){
			this.dateStarted.set(dateStarted);
		}
		public String getDateFinished(){
			return dateFinished.get();
		}
		public void setDateFinished(String dateFinished){
			this.dateFinished.set(dateFinished);
		}
		public String getThoughts(){
			return thoughts.get();
		}
		public void setThoughts(String thoughts){
			this.thoughts.set(thoughts);
		}

		public void add(BookDataModel book) {
			
		}

		public void add(String string) {
			
		}

		

		
	
}	

