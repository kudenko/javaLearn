package app.library.storage;

import app.library.config.HibernateConnectionManager;
import app.library.config.PropertyConfig;
import app.library.exceptions.JournalRepositoryException;
import app.library.exceptions.RepositoryException;
import app.library.model.Journal;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class JournalRepository implements JournalRepositoryCustom<Journal> {


    public JournalRepository() {
    }

    static {
        HibernateConnectionManager.initialize(new PropertyConfig());
    }

    SessionFactory sessionFactory = HibernateConnectionManager.getSessionFactory();

    @Override
    public void save(Journal entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            throw new JournalRepositoryException("Error in Journal save", e);
        }
    }

    @Override
    public List<Journal> findAll() {
        List<Journal> journals = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Journal> query = builder.createQuery(Journal.class);
            Root<Journal> root = query.from(Journal.class);
            query.select(root);
            journals =  session.createQuery(query).getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new RepositoryException("Error in find all journals", e);
        }
        return journals;
    }

    @Override
    public void delete(Journal entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
        } catch (HibernateException e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new JournalRepositoryException("Error in journal deletion", e);
        }
    }

    @Override
    public Journal findById(long id) {
        Journal journal = null;
        try (Session session = sessionFactory.openSession()) {
            journal = session.get(Journal.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new JournalRepositoryException(String.format("Error in find journal with id %d", id), e);
        }
        return journal;
    }

    @Override
    public void saveAll(List<Journal> entities) {
        entities.forEach(this::save);
    }

    @Override
    public void print() {
        findAll().forEach(System.out::println);
    }

    @Override
    public Journal update(Journal entity) {
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
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            throw new JournalRepositoryException(String.format("Error in journal deletion with id %d", id), e);
        }
    }

    @Override
    public List<Journal> findByNameYearNumber(String name, int year, int number) {
        List<Journal> journals = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Journal> query = builder.createQuery(Journal.class);
            Root<Journal> root = query.from(Journal.class);

            query.select(root)
                    .where(builder.and(
                            builder.equal(root.get("name"), name),
                            builder.equal(root.get("publicationYear"), year),
                            builder.equal(root.get("number"), number)
                    ));
            journals = session.createQuery(query).getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new JournalRepositoryException(String.format("Error in finding Journal with name %s, year %d and number %d", name, year, number), e);
        }
        return journals;
    }
}
