package facades;

import dtos.EmployeeDTO;
import dtos.MovieDTO;
import entities.Employee;
import entities.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static EntityManagerFactory emf;


    public MovieFacade() {}


    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void createMovie(Movie m){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Movie(m.getYear(), m.getTitle(), m.getActors()));
            em.getTransaction().commit();
        } finally {
            em.close();
            emf.close();
        }
    }

    public List<MovieDTO> getMovieById(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            TypedQuery<Movie> query = em.createQuery("select m from movie m where m.id = :id", Movie.class);
            query.setParameter("id", id);
            List<Movie> movies = query.getResultList();
            em.getTransaction().commit();
            return (List<MovieDTO>) (List<?>)movies;
        }
        finally
        {
            em.close();
            emf.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<MovieDTO> getAllMovies(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            TypedQuery<Movie> query = em.createQuery("SELECT m FROM movie m", Movie.class);
            List<Movie> movies = query.getResultList();
            em.getTransaction().commit();
            return (List<MovieDTO>)(List<?>) movies;
        }
        finally
        {
            em.close();
            emf.close();
        }
    }

    public List<MovieDTO> getMovieByTitle(String title){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            TypedQuery<Movie> query = em.createQuery("SELECT m FROM movie m where m.title = :title", Movie.class);
            query.setParameter("title", title);
            List<Movie> movie = query.getResultList();
            em.getTransaction().commit();
            return (List<MovieDTO>)(List<?>) movie;
        }
        finally
        {
            em.close();
            emf.close();
        }
    }

}
