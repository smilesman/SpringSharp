package lab.sharp.core.exception;

public class WebException extends BaseRuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = -1234054104781881035L;

	public WebException(String msg) {
        super(msg);
    }

    public WebException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
