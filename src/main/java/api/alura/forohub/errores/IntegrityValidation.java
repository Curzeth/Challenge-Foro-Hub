package api.alura.forohub.errores;

public class IntegrityValidation extends RuntimeException {
    public IntegrityValidation(String e) {
        super(e);
    }
}
