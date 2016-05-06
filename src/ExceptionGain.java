
public class ExceptionGain extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExceptionGain() {
		System.out.println("On ne peut appliquer le gain qu'à la fin d'une partie");
	}

}
