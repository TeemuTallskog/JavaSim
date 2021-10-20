package simu.model;

public interface IDatabaseAccessObject {

	public Tulos[] haeTulokset();
	
	public Tulos haeTulos(int id);
	
	public void vieTulos(Tulos tulos);
}
