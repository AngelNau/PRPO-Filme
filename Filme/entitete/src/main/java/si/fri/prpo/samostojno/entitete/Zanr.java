package si.fri.prpo.samostojno.entitete;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "zanr")
@NamedQueries(value =
        {
                @NamedQuery(name = "Zanr.getAll", query = "SELECT z FROM zanr z")
        })
public class Zanr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer zanr_id;
    private String ime;

    public String getIme() {
        return ime;
    }

    public Integer getId() {
        return zanr_id;
    }

    public void setIme(String Ime) {
        this.ime = Ime;
    }
}
