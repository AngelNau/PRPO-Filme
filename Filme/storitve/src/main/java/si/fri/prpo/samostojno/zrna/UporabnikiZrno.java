package si.fri.prpo.samostojno.zrna;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.prpo.samostojno.entitete.Film;
import si.fri.prpo.samostojno.entitete.Uporabnik;
import si.fri.prpo.samostojno.izjeme.UserDoesNotExistException;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped
public class UporabnikiZrno {
    @Context
    protected UriInfo uriInfo;
    @PersistenceContext(unitName = "filme-jpa")
    private EntityManager em;

    public List<Uporabnik> getUporabniki() {
        TypedQuery<Uporabnik> query = em.createNamedQuery("Uporabnik.getAll", Uporabnik.class);
        return query.getResultList();
    }

    public List<Uporabnik> getUporabniki(QueryParameters query) {
        return JPAUtils.queryEntities(em, Uporabnik.class, query);
    }

    public Long getNumUporabniki(QueryParameters query) {
        return JPAUtils.queryEntitiesCount(em, Film.class, query);
    }

    public Uporabnik getUporabnik(int uporabnikId) {
        TypedQuery<Uporabnik> query = em.createNamedQuery("Uporabnik.getUporabnik", Uporabnik.class);
        query.setParameter("id", uporabnikId);
        Uporabnik user = query.getSingleResult();
        if (user == null) {
            throw new UserDoesNotExistException("Uporabnik ne obstaja.");
        }
        return query.getSingleResult();
    }
}
