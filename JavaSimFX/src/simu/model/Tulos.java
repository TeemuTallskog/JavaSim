package simu.model;

public class Tulos {
	private int asiakasCount;
	private String distribution;
	private double ajoAika;
	private double keskPalvAika;
	private double keskLapimenoAika;

	public Tulos() {

	}

	public int getAsiakasCount() {
		return asiakasCount;
	}

	public void setAsiakasCount(int asiakasCount) {
		this.asiakasCount = asiakasCount;
	}

	public String getDistribution() {
		return distribution;
	}

	public void setDistribution(String distribution) {
		this.distribution = distribution;
	}

	public double getAjoAika() {
		return ajoAika;
	}

	public void setAjoAika(long ajoAika) {
		this.ajoAika = ajoAika;
	}

	public double getKeskPalvAika() {
		return keskPalvAika;
	}

	public void setKeskPalvAika(double keskPalvAika) {
		this.keskPalvAika = keskPalvAika;
	}

	public double getKeskLapimenoAika() {
		return keskLapimenoAika;
	}

	public void setKeskLapimenoAika(double keskLapimenoAika) {
		this.keskLapimenoAika = keskLapimenoAika;
	}

}
