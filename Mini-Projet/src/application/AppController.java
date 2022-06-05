package application;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class AppController implements Initializable {

	@FXML
	private TextArea matricule;
	@FXML
	private TextArea nom;

	@FXML
	private TextArea email;

	@FXML
	private TextArea salaire;

	@FXML
	private TextArea date_embauche;

	@FXML
	private TextArea categorie;

	@FXML
	private TextArea Hssupp_Vente;

	@FXML
	private TextArea PHSupp_Pourcentage;

	@FXML
	private TableView<Salarie> TableSalarie;

	@FXML
	private Button add_btn;

	@FXML
	private Button delete_btn;

	@FXML
	private Button edite_btn;

	@FXML
	private TextArea chercher;

	@FXML
	private TableColumn<Salarie, Integer> matricule_col;

	@FXML
	private TableColumn<Salarie, String> nom_col;

	@FXML
	private TableColumn<Salarie, String> email_col;

	@FXML
	private TableColumn<Salarie, Double> date_col;

	@FXML
	private TableColumn<Salarie, Double> salaire_col;

	@FXML
	private TableColumn<Salarie, String> categorie_col;

	@FXML
	void handelButtonAction(ActionEvent event) {

		if (event.getSource() == add_btn) {
			createSalarie();
		} else if (event.getSource() == edite_btn) {
			ModifierSalarie();
		} else if (event.getSource() == delete_btn) {
			SupprimerSalarie();
		}

	}

	@FXML
	void handelMouseAvtion(MouseEvent event) {
		Salarie Salarie = TableSalarie.getSelectionModel().getSelectedItem();
		matricule.setText(""+ Salarie.getMatricule());
		nom.setText(Salarie.getNom());
		email.setText(Salarie.getEmail());
		salaire.setText("" + Salarie.getSalaire());
		date_embauche.setText("" + Salarie.getAnneRecruit());
		categorie.setText(Salarie.getRole());

	}

 
 
    
    
    public void ListeEmployee(ActionEvent event) throws IOException {
        try {
      
            Parent root;
            root = FXMLLoader.load(getClass().getResource("EmployeeList.fxml"));
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
    }
	
    
    public void listeVendeur(ActionEvent event) throws IOException {
        try {
      
            Parent root;
            root = FXMLLoader.load(getClass().getResource("VendeurList.fxml"));
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		showSalarie();

	}

	public ObservableList<Salarie> getSalarieList() {
		ObservableList<Salarie> SalarieList = FXCollections.observableArrayList(); // Retourner la liste des employee

		String SQL = "select * from salarie";

		try {
			Statement st = connexion.getConx().createStatement();
			ResultSet rs = st.executeQuery(SQL);
			while (rs.next()) {

				Salarie Salarie = new Salarie(rs.getInt("matricule"), rs.getString("nom"), rs.getString("email"),
						rs.getDouble("anneRecruit"), rs.getDouble("salaire"), rs.getString("Role"));
				SalarieList.add(Salarie);
			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SalarieList;
	}

	public void showSalarie() {

		ObservableList<Salarie> List = getSalarieList();

		matricule_col.setCellValueFactory(new PropertyValueFactory<>("Matricule"));
		nom_col.setCellValueFactory(new PropertyValueFactory<Salarie, String>("Nom"));
		email_col.setCellValueFactory(new PropertyValueFactory<Salarie, String>("Email"));
		date_col.setCellValueFactory(new PropertyValueFactory<Salarie, Double>("AnneRecruit"));
		salaire_col.setCellValueFactory(new PropertyValueFactory<Salarie, Double>("Salaire"));
		categorie_col.setCellValueFactory(new PropertyValueFactory<Salarie, String>("Role"));
		TableSalarie.setItems(List);

	}

	/*public void insertSalarie() {
		try {
			String SQL = "Insert into salarie values(" + matricule.getText() + ",'" + nom.getText() + "','"
					+ email.getText() + "'," + salaire.getText() + "," + date_embauche.getText() + ",'"
					+ categorie.getText() + "')";
			Statement stmt = connexion.getConx().createStatement();
			if (stmt.executeUpdate(SQL) == 1) {
				showSalarie();
			}
			// if(categorie.getText().equals("Employee"))

		} catch (SQLException ex) {
			System.out.println("Error" + ex);
		}
	}
*/
	public void ModifierSalarie() {
		try {
			String SQL = "update salarie set nom='" + nom.getText() + "',email='" + email.getText() + "',salaire="
					+ salaire.getText() + ",anneRecruit=" + date_embauche.getText() + ",Role='" + categorie.getText()
					+ "' where matricule=" + matricule.getText();
			Statement stmt = connexion.getConx().createStatement();
			if (stmt.executeUpdate(SQL) == 1)
			showSalarie();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	public void SupprimerSalarie() {
		try {
			String SQL = "delete from salarie  where Matricule=" + matricule.getText();
			Statement stmt = connexion.getConx().createStatement();
			if (stmt.executeUpdate(SQL) == 1)
				showSalarie();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	public void createSalarie() {
		try {
			Statement stmt = connexion.getConx().createStatement();

			if (Integer.parseInt(date_embauche.getText()) >= 2005) {
				salaire.setText("400");
			} else {
				salaire.setText("280");

			}

			if (categorie.getText().equals("employee")) {
				String SqlSalaire = "Insert into salarie values(" + matricule.getText() + ",'" + nom.getText() + "','"
						+ email.getText() + "'," + salaire.getText() + "," + date_embauche.getText() + ",'"
						+ categorie.getText() + "')";
				System.out.println(SqlSalaire);
				if (stmt.executeUpdate(SqlSalaire) == 1) {
					System.out.println("Insertion a Salarie  a étè effectuer !");
					showSalarie();
				}
				String reqEmployee = "Insert into employee values (" + matricule.getText() + ","
						+ Hssupp_Vente.getText() + "," + PHSupp_Pourcentage.getText() + ");";
				
				System.out.println(stmt.executeUpdate(reqEmployee));
					System.out.println("Insertion a Employee  a étè effectuer !");
				
			}
			if (categorie.getText().equals("vendeur")) {
				String reqSalarie = "Insert into salarie values(" + matricule.getText() + ",'" + nom.getText() + "','"
						+ email.getText() + "'," + salaire.getText() + "," + date_embauche.getText() + ",'"
						+ categorie.getText() + "')";
				System.out.println(reqSalarie);
				if (stmt.executeUpdate(reqSalarie) == 1) {
					System.out.println("Insertion a Salarie  a étè effectuer !");
					showSalarie();
				}

				String reqVendeur = "Insert into vendeur values (" + matricule.getText() + "," + Hssupp_Vente.getText()
						+ "," + PHSupp_Pourcentage.getText() + ");";
				System.out.println(matricule.getText());
				System.out.println(stmt.executeUpdate(reqVendeur));
					System.out.println("Insertion a Vendeur  a étè effectuer !");
				
			}

		} catch (SQLException ex) {
			System.out.println("Erreur SQL " + ex);

		}
	}
}