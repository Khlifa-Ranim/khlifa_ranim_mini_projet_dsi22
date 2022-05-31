package application.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Employee;
import application.Salarie;
import application.Vendeur;
import application.connexion.connexion;

public class AppService {

	public boolean createEmployee(Employee E) {
		try {
			Statement stmt = connexion.getConx().createStatement();
			if (E.getAnneRecruit() < 2005)
				E.setSalaire(400);
			else
				E.setSalaire(280);
			String reqSalarie = "Insert into salarie values(" + E.getMatricule() + ",'" + E.getNom() + "','"
					+ E.getEmail() + "'," + E.getAnneRecruit() + "," + E.getSalaire() + ",'Employee');"; 
			if (stmt.executeUpdate(reqSalarie) == 1) {
				String reqEmployee = "Insert into Employee values (" + E.getMatricule() + "," + E.getHSupp() + ","
						+ E.getPHsupp() + ");";
				if (stmt.executeUpdate(reqEmployee) == 1) {
					return true;
				}
			}
		} catch (SQLException ex) {
			System.out.println("Erreur SQL");
		}
		return false;
	}

	public boolean createVendeur(Vendeur V) {
		try {
			Statement stmt = connexion.getConx().createStatement();
			if (V.getAnneRecruit() < 2005)
				V.setSalaire(400);
			else
				V.setSalaire(280);
			String reqSalarie = "Insert into salarie values(" + V.getMatricule() + ",'" + V.getNom() + "','"
					+ V.getEmail() + "'," + V.getAnneRecruit() + "," + V.getSalaire() + ",'Vendeur');";
			if (stmt.executeUpdate(reqSalarie) == 1) {
				String reqEmployee = "Insert into Vendeur values (" + V.getMatricule() + "," + V.getVente() + ","
						+ V.getPourcentage() + ");";
				if (stmt.executeUpdate(reqEmployee) == 1) {
					return true;
				}
			}

		} catch (SQLException ex) {
			System.out.println("Erreur SQL" + ex);
		}
		return false;
	}

	public void ListeSalarie() {
		String req = "select * from Salarie ";
		try {
			Statement st = connexion.getConx().createStatement();
			ResultSet rs = st.executeQuery(req);
			System.out.println("La liste des Salaries :");
			while (rs.next()) {
				System.out.println("[" + rs.getInt("matricule") + "  " + rs.getString("nom") + "  "
						+ rs.getString("email") + "  " + rs.getDouble("anneRecruit") + "  " + rs.getDouble("salaire")
						+ "  " + rs.getString("Role") + "]");
			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ListeEmployee() {
		String req = "select * from employee ";
		try {
			Statement st = connexion.getConx().createStatement();
			ResultSet rs = st.executeQuery(req);
			System.out.println("La liste des Employees :");
			while (rs.next()) {
				System.out.println("[" + rs.getInt("matricule") + "  " + rs.getDouble("HSupp") + "  "
						+ rs.getDouble("PHSupp") + "]");
			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ListeVendeur() {
		String req = "select * from vendeur ";
		try {
			Statement st = connexion.getConx().createStatement();
			ResultSet rs = st.executeQuery(req);
			System.out.println("La liste des Vendeurs :");
			while (rs.next()) {
				System.out.println("[" + rs.getInt("matricule") + "  " + rs.getDouble("Vente") + "  "
						+ rs.getDouble("Pourcentage") + "]");
			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	public boolean DeleteEmployee (Employee e) {
		
		String req = "delete from employee  where Matricule  ="+e.getMatricule();
		
		
		try {
			Statement st =connexion.getConx().createStatement();
			int nb =st.executeUpdate(req);
			st.close();
			System.out.println(nb);
			System.out.println("Supeission efectueé");
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return false;
	}
	
	
	public boolean DeleteVendeur (Vendeur v) {
		
		String req = "delete from employee  where Matricule  ="+v.getMatricule();
		
		
		try {
			Statement st =connexion.getConx().createStatement();
			int nb =st.executeUpdate(req);
			st.close();
			System.out.println(nb);
			System.out.println("Supeission efectueé");
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return false;
	}
	
	

	
	public boolean DeleteSalarie (Salarie s) {
		
		String req = "delete from salarie  where Matricule  ="+s.getMatricule();
		
		
		try {
			Statement st =connexion.getConx().createStatement();
			int nb =st.executeUpdate(req);
			st.close();
			System.out.println(nb);
			System.out.println("Supeission efectueé");
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return false;
	}
	
	/*public boolean DeleteEmployee (int matricule) {
		
		String req = "delete from employee  where Matricule  ="+ matricule;
		
		
		try {
			Statement st =connexion.getConx().createStatement();
			int nb =st.executeUpdate(req);
			st.close();
			System.out.println(nb);
			System.out.println("Supeission efectueé");
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return false;
	}
*/
	
	public boolean update(Employee s) {
		String req = "update  employee set nom=? , email=? ,anneRecruit=?,salaire=?,HSupp=?,PHSupp=? where matricule = ?";

		try {
			PreparedStatement ps = connexion.getConx().prepareStatement(req);
			ps.setString(1,s.getNom());
			ps.setString(2,s.getEmail());
			ps.setDouble(3,s.getAnneRecruit());
			ps.setDouble(4,s.getSalaire());
			ps.setDouble(5, s.getHSupp());
			ps.setDouble(6,s.getPHsupp());
			ps.setInt(7,s.getMatricule());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}

		return false;
	}
	
	
	public static void main(String[] args) {
		AppService S = new AppService();
		Employee E1= new Employee(147,"mouhamed", "mouhamed@email.com", 2016,1700,"employee", 10, 20);
		Employee E2= new Employee(369,"Habib", "habib@email.com", 2000,1700,"employee", 30, 50);
		Employee E3= new Employee(258,"Sami", "Sami@email.com", 2001,420,"employee", 13, 30);
		Employee E5= new Employee(789,"EmployeeName2","email@email.com",2012.0,280.0,"Employee",78,20);
		Employee E6= new Employee(789,"EmployeeName3","email@email.com",2013.0,280.0,"Employee",78,20);
		Vendeur  V1 = new Vendeur(45678,"Mariem","mariem@email.com", 2003, 420,"Vendeur", 11, 20);
		
		S.createEmployee(E3);
		S.createVendeur(V1);
		S.createEmployee(E2);
		/*S.createEmployee(E5);*/
		S.ListeSalarie();
		S.ListeEmployee();
		S.ListeVendeur();
		/*S.DeleteEmployee(E2);*/
		/*S.DeleteEmployee();*/
		/*S.DeleteSalarie(E4);*/
		 S.update(E6);

	}
}
