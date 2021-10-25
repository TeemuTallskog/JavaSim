package simu.model;

public interface IDatabaseAccessObject {

	public Tulos[] haeTulokset();
	
	public Tulos haeTulos(int id);
	
	public boolean vieTulos(Tulos tulos);
	
	public int getHighID();
	
	public void truncateTables();
}
