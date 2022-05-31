package application;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.connexion.connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmployeeController implements Initializable {

    @FXML
    private TableColumn<Employee, Integer> Matricule_Col;

    @FXML
    private TableColumn<Employee, Double> Hsupp_col;

    @FXML
    private TableColumn<Employee, Double> Phsupp_col;
    
    @FXML
    private TableView<Employee> TableEmployee;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		showEmployee() ;
		
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


