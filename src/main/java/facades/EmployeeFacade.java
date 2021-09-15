package facades;

import dtos.EmployeeDTO;
import dtos.RenameMeDTO;
import entities.Employee;
import entities.RenameMe;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class EmployeeFacade {

    private static EntityManagerFactory emf;


    public EmployeeFacade() {}


    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void createEmployee(EmployeeDTO e){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Employee(e.getName(), e.getAddress(), e.getSalary()));
            em.getTransaction().commit();
        } finally {
            em.close();
            emf.close();
        }
    }
    public EmployeeDTO getEmployeeById(long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            TypedQuery<Employee> query = em.createQuery("select e from Employee e where e.id = :id", Employee.class);
            query.setParameter("id", id);
            Employee emp = query.getSingleResult();
            em.getTransaction().commit();
            return new EmployeeDTO(emp);
        }
        finally
        {
            em.close();
            emf.close();
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<EmployeeDTO> getEmployeeWithHighestSalary(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            TypedQuery<Employee> query = em.createQuery("select e from Employee e where e.salary = (select max(e.salary) from Employee e)", Employee.class);
            List<Employee> emp = query.getResultList();
            em.getTransaction().commit();
            return (List<EmployeeDTO>)(List<?>) emp;
        }finally{  
            em.close();
            emf.close();
        }
    }
    @SuppressWarnings("unchecked")
    public List<EmployeeDTO> getAllEmployees(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
            List<Employee> emp = query.getResultList();
            em.getTransaction().commit();
            return (List<EmployeeDTO>)(List<?>) emp;
        }
        finally
        {
            em.close();
            emf.close();
        }
    }

    public List<EmployeeDTO> getEmployeesByName(String name){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            TypedQuery<Employee> query = em.createQuery("SELECT e.name FROM Employee e where e.name = :name", Employee.class);
            query.setParameter("name", name);
            List<Employee> emp = query.getResultList();
            em.getTransaction().commit();
            return (List<EmployeeDTO>)(List<?>) emp;
        }
        finally
        {
            em.close();
            emf.close();
        }
    }

}
