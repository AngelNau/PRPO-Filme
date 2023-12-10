package v1.viri;

import si.fri.prpo.samostojno.entitete.Film;
import si.fri.prpo.samostojno.zrna.FilmiZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("filmi")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class FilmiVir {
    @Inject
    private FilmiZrno filmiZrno;

    @GET
    public Response getFilms() {
        List<Film> films = filmiZrno.getFilmi();
        return Response.status(Response.Status.OK).entity(films).build();
    }

    @GET
    @Path("{id}")
    public Response getFilm(@PathParam("id") Integer id){
        return Response
                .status(Response.Status.CREATED)
                .entity(filmiZrno.getFilm(id))
                .build();
    }

    @GET
    @Path("changeRating/{id}&{rating}")
    public Response changeRating(@PathParam("id") Integer id, @PathParam("rating") Integer rating) {
        return Response
                .status(Response.Status.OK)
                .entity(filmiZrno.changeRating(id, rating))
                .build();
    }

    @DELETE
    @Path("removeFilm/{id}")
    public Response removeFilm(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(filmiZrno.deleteFilm(id))
                .build();
    }
}


