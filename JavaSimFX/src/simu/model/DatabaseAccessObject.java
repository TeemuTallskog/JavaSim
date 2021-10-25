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
		Session session = sf.openSession();
		try {
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
			e.printStackTrace();
			System.err.println("Session factory build failed.");
			System.exit(-1);
		}
	}
	
	/**
	 * Used to wipe the database
	 */
	@Override
	public void truncateTables() {
		buildSF();
		Session session = sf.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.createSQLQuery("truncate table Tulos").executeUpdate();
			transaction.commit();
			sf.close();
		}catch(Exception e) {
			if(transaction != null)transaction.rollback();
			System.out.println("Emptying database did not succeed");
		}
	}

}
