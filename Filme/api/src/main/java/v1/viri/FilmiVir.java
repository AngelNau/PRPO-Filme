package v1.viri;

import com.kumuluz.ee.rest.beans.QueryParameters;
import si.fri.prpo.samostojno.zrna.FilmiZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("filmi")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class FilmiVir {
    @Context
    protected UriInfo uriInfo;
    @Inject
    private FilmiZrno filmiZrno;

    @GET
    public Response getFilms() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        Long numFilms = filmiZrno.getNumFilmi(query);
        return Response
                .ok(filmiZrno.getFilmi(query))
                .header("X-Total-Count", numFilms)
                .build();
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


