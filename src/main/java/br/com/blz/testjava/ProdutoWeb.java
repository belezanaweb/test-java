package br.com.blz.testjava;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.blz.model.ProdutoModel;
import br.com.blz.service.ServiceFacade;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON+";charset=utf-8")
public class ProdutoWeb {

	private static final String URL_SERVICE = "/produtos/";
	private ServiceFacade service = new ServiceFacade();


	@GET
	@Path("/lista")
	public List<ProdutoModel> getAllProduto() {		
		return service.get();
	}
	
	@GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") Integer id) throws URISyntaxException{
		ProdutoModel produto = service.get(id);
		 
        if(produto == null) {
            return Response.status(404).build();
        }
        return Response
                .status(200)
                .entity(produto)
                .contentLocation(new URI(URL_SERVICE+id)).build();
    }
	
	
	@PUT
	@Path("/put")
	public Response put(ProdutoModel produtoEditado) throws URISyntaxException{
		ProdutoModel produtoDesatualizado = service.get(produtoEditado.getSku());
		
		if(produtoDesatualizado !=  null) {
			service.update(produtoEditado);
			return Response
	                .status(200)
	                .entity(produtoDesatualizado)
	                .contentLocation(new URI(URL_SERVICE+produtoEditado.getSku())).build();
        }
         return Response.status(404).build();
	}
	
	@POST
    @Consumes("application/json")
	public Response add(ProdutoModel produtoNovo) throws URISyntaxException{
		ProdutoModel cadastrado = service.get(produtoNovo.getSku());
		
		if(cadastrado == null) {
			service.add(produtoNovo);
			return  Response.status(201).contentLocation(new URI(URL_SERVICE+produtoNovo.getSku())).build();
            
        }		
		return Response.status(404).build();
		
        
	}
	
	@DELETE
    @Path("/{id}")
    public Response del(@PathParam("id") Integer id) throws URISyntaxException{
		ProdutoModel produto = service.get(id);
		 
		if(produto !=  null) {
			service.delete(produto);
			return Response
	                .status(200).build();
        }
         return Response.status(404).build();
    }
	
	
}
