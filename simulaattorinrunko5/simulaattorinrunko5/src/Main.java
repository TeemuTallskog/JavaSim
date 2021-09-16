import simu.framework.Moottori;
import simu.framework.Trace;
import simu.framework.Trace.Level;
import simu.model.OmaMoottori;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trace.setTraceLevel(Level.INFO);
		Moottori m = new OmaMoottori();
		m.setSimulointiaika(10000);
		m.aja();
	}

}
