package si.fri.prpo.samostojno.zrna;

import si.fri.prpo.samostojno.entitete.Zanr;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class ZanriZrno {
    private static final Logger log = Logger.getLogger(FilmiZrno.class.getName());

    @PersistenceContext(unitName = "filme-jpa")
    private EntityManager em;

    public List<Zanr> getZanri() {
        TypedQuery<Zanr> query = em.createNamedQuery("Zanr.getAll", Zanr.class);
        return query.getResultList();
    }

    @Transactional
    public boolean putZanr(String ime) {
        if(ime.isEmpty()) {
            log.severe("Please insert genre name.");
            return false;
        }
        Zanr zanr = new Zanr();
        zanr.setIme(ime);
        em.persist(zanr);
        return true;
    }

    @PostConstruct
    public void init() {
        log.info("Zanri zrno ustvarjeno");
    }

    @PreDestroy
    public void destroy() {
        log.info("Zanri zrno uniceno");
    }
}
