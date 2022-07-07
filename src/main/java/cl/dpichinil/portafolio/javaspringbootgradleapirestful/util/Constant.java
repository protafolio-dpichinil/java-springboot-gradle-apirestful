package cl.dpichinil.portafolio.javaspringbootgradleapirestful.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constant {
    public static final String REX_EMAIL = "^[A-Za-z0-9]+@+[A-Za-z0-9]+\\.+[A-Za-z]{2,}$";
    public static final String REX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*((\\S*[0-9]){2,}))(?=.*[.-_%\\/])\\S{2,8}$";

    public static final String CLAIM_KEY_CREATED = "created";
    public static final String KEY = "clave-de-encriptacion";
    public static final String CLAIM_KEY_USERNAME = "sub";
}
