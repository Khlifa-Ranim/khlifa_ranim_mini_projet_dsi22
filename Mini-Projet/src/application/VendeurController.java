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

public class VendeurController implements Initializable {
	
	

    @FXML
    private TableView<Vendeur> tableVendeur;

    @FXML
    private TableColumn<Vendeur,Integer> Matricule_Col;

    @FXML
    private TableColumn<Vendeur,Double> Vente_col;

    @FXML
    private TableColumn<Vendeur,Double> Pourcentage_col;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		showVendeur();
		
	}
	public ObservableList<Vendeur> getVendeurList() {
		ObservableList<Vendeur> VendeurList = FXCollections.observableArrayList(); // Retourner la liste des employee

		String SQL = "select * from vendeur v,salarie s where v.matricule=s.matricule";

		try {
			Statement st = connexion.getConx().createStatement();
			ResultSet rs = st.executeQuery(SQL);
			while (rs.next()) {

				Vendeur Vendeur = new Vendeur(rs.getInt("matricule"),rs.getString("nom"),rs.getString("email"),rs.getDouble("anneRecruit"),rs.getDouble("salaire"),rs.getString("Role"),rs.getDouble("Vente"), rs.getDouble("Pourcentage"));
				VendeurList.add(Vendeur);
			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return VendeurList;
	}

	public void showVendeur() {

		ObservableList<Vendeur> List = getVendeurList();

		Matricule_Col.setCellValueFactory(new PropertyValueFactory<>("Matricule"));
		Vente_col.setCellValueFactory(new PropertyValueFactory<>("Vente"));
		Pourcentage_col.setCellValueFactory(new PropertyValueFactory<>("Pourcentage"));
	
		tableVendeur.setItems(List);

	}

}
