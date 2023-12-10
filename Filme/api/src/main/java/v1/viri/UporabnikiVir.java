package v1.viri;

import si.fri.prpo.samostojno.entitete.Film;
import si.fri.prpo.samostojno.zrna.UporabnikiZrno;
import si.fri.prpo.samostojno.entitete.Uporabnik;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("uporabniki")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UporabnikiVir {
    @Inject
    private UporabnikiZrno uporabnikiZrno;

    @GET
    public Response getUsers() {
        List<Uporabnik> users = uporabnikiZrno.getUporabniki();
        return Response.status(Response.Status.OK).entity(users).build();
    }
    @GET
    @Path("{id}")
    public Response getUser(@PathParam("id") Integer id){
        return Response
                .status(Response.Status.CREATED)
                .entity(uporabnikiZrno.getUporabnik(id))
                .build();
    }
}
