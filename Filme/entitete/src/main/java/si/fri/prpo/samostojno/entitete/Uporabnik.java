package si.fri.prpo.samostojno.entitete;

import javax.persistence.*;

@Entity(name = "Uporabnik")
@org.hibernate.annotations.NamedQueries(value =
        {
                @org.hibernate.annotations.NamedQuery(name = "Uporabnik.getAll", query = "SELECT u FROM Uporabnik u"),
                @org.hibernate.annotations.NamedQuery(name = "Uporabnik.getUporabnik", query = "SELECT u FROM Uporabnik u WHERE u.id = :id"),
        })
public class Uporabnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @Column(name = "Ime", nullable = false)
    private String ime;

    @Column(name = "Priimek", nullable = false)
    private String priimek;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Starost", nullable = false)
    private Integer starost;

    public Integer getId() {
        return user_id;
    }

    public String getIme() {
        return ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public String getEmail() {
        return email;
    }

    public Integer getStarost() { return starost; }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStarost(Integer starost) {
        this.starost = starost;
    }
}
