package si.fri.prpo.samostojno.entitete;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;


@Entity(name = "film")
@NamedQueries(value =
    {
            @NamedQuery(name = "Film.getAll", query = "SELECT f FROM film f"),
            @NamedQuery(name = "Film.getName", query = "SELECT f.ime FROM film f WHERE f.id = :id"),
            @NamedQuery(name = "Film.getFilm", query = "SELECT f FROM film f WHERE f.id = :id")
    })
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ime;
    private String opis;
    private Integer rating;
    @ManyToOne
    @JoinColumn(name = "zanr_id")
    private Zanr zanr;
    @Override
    public String toString() {
        String str = "Ime " +
                this.ime +
                " Opis: " +
                this.opis +
                " Rating: " +
                this.rating +
                " Zanr: " +
                this.zanr.getIme();
        return str;
    }
}
