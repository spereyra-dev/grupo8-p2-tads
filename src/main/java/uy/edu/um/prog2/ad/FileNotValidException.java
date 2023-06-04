package uy.edu.um.prog2.ad;

public class FileNotValidException extends RuntimeException {
    public FileNotValidException(String fileGenerationError, Exception ex) {
        super(fileGenerationError, ex);
    }

}
