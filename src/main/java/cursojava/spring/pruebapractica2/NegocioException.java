package cursojava.spring.pruebapractica2;

public class NegocioException extends Exception {

	public NegocioException() {
	}

	public NegocioException(String message, Throwable cause) {
		super(message, cause);
	}

	public NegocioException(String message) {
		super(message);
	}


}
