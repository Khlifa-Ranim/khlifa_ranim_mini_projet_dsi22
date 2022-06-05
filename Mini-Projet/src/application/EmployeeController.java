package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.connexion.connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class EmployeeController implements Initializable {

    @FXML
    private TableColumn<Employee, Integer> Matricule_Col;

    @FXML
    private TableColumn<Employee, Double> Hsupp_col;

    @FXML
    private TableColumn<Employee, Double> Phsupp_col;
    
    @FXML
    private TableView<Employee> TableEmployee;
    
    @FXML
    private Button Retourner;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		showEmployee() ;
		
	}

	  @FXML
	  public void RtournerAcceuil(ActionEvent event) throws IOException {
	        try {
	      
	            Parent root;
	            root = FXMLLoader.load(getClass().getResource("gestion_salarie.fxml"));
	            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
	            Scene scene = new Scene(root);
	            stage.setScene(scene);
	            stage.show();
	        } catch (Exception e) {
	            System.out.println(e.fillInStackTrace());
	        }
	    }
	
	  
	  
	public ObservableList<Employee> getEmployeeList() {
		ObservableList<Employee> EmployeeList = FXCollections.observableArrayList(); // Retourner la liste des employee

		String SQL = "select * from employee e,salarie s where e.matricule=s.matricule";

		try {
			Statement st = connexion.getConx().createStatement();
			ResultSet rs = st.executeQuery(SQL);
			while (rs.next()) {

				Employee Employee = new Employee(rs.getInt("matricule"),rs.getString("nom"),rs.getString("email"),rs.getDouble("anneRecruit"),rs.getDouble("salaire"),rs.getString("Role"),rs.getDouble("HSupp"), rs.getDouble("PHSupp"));
				EmployeeList.add(Employee);
			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return EmployeeList;
	}

	public void showEmployee() {

		ObservableList<Employee> List = getEmployeeList();

		Matricule_Col.setCellValueFactory(new PropertyValueFactory<>("Matricule"));
		Hsupp_col.setCellValueFactory(new PropertyValueFactory<>("HSupp"));
		Phsupp_col.setCellValueFactory(new PropertyValueFactory<>("PHsupp"));
	
		TableEmployee.setItems(List);

	}
}


