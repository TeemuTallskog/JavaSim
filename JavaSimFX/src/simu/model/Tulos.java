package simu.model;

import javax.persistence.*;

@Entity
@Table(name="tulos")
public class Tulos {
	
	@Column(name="asiakascount")
	private int asiakasCount;
	@Column(name="jakauma")
	private DistributionTyyppi distribution;
	@Column(name="ajoaika")
	private double ajoAika;
	@Column(name="palvaika")
	private double keskPalvAika;
	@Column(name="lapiaika")
	private double keskLapimenoAika;
	@Column(name="allcustomers")
	private int allCustomers;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	@Column
	private int firstArg;
	@Column
	private int secondArg;

	public Tulos() {

	}
	
	public void setAllCustomers(int i) {
		this.allCustomers = i;
	}
	
	public int getFirstArg() {
		return firstArg;
	}

	public void setFirstArg(int firstArg) {
		this.firstArg = firstArg;
	}

	public int getSecondArg() {
		return secondArg;
	}

	public void setSecondArg(int secondArg) {
		this.secondArg = secondArg;
	}

	public int getAllCustomers() {
		return allCustomers;
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

	public DistributionTyyppi getDistribution() {
		return distribution;
	}

	public void setDistribution(DistributionTyyppi distribution) {
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
