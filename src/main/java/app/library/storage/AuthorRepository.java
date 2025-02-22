package app.library.storage;

import app.library.config.HibernateConnectionManager;
import app.library.config.PropertyConfig;
import app.library.model.Author;
import app.library.config.DatabaseConnectionManager;
import app.library.exceptions.AuthorRepositoryException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class AuthorRepository implements AuthorRepositoryCustom<Author> {

    static {
        HibernateConnectionManager.initialize(new PropertyConfig());
    }
    SessionFactory sessionFactory = HibernateConnectionManager.getSessionFactory();

    @Override
    public void save(Author entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        } catch (HibernateException e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw new AuthorRepositoryException("Save didn't work. Please try again.", e);
        }

    }

    @Override
    public List<Author> findAll() {
        List<Author> authors;
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            authors = session.createQuery("from Author", Author.class).getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            throw new AuthorRepositoryException("Cannot get all authors. Please try again.", e);
        }
        return authors;
    }

    @Override
    public void delete(Author entity) {
        delete(entity.getId());
    }

    @Override
    public Author findById(long id) {
        Author author = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            author = session.get(Author.class, id);
            transaction.commit();
        } catch (HibernateException e) {
            throw new AuthorRepositoryException(String.format("Cannot find entity by ID %d.", id), e);
        }
        return author;
    }

    @Override
    public void saveAll(List<Author> entities) {
        entities.forEach(this::save);
    }

    @Override
    public void print() {
        findAll().forEach(System.out::println);
    }

    @Override
    public Author update(Author entity) {
        save(entity);
        return findById(entity.getId());
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(findById(id));
            transaction.commit();
        } catch (HibernateException e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw new AuthorRepositoryException(String.format("Cannot delete author's entity with id = %d", id), e);
        }
    }

    @Override
    public List<Author> findByEmail(String email) {
        List<Author> authors = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Author> query = builder.createQuery(Author.class);
            Root<Author> root = query.from(Author.class);

            query.select(root)
                    .where(builder.equal(root.get("email"), email));

            authors = session.createQuery(query).getResultList();
        } catch (HibernateException e) {
            throw new AuthorRepositoryException(String.format("Error when find author by email. %s", email), e);
        }
        return authors;
    }
}
