package si.fri.prpo.samostojno.zrna;

import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

@ApplicationScoped
public class BelezenjeKlicevZrno {
    private static int counter = 0;
    private final Logger log = Logger.getLogger(BelezenjeKlicevZrno.class.getName());

    public int increaseCounter(){
        counter++;
        log.info("Belezenje klicev counter: " + counter);
        return counter;
    }
}
