package eu.iadvise.customer.dao;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import eu.iadvise.customer.model.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addCustomer(Customer c) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Customer>> constraintViolations = validator.validate(c);
		if (constraintViolations.isEmpty()) {
			Session session = this.sessionFactory.getCurrentSession();
			session.persist(c);
			logger.info("Customer saved successfully, Customer Details="+c);
		}
		else {
			logger.warn("Customer invalid, Customer Details="+c);
		}
	}

	@Override
	public void updateCustomer(Customer c) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Customer>> constraintViolations = validator.validate(c);
		if (constraintViolations.isEmpty()) {
			Session session = this.sessionFactory.getCurrentSession();
			session.update(c);
			logger.info("Customer updated successfully, Customer Details="+c);
		}
		else {
			logger.warn("Customer invalid, Customer Details="+c);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> listCustomers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Customer> customersList = session.createQuery("from Customer").list();
		for(Customer c : customersList){
			logger.info("Customer List::"+c);
		}
		return customersList;
	}

	@Override
	public Customer getCustomerById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Customer c = (Customer) session.load(Customer.class, new Integer(id));
		logger.info("Customer loaded successfully, Customer details="+c);
		return c;
	}

	@Override
	public void removeCustomer(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Customer c = (Customer) session.load(Customer.class, new Integer(id));
		if(null != c){
			session.delete(c);
		}
		logger.info("Customer deleted successfully, customer details="+c);
	}

}
