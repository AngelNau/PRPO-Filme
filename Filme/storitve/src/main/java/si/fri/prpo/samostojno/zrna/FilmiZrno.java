package si.fri.prpo.samostojno.zrna;

import si.fri.prpo.samostojno.entitete.Film;
import si.fri.prpo.samostojno.entitete.Zanr;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Query;
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
            log.severe(e.toString() + " Couldn't create Film.");
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
