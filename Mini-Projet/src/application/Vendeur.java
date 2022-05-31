package application;

public class Vendeur  extends Salarie{
	
	private double  Vente;
	private double Pourcentage;





	public Vendeur(int matricule, String nom, String email, double anneRecruit, double salaire, String role,
			double vente, double pourcentage) {
		super(matricule, nom, email, anneRecruit, salaire, role);
		Vente = vente;
		Pourcentage = pourcentage;
	}


	public double getVente() {
		return Vente;
	}


	public void setVente(double vente) {
		Vente = vente;
	}


	public double getPourcentage() {
		return Pourcentage;
	}


	public void setPourcentage(double pourcentage) {
		Pourcentage = pourcentage;
	}


	@Override
	public String toString() {
		return "Vendeur [Vente=" + Vente + ", Pourcentage=" + Pourcentage + "]";
	}
	
	
	

}
