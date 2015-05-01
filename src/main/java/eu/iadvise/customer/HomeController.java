package eu.iadvise.customer;

import java.io.File;

import javax.validation.Valid;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xml.sax.SAXException;

import eu.iadvise.customer.model.Customer;
import eu.iadvise.customer.model.Customers;
import eu.iadvise.customer.service.CustomerService;

@Controller
public class HomeController {
	
	private CustomerService customerService;
	
	@Autowired(required=true)
	@Qualifier(value="customerService")
	public void setCustomerService(CustomerService ps){
		this.customerService = ps;
	}
	
	@RequestMapping(value = {"/", "customers"}, method = RequestMethod.GET)
	public String listCustomers(Model model) {
		model.addAttribute("listCustomers", this.customerService.listCustomers());
		return "customers";
	}
	
	@RequestMapping(value= "/customer/add", method = RequestMethod.POST)
	public String addCustomer(@Valid @ModelAttribute("customer") Customer c,
	        BindingResult bindingResult){

    if (bindingResult.hasErrors()) {
        //return current page
        return "customer";
    }
		if(c.getId() == 0){
			this.customerService.addCustomer(c);
		}else{
			this.customerService.updateCustomer(c);
		}
		
		return "redirect:/customers";
		
	}
	
	@RequestMapping("/remove/{id}")
    public String removeCustomer(@PathVariable("id") int id){
		
        this.customerService.removeCustomer(id);
        return "redirect:/customers";
    }
 
    @RequestMapping("/edit/{id}")
    public String editCustomer(@PathVariable("id") int id, Model model){
        model.addAttribute("customer", this.customerService.getCustomerById(id));
        return "customer";
    }	
    
    @RequestMapping("/add")
    public String addCustomer(Model model){
        model.addAttribute("customer", new Customer());
        return "customer";
    }	

    @RequestMapping("/read")
    public String unMarshallCustomers()
    {
    	SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI); 
    	try {
	        Schema schema = sf.newSchema(new File("C:/Users/crememi/Documents/customers.xsd")); 
	 
	        JAXBContext jc = JAXBContext.newInstance(Customers.class);
	 
	        Unmarshaller unmarshaller = jc.createUnmarshaller();
	        unmarshaller.setSchema(schema);
	
	        try {
	        	Customers cust = (Customers) unmarshaller.unmarshal( new File("C:/Users/crememi/Documents/customers.xml") );
	            for(Customer c : cust.getCustomers())
	            {
	            	this.customerService.addCustomer(c);
	            }
	        } catch (UnmarshalException ue) {
	        	//Unmarshall exception
	        	System.err.println(ue);
	        }
    	} catch (SAXException se) {
    		//SAX exception
    		System.err.println(se);
    	} catch (JAXBException je) {
    		//JAXB exception
    		System.err.println(je);
    	}
        return "redirect:/customers";
    }
 
}
