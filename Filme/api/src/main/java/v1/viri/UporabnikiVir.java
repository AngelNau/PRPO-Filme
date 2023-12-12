package v1.viri;

import com.kumuluz.ee.rest.beans.QueryParameters;
import si.fri.prpo.samostojno.entitete.Uporabnik;
import si.fri.prpo.samostojno.zrna.UporabnikiZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("uporabniki")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UporabnikiVir {
    @Context
    protected UriInfo uriInfo;
    @Inject
    private UporabnikiZrno uporabnikiZrno;
    @GET
    public Response getUsers() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        Long numUporabniki = uporabnikiZrno.getNumUporabniki(query);
        return Response
                .ok(uporabnikiZrno.getUporabniki(query))
                .header("X-Total-Count", numUporabniki)
                .build();
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
