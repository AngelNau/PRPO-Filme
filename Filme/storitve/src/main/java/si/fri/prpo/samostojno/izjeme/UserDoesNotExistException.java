package si.fri.prpo.samostojno.izjeme;

public class UserDoesNotExistException extends RuntimeException{
    public UserDoesNotExistException(String msg) {
        super(msg);
    }
}
