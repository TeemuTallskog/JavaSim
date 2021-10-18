import java.util.ArrayList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Tulos;

public class AlustaLuokat {
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("simu");
	
	private ArrayList<Tulos> tulosLista = new ArrayList<>();
	
	//Konstruktori luo yhteyden tietokantaan ja luo listan tuloksista.
	//Jos yhteys epäonnistuu niin printtaa virhekoodit.
	public AlustaLuokat () {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT c FROM tulokset WHERE c.id IS NOT NULL";
		
		TypedQuery<Tulos> tq = em.createQuery(query, Tulos.class);
		List<simu.model.Tulos> tulokset;
		
		
		//Jos yhteys onnistuu niin tuloslista täytetään tietokannasta
		//saaduista tiedoista. Vielä ei ole logiikkaa tulosten 
		//Lisäämiselle tietokantaan.
		try {
			tulokset = tq.getResultList();
			tulokset.forEach(tulos -> tuloksLista.add(new Tulos(tulos.getAjoAika(), tulos.getAsiakasCount(), tulos.getDistribution(), tulos.getId(), tulos.getKeskLapimenoAika(), tulos.getKeskPalvAika())));
			
		}
		
		catch(NoResultException ex) {
			ex.printStackTrace();
		}
		
		finally {
			em.close();
		}
	}
	//Palauttaa tulokset.
	public ArrayList<Tulos> getTulosLista() {return tulosLista();}
}
