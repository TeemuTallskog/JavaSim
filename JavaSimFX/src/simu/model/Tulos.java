package simu.model;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="tulokset")
public class Tulos implements Serializable{
	
	@Column(name="asiakasCount")
	private int asiakasCount;
	@Column(name="distribution")
	private String distribution;
	@Column(name="ajoAika")
	private double ajoAika;
	@Column(name="keskPalvAika")
	private double keskPalvAika;
	@Column(name="keskLapimenoAika")
	private double keskLapimenoAika;
	@Id
	@Column(name="tulosID", unique = true)
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
