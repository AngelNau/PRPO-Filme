package v1.viri;

import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
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
    @Operation(description = "Vrne seznam uporabnikov", summary = "Seznam uporabnikov")
    @APIResponses({
            @APIResponse(description = "Seznam uporabnikov", responseCode = "200", content = @Content(schema = @Schema(implementation = Uporabnik[].class)))
    })
    @GET
    public Response getUsers() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        Long numUporabniki = uporabnikiZrno.getNumUporabniki(query);
        return Response
                .ok(uporabnikiZrno.getUporabniki(query))
                .header("X-Total-Count", numUporabniki)
                .build();
    }
    @Operation(description = "Vrne uporabnik", summary = "Uporabnik")
    @APIResponses({
            @APIResponse(description = "Uporabnik", responseCode = "200", content = @Content(schema = @Schema(implementation = Uporabnik.class))),
            @APIResponse(description = "Uporabnik s tem id-jem ne obstaja!", responseCode = "404")
    })
    @GET
    @Path("{id}")
    public Response getUser(@PathParam("id") Integer id){
        try {
            Uporabnik user = uporabnikiZrno.getUporabnik(id);
            return Response
                    .status(Response.Status.FOUND)
                    .entity(user)
                    .build();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }
}
