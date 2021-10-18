package simu.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class DatabaseAccessObject implements IDatabaseAccessObject {
	
	private SessionFactory sf = null;

	@Override
	public Tulos[] haeTulokset() {
		buildSF();
		try (Session session = sf.openSession()) {
			List result = session.createQuery("from Tulos").list();
			Tulos[] list = new Tulos[result.size()];
			result.toArray(list);
			sf.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Tulos haeTulos(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean vieTulos(Tulos tulos) {
		buildSF();
		Transaction transaction = null;
		try (Session session = sf.openSession()) {
			transaction = session.beginTransaction();
			session.saveOrUpdate(tulos);
			transaction.commit();
			sf.close();
			return true;
		} catch (Exception e) {
			if (transaction != null) transaction.rollback();
			return false;
		}	
	}

	@Override
	public int getHighID() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private void buildSF() {
		try {
			sf = new Configuration().configure().buildSessionFactory();
		} catch (Exception e) {
			System.err.println("Session factory build failed.");
			System.exit(-1);
		}
	}

}
