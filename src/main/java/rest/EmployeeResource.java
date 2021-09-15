package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.EmployeeDTO;
import facades.EmployeeFacade;
import facades.FacadeExample;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

//Todo Remove or change relevant parts before ACTUAL use
@Path("/employee")
public class EmployeeResource {

    @GET
    @Produces("text/plain")
    public String hello() {
        return "hello world!";
    }
    //url skal fx være api/employee/all
    @Path("/all")
    @GET
    @Produces("application/json")
    public String getAllEmployees(){
            EmployeeFacade facade = new EmployeeFacade();
            List<EmployeeDTO> emp = facade.getAllEmployees();
            return new Gson().toJson(emp);
    }
    //url skal fx være api/employee/id=1
    @Path("/id={id}")
    @GET
    @Produces("application/json")
    public String getEmployeeById(@PathParam("id") Long id){
        EmployeeFacade facade = new EmployeeFacade();
        EmployeeDTO emp = facade.getEmployeeById(id);
        return new Gson().toJson(emp);
    }

    //url skal fx være api/employee/highestpaid
    @Path("/highestpaid")
    @GET
    @Produces("application/json")
    public String getHighestSalary(){
        EmployeeFacade facade = new EmployeeFacade();
        List<EmployeeDTO> emp = facade.getEmployeeWithHighestSalary();
        return new Gson().toJson(emp);
    }

    //url skal fx være api/employee/name/elias
    @Path("/name/{name}")
    @GET
    @Produces("application/json")
    public String getEmployeeByName(@PathParam("name") String name){
        EmployeeFacade facade = new EmployeeFacade();
        List<EmployeeDTO> emp = facade.getEmployeesByName(name);
        return new Gson().toJson(emp);
    }

}
