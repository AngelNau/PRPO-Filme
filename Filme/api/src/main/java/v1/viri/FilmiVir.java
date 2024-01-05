package v1.viri;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import si.fri.prpo.samostojno.entitete.Film;
import si.fri.prpo.samostojno.zrna.FilmiZrno;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
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
    private final ConfigurationUtil configuration = ConfigurationUtil.getInstance();
    private Client httpClient;
    private String apiUrl;
    private JSONParser parser;

    @PostConstruct
    public void init() {
        httpClient = ClientBuilder.newClient();
        apiUrl = configuration.get("zunanjiApi").get();
        parser = new JSONParser();
    }

    @GET
    @Operation(description = "Vrne seznam filmov", summary = "Seznam filmov")
    @APIResponses({
            @APIResponse(description = "Seznam filmov", responseCode = "200", content = @Content(schema = @Schema(implementation = Film[].class)))
    })
    public Response getFilms() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        Long numFilms = filmiZrno.getNumFilmi(query);
        return Response
                .ok(filmiZrno.getFilmi(query))
                .header("X-Total-Count", numFilms)
                .build();
    }

    @Operation(description = "Vrne film z določen id", summary = "Film")
    @APIResponses({
            @APIResponse(description = "Film z določen id", responseCode = "200", content = @Content(schema = @Schema(implementation = Film.class))),
            @APIResponse(description = "Film s tem id-jem ne obstaja!", responseCode = "404")
    })
    @GET
    @Path("{id}")
    public Response getFilm(@PathParam("id") Integer id) {
        Film film = filmiZrno.getFilm(id);
        if(film == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        return Response
                .status(Response.Status.OK)
                .entity(filmiZrno.getFilm(id))
                .build();
    }

    @Operation(description = "Spremeni rating filma", summary = "Spremeni rating")
    @APIResponses({
            @APIResponse(description = "Spremenjen rating filma!", responseCode = "200", content = @Content(schema = @Schema(implementation = Film.class))),
            @APIResponse(description = "Rating mora biti med 0 in 10!", responseCode = "400"),
            @APIResponse(description = "Film s tem id-jem ne obstaja!", responseCode = "404")
    })
    @POST
    @Path("changeRating/{id}&{rating}")
    public Response changeRating(@PathParam("id") Integer id, @PathParam("rating") Integer rating) {
        if (rating > 10 || rating < 0) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }
        boolean filmChanged = filmiZrno.changeRating(id, rating);
        if(filmChanged) {
            return Response
                    .ok(filmiZrno.getFilm(id))
                    .build();
        }
        return Response
                .status(Response.Status.NOT_FOUND)
                .build();
    }

    @Operation(description = "Odstrani film", summary = "Odstrani film")
    @APIResponses({
            @APIResponse(description = "Ostranjen film s tem id-jem!", responseCode = "200", content = @Content(schema = @Schema(implementation = Film.class))),
            @APIResponse(description = "Film s tem id-jem ne obstaja!", responseCode = "404")
    })
    @DELETE
    @Path("removeFilm/{id}")
    public Response removeFilm(@PathParam("id") Integer id) {
        Film film = filmiZrno.getFilm(id);
        if (film == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        return Response
                .status(Response.Status.OK)
                .entity(filmiZrno.deleteFilm(id))
                .build();
    }

    @Operation(description = "Dobi podatke o filmu", summary = "Podatki o filmu")
    @APIResponses({
            @APIResponse(description = "Podatki o filmu", responseCode = "200", content = @Content(schema = @Schema(implementation = Film.class))),
            @APIResponse(description = "Slaba zahteva!", responseCode = "400")
    })
    @GET
    @Path("getMovieData/{name}")
    public Response getFilmData(@PathParam("name") String name) throws ParseException {
        Response rez =  httpClient
                .target(apiUrl + "?q=" + name)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE).get();
        try {
            String body = rez.readEntity(String.class);
            JSONObject res = (JSONObject) parser.parse(body);
            JSONObject desc = (JSONObject) ((JSONArray)res.get("description")).get(0);
            return Response
                    .ok(desc)
                    .build();
        } catch(Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }
    }
}