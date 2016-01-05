package application;


import java.sql.Connection;
import java.sql.PreparedStatement;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewEntryController {
	
	
	@FXML
	private GridPane newEntryDialog;
	@FXML
	private GridPane gridPane;
	@FXML
	private Button addBookButton;
	@FXML
	private Button cancelButton;
	@FXML
	private TextField titleTextField;
	@FXML
	private TextField authorTextField;
	@FXML
	private TextField numberOfPagesTextField;
	@FXML 
	private TextField startDateTextField;
	@FXML
	private TextField endDateTextField;
	@FXML
	private TextField thoughtsTextField;
	@FXML
	private Label titleLabel;
	@FXML
	private Label authorLabel;
	@FXML
	private Label numberOfPagesLabel;
	@FXML 
	private Label startDateLabel;
	@FXML
	private Label endDateLabel;
	@FXML
	private Label thoughtsLabel;
	@FXML
	public TableView<BookDataModel> bookTable;
	@FXML
	public ObservableList<BookDataModel> bookData;
	
	BookDataModel data;
	public NewEntryController(){}
	
	
	
	@FXML
	private void initialize(){
		addBookButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	DBConnection conn = new DBConnection();
				
				String s1 = titleTextField.getText();
				String s2 = authorTextField.getText();
				String s3 = numberOfPagesTextField.getText();
				String s4 = startDateTextField.getText();
				String s5 = endDateTextField.getText();
				String s6 = thoughtsTextField.getText();
				
				String query = "INSERT INTO BOOKS (Title, Author, Number_of_Pages, Start_Date, End_Date, Thoughts) VALUES (' "+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"')";
				PreparedStatement pst;
				
				try{
					
				Connection myConn = conn.getConnection();
				pst = myConn.prepareStatement(query);
			    pst.executeUpdate();
			    data.setTitle(s1);
			    data.setAuthor(s2);
			    data.setNumberOfPages(s3);
			    data.setDateStarted(s4);
			    data.setDateFinished(s5);
			    data.setThoughts(s6);
		     
				}catch (Exception ex){
					System.out.println(ex);
				}
				
				Stage stage = (Stage) newEntryDialog.getScene().getWindow();
		        stage.close();
		        
		    }
		    	
		});
	}
		
	
	//Builds window.
	final void handleAddNewEntryMenuItem(GridPane gridPane){
		try{
			GridPane root = FXMLLoader.load(getClass().getResource("/view/NewEntryDialog.fxml"));	
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.WINDOW_MODAL);
			stage.setScene(scene);
			stage.showAndWait();
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	
	

	@FXML
	public void handleCancel(){
		Stage stage = (Stage) newEntryDialog.getScene().getWindow();
        stage.close();
	}

	
        	
        
	}
	

