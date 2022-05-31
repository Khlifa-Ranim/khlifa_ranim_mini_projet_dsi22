package application;

public class Salarie {
	
	private int Matricule;
	private String Nom;
	private String Email;
	private double AnneRecruit;
	private double Salaire;
	private String Role;
	
	
	


	public Salarie(int matricule, String nom, String email, double anneRecruit, double salaire, String role) {
		super();
		Matricule = matricule;
		Nom = nom;
		Email = email;
		AnneRecruit = anneRecruit;
		Salaire = salaire;
		Role = role;
	}


	
	
	public String getRole() {
		return Role;
	}




	public void setRole(String role) {
		Role = role;
	}




	public int getMatricule() {
		return Matricule;
	}


	public void setMatricule(int matricule) {
		Matricule = matricule;
	}


	public String getNom() {
		return Nom;
	}


	public void setNom(String nom) {
		Nom = nom;
	}


	public String getEmail() {
		return Email;
	}


	public void setEmail(String email) {
		Email = email;
	}


	public double getAnneRecruit() {
		return AnneRecruit;
	}


	public void setAnneRecruit(double anneRecruit) {
		AnneRecruit = anneRecruit;
	}


	public double getSalaire() {
		return Salaire;
	}


	public void setSalaire(double salaire) {
		Salaire = salaire;
	}


	@Override
	public String toString() {
		return "Salaire [Matricule=" + Matricule + ", Nom=" + Nom + ", Email=" + Email + ", AnneRecruit=" + AnneRecruit
				+ ", Salaire=" + Salaire + "]";
	}
	
	
	
	
	

}
