package facades;

import dtos.EmployeeDTO;
import dtos.RenameMeDTO;
import entities.Employee;
import entities.RenameMe;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private EmployeeFacade() {}


    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public EmployeeDTO createEmployee(Employee emp){
        Employee employee = new Employee(emp.getName(),emp.getAddress(), emp.getSalary());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new EmployeeDTO(employee);
    }
    public EmployeeDTO getEmployeeById(long id){
        EntityManager em = emf.createEntityManager();
        return new EmployeeDTO(em.find(Employee.class, id));
    }
    

    public List<EmployeeDTO> getEmployeeWithHighestSalary(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Employee> query = em.createQuery("select e from Employee e where e.salary = (select max(e.salary) from Employee e)", Employee.class);
            List<Employee> emp = query.getResultList();
            return EmployeeDTO.getDtos(emp);
        }finally{  
            em.close();
        }
    }
    
    public List<EmployeeDTO> getAllEmployees(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        List<Employee> emp = query.getResultList();
        return EmployeeDTO.getDtos(emp);
    }

    public List<EmployeeDTO> getEmployeesByName(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> query = em.createQuery("SELECT e.name FROM Employee e", Employee.class);
        List<Employee> emp = query.getResultList();
        return EmployeeDTO.getDtos(emp);
    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        EmployeeFacade fe = getEmployeeFacade(emf);
        fe.getAllEmployees().forEach(dto->System.out.println(dto));
    }

}
