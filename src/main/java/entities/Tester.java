package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class Tester {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        Employee e1 = new Employee("ole", "bilka", 30000);
        Employee e2 = new Employee("jens", "netto", 25000);
        Employee e3 = new Employee("lars", "lidl", 20000);
        List<String> matrixActors = new ArrayList<>();
        List<String> avengersActors = new ArrayList<>();
        matrixActors.add("Keanu Reeves");
        matrixActors.add("Laurence Fishburne");
        avengersActors.add("Robert Downey Jr.");
        avengersActors.add("Chris Evans");
        avengersActors.add("Mark Ruffalo");

    try {
        em.getTransaction().begin();
        em.persist(e1);
        em.persist(e2);
        em.persist(e3);
        em.persist(new Movie("The Matrix", 1999, matrixActors));
        em.persist(new Movie("The Avengers", 2012, avengersActors));
        em.getTransaction().commit();

    }
    finally {
        em.close();
        emf.close();
    }

    }
}
