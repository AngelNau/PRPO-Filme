package si.fri.prpo.samostojno;

import si.fri.prpo.samostojno.entitete.Film;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class FilmiZrno {

    @PersistenceContext(unitName = "priporocila-jpa")
    private EntityManager em;

    public List<Film> getFilmi() {
        TypedQuery<Film> query = em.createNamedQuery("Film.getAll", Film.class);
        Query test = em.createNamedQuery("Film.getAll");
        return query.getResultList();
    }
}
