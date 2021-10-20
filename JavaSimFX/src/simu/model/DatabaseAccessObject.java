package simu.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class DatabaseAccessObject implements IDatabaseAccessObject {

	// Luodaan istuntotehdas
		SessionFactory istuntotehdas = null;
	
	public DatabaseAccessObject() {
		try {
			istuntotehdas = new Configuration().configure().buildSessionFactory();

		} catch (Exception e) {
			System.out.println("Istunnon luominen ei onnistunut. " + e);
			System.exit(-1);
		}

		System.out.println("Istunnon luominen onnistui. ");
	}
	
	Transaction transaktio = null;
	
	@Override
	public Tulos[] haeTulokset() {
		
		ArrayList<Tulos> lista = new ArrayList<Tulos>();
		try (Session istunto = istuntotehdas.openSession()) {
			transaktio = istunto.beginTransaction();
			List result = istunto.createQuery("from tulokset").list();
			for (Tulos t : (List<Tulos>) result) {
				lista.add(t);
			}
			transaktio.commit();
		} catch (Exception e) {
			System.out.println("Virhe tuloksia haettaessa." + e);
			transaktio.rollback();
		}
		Tulos[] palautettavaTaulu = new Tulos[lista.size()];
		palautettavaTaulu = (Tulos[]) lista.toArray(palautettavaTaulu);

		for (Tulos t : palautettavaTaulu) {
			System.out.println(t);
		}

		return palautettavaTaulu;
	}

	@Override
	public Tulos haeTulos(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void vieTulos(Tulos tulos) {
		// TODO Auto-generated method stub

	}

}
