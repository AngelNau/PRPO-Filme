package si.fri.prpo.samostojno.zrna;

import si.fri.prpo.samostojno.entitete.Film;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import com.kumuluz.ee.rest.utils.JPAUtils;
import com.kumuluz.ee.rest.beans.QueryParameters;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
public class FilmiZrno {
    private static final Logger log = Logger.getLogger(FilmiZrno.class.getName());
    @PersistenceContext(unitName = "priporocila-jpa")
    private EntityManager em;

    public List<Film> getFilmi() {
        TypedQuery<Film> query = em.createNamedQuery("Film.getAll", Film.class);
        return query.getResultList();
    }

    public List<Film> getFilmi(QueryParameters query) {
        return JPAUtils.queryEntities(em, Film.class, query);
    }

    public Long getNumFilmi(QueryParameters query) {
        return JPAUtils.queryEntitiesCount(em, Film.class, query);
    }

    public Film getFilm(int id) {
        TypedQuery<Film> query = em.createNamedQuery("Film.getFilm", Film.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Transactional
    public void putFilm(Film film) {
        try {
            em.persist(film);
        } catch(Exception e) {
            log.severe(e + " Couldn't create Film.");
        }
    }

    @Transactional
    public boolean changeRating(int filmId, int rating) {
        Film film = getFilm(filmId);
        if(film != null) {
            film.setRating(rating);
            em.persist(film);
            return true;
        }
        return false;
    }

    public boolean deleteFilm(int filmId) {
        Film film = getFilm(filmId);
        if(film != null) {
            em.remove(film);
            return true;
        }
        return false;
    }

    @PostConstruct
    public void init() {
        log.info("Filmi zrno ustvarjeno");
    }

    @PreDestroy
    public void destroy() {
        log.info("Filmi zrno uniceno");
    }
}
