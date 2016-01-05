package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;






public class BookGUIController implements Initializable{
	
	@FXML
	private BorderPane borderPane;
	@FXML
    public TableView<BookDataModel> bookTable;
    @FXML
    private TableColumn<BookDataModel, String> title;
    @FXML
    private TableColumn<BookDataModel, String> author;
    @FXML
    private TableColumn<BookDataModel, String> numberOfPages;
    @FXML
    private TableColumn<BookDataModel, String> dateStarted;
    @FXML
    private TableColumn<BookDataModel, String> dateFinished;
    @FXML
    private TableColumn<BookDataModel, String> thoughts;
    @FXML
    private TextField searchField;
    @FXML
    private Button deleteButton;
    @FXML
    private MenuButton optionsMenuBar;
    @FXML
    private MenuItem addNewEntry;
    @FXML
    private MenuItem editExistingEntry;
    @FXML
    private GridPane newEntryDialog;
    @FXML
    private GridPane editExistingEntryDialog;
    @FXML 
    private NewEntryController newEntryDialogController;
    @FXML
    private EditExistingEntryController editExistingEntryDialogController;
    
    
    public BookGUIController(){}
    
    
	
	@Override
    public void initialize(URL location, ResourceBundle resources) {
    	
		//Creates columns.
		title.setCellValueFactory(new PropertyValueFactory<BookDataModel,String>("title"));		
		author.setCellValueFactory(new PropertyValueFactory<BookDataModel,String>("author"));	
		numberOfPages.setCellValueFactory(new PropertyValueFactory<BookDataModel,String>("numberOfPages"));	
		dateStarted.setCellValueFactory(new PropertyValueFactory<BookDataModel,String>("dateStarted"));
		dateFinished.setCellValueFactory(new PropertyValueFactory<BookDataModel,String>("dateFinished"));
		thoughts.setCellValueFactory(new PropertyValueFactory<BookDataModel,String>("thoughts"));
		
		DBConnection conn = new DBConnection();
	
		try{
		
	    ObservableList<BookDataModel> bookData = FXCollections.observableArrayList();	
		
	    //Connects to database.
		Connection myConn = conn.getConnection();
		String query = "SELECT * FROM BOOKS";	
		PreparedStatement pst;
		pst = myConn.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		
        //Iterates through database and inserts information from .csv file into table.
        while (rs.next()) {
        	BookDataModel book = new BookDataModel();
            book.setTitle(rs.getString("Title"));
            book.setAuthor(rs.getString("Author"));
            book.setNumberOfPages(rs.getString("Number_of_Pages"));
            book.setDateStarted(rs.getString("Start_Date"));
            book.setDateFinished(rs.getString("End_Date"));
            book.setThoughts(rs.getString("Thoughts"));   
            bookData.add(book);
        	}
        
        bookTable.setItems(bookData);
        bookTable.setEditable(true);
        
        bookTable.setRowFactory( tv -> {
            TableRow<BookDataModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    BookDataModel rowData = bookTable.getSelectionModel().getSelectedItem();
                    editExistingEntryDialogController.showBook(rowData);
                    handleEdit();
                    
                }
            });
            return row ;
        });
        
       
        
        
        
        //Implement search function
        FilteredList<BookDataModel> filteredData = new FilteredList<>(bookData, p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(data -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (data.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } if (data.getAuthor().toLowerCase().indexOf(lowerCaseFilter) != -1){
                	return true; 
                } if (data.getNumberOfPages().toLowerCase().indexOf(lowerCaseFilter) != -1){
                	return true;
                } if (data.getDateStarted().toLowerCase().indexOf(lowerCaseFilter) != -1){
                	return true;
                } if (data.getDateFinished().toLowerCase().indexOf(lowerCaseFilter) != -1){
                	return true;
                } if (data.getThoughts().toLowerCase().indexOf(lowerCaseFilter) != -1){
                	return true;
                } else{
                	return false;
                }
            });
        });
        SortedList<BookDataModel> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(bookTable.comparatorProperty());
        bookTable.setItems(sortedData); 
		}
		
        catch (SQLException | ClassNotFoundException e){
        	System.out.println(e.getMessage());
        } 
			     
	}
	
	@FXML
	private void deleteEntry(ActionEvent e){
		DBConnection conn = new DBConnection();
		BookDataModel data = bookTable.getSelectionModel().getSelectedItem();
		
		if(data != null){
			
			try{
				Connection myConn = conn.getConnection();
				String query = "DELETE FROM BOOKS WHERE Title = '"+data.getTitle()+"'";	
				PreparedStatement pst;
				pst = myConn.prepareStatement(query);
				pst.executeUpdate();
				bookTable.getSelectionModel().clearSelection();
			
			} catch (Exception ex){
				System.out.println(ex);
			}
		}
		
	    if (data == null){
	        // Nothing selected.
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(Main.getStage());
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Row Selected");
	        alert.setContentText("Please select a row in the table.");
	        alert.showAndWait();
	    }
		
	}
	
	
	      
	
	//Sets the New Entry menu item with its proper controller.
	@FXML
	void showNewEntry(ActionEvent e){
		newEntryDialogController.handleAddNewEntryMenuItem(newEntryDialog);
	}
	
	//Handle editing entry
	@FXML
	public void handleEdit(){
	    	editExistingEntryDialogController.handleEditExistingEntryMenuItem(editExistingEntryDialog);
	    	
	}
	

}
        



	
	

	
		

