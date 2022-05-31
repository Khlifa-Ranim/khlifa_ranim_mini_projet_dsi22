package application;

public class Employee extends Salarie  {

	private double HSupp;
	private double PHsupp;
	
	
  

	public Employee(int matricule, String nom, String email, double anneRecruit, double salaire, String role,
			double hSupp, double pHsupp) {
		super(matricule, nom, email, anneRecruit, salaire, role);
		HSupp = hSupp;
		PHsupp = pHsupp;
	}


	public double getHSupp() {
		return HSupp;
	}


	public void setHSupp(double hSupp) {
		HSupp = hSupp;
	}


	public double getPHsupp() {
		return PHsupp;
	}


	public void setPHsupp(double pHsupp) {
		PHsupp = pHsupp;
	}


	@Override
	public String toString() {
		return "Employee [HSupp=" + HSupp + ", PHsupp=" + PHsupp + "]";
	}
	
	
	
	
}
