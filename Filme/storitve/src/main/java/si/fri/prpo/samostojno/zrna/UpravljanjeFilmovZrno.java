package si.fri.prpo.samostojno.zrna;

import si.fri.prpo.samostojno.dtos.FilmDto;
import si.fri.prpo.samostojno.entitete.Film;
import si.fri.prpo.samostojno.entitete.Zanr;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

public class UpravljanjeFilmovZrno {
    private static final Logger log = Logger.getLogger(FilmiZrno.class.getName());
    @Inject
    FilmiZrno filmiZrno;
    @Inject
    UporabnikiZrno uporabnikiZrno;
    @Inject
    ZanriZrno zanriZrno;

    public boolean addFilm(FilmDto filmDto) {
        if(filmDto.getIme().isEmpty()) {
            log.severe("Please state name of film.");
            return false;
        }
        Film film = new Film();
        film.setIme(filmDto.getIme());
        film.setOpis(film.getOpis());
        film.setRating(filmDto.getRating());
        if(!filmDto.getZanr().isEmpty()) { zanriZrno.putZanr(filmDto.getIme()); }
        filmiZrno.putFilm(film);
        return true;
    }
}
