/**
 * @author: Edson A. Terceros T.
 */

package edu.umss.dip.ssiservice.controller;

import edu.umss.dip.ssiservice.dto.EmployeeDto;
import edu.umss.dip.ssiservice.model.Employee;
import edu.umss.dip.ssiservice.service.EmployeeService;
import edu.umss.dip.ssiservice.service.GenericService;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.List;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Controller
public class EmployeeController extends GenericController<Employee, EmployeeDto> {
    private EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @Override
    @GET
    public List<EmployeeDto> getAll() {
        return super.getAll();
    }


    @POST
    public EmployeeDto save(@RequestBody EmployeeDto employee) {
        return super.save(employee);
    }

    @DELETE
    public void delete(@PathParam("id") @NotNull Long id) {
        super.deleteElement(id);
    }


    @Override
    protected GenericService getService() {
        return service;
    }

    @Path("/{id}/image")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadFile(@PathParam("id") String id,
            @FormDataParam("file") InputStream file,
            @FormDataParam("file") FormDataContentDisposition fileDisposition) {
        service.saveImage(Long.valueOf(id), file);
        return Response.ok("Data uploaded successfully !!").build();
    }
    /*
    https://www.getpostman.com/collections/cb9764af6c5d5bcaa0c9
    */
}
