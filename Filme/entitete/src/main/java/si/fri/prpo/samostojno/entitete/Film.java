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
            @NamedQuery(name = "Film.removeFilm", query = "DELETE FROM film f WHERE f.id = :id"),
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

    public Integer getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public Integer getRating() {
        return rating;
    }

    public String getOpis() {
        return opis;
    }

    public Zanr getZanr() {
        return zanr;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setZanr(Zanr zanr) {
        this.zanr = zanr;
    }

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
