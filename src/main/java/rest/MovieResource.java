package rest;

import com.google.gson.Gson;
import dtos.EmployeeDTO;
import dtos.MovieDTO;
import facades.EmployeeFacade;
import facades.MovieFacade;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

//Todo Remove or change relevant parts before ACTUAL use
@Path("/movie")
public class MovieResource {

    @GET
    @Produces("text/plain")
    public String hello() {
        return "hello world!";
    }
    //url skal fx være api/movie/all
    @Path("/all")
    @GET
    @Produces("application/json")
    public String getAllMovies(){
        MovieFacade facade = new MovieFacade();
            List<MovieDTO> md = facade.getAllMovies();
            return new Gson().toJson(md);
    }
    //url skal fx være api/movie/1
    @Path("/{id}")
    @GET
    @Produces("application/json")
    public String getMovieById(@PathParam("id") Integer id){
        MovieFacade facade = new MovieFacade();
        List<MovieDTO> md = facade.getMovieById(id);
        return new Gson().toJson(md);
    }

    //url skal fx være api/movie/title/The Matrix
    @Path("/title/{title}")
    @GET
    @Produces("application/json")
    public String getMovieByTitle(@PathParam("title") String title){
        MovieFacade facade = new MovieFacade();
        List<MovieDTO> md = facade.getMovieByTitle(title);
        return new Gson().toJson(md);
    }

}
