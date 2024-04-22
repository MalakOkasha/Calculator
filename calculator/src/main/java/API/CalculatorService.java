package API;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ejbs.Calculator;

@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("calc")
public class CalculatorService {

	
	@PersistenceContext(unitName="calc")
	private EntityManager entityManager;
	
	
	@POST
	public double createCalculationn(Calculator c) { 
		entityManager.persist(c);
		String operation = c.getOperation();
		double Result = 0;
		System.out.print("Result: ");
		
	     if(operation.equals("+")) 
	    	 {
	    	 Result = c.getNumber1() + c.getNumber2(); 
	    	 c.setResult(Result);
		     return c.getResult();
		    }
	     else if(operation.equals("-")) 
	    	 {
	    	 Result = c.getNumber1() - c.getNumber2();
	    	 c.setResult(Result);
		     return c.getResult();
		     }
	     else if(operation.equals("/")) 
	     {
	    	 if (c.getNumber2() != 0 )
	    	     {Result = c.getNumber1() / c.getNumber2(); 
	    	     c.setResult(Result);
			     return c.getResult(); }
	    	 else
	    		 throw new IllegalArgumentException("Cannot divide by zero, please change the value of number 2");
	     }
	     else if(operation.equals("*")) 
	     {
	    	 Result = c.getNumber1() * c.getNumber2(); 
	    	 c.setResult(Result);
		     return c.getResult();
	     }
	     else 
	     {
	    	 throw new IllegalArgumentException("invalid operation");
	    	
	     }
	}
	
	@GET
	@Path("calculations")
	public List<Calculator> getCalculation() {
		TypedQuery<Calculator> query = entityManager.createQuery("SELECT c FROM Calculator c", Calculator.class);
		List<Calculator> calcs = query.getResultList();

		return calcs;
	}
	
	
	
	

}
