package simu.model;

import javax.persistence.*;

@Entity
@Table(name="tulos")
public class Tulos {
	
	@Column(name="asiakascount")
	private int asiakasCount;
	@Column(name="jakauma")
	private String distribution;
	@Column(name="ajoaika")
	private double ajoAika;
	@Column(name="palvaika")
	private double keskPalvAika;
	@Column(name="lapiaika")
	private double keskLapimenoAika;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;

	public Tulos() {

	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setAjoAika(double ajoAika) {
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
