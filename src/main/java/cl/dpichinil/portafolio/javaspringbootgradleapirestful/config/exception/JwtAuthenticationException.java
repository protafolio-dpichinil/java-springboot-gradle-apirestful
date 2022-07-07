package cl.dpichinil.portafolio.javaspringbootgradleapirestful.config.exception;

public class JwtAuthenticationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public JwtAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtAuthenticationException(String message) {
        super(message);
    }
}
