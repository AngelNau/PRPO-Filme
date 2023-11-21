package si.fri.prpo.samostojno.zrna;

import si.fri.prpo.samostojno.entitete.Uporabnik;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class UporabnikiZrno {
    private static final Logger log = Logger.getLogger(FilmiZrno.class.getName());
    @PersistenceContext(unitName = "priporocila-jpa")
    private EntityManager em;

    public List<Uporabnik> getUporabniki() {
        TypedQuery<Uporabnik> query = em.createNamedQuery("Uporabnik.getAll", Uporabnik.class);
        return query.getResultList();
    }

    public Uporabnik getUporabnik(int uporabnikId) {
        TypedQuery<Uporabnik> query = em.createNamedQuery("Uporabnik.getUporabnik", Uporabnik.class);
        query.setParameter("id", uporabnikId);
        return query.getSingleResult();
    }
}
