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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class JournalRepository implements JournalRepositoryCustom<Journal> {


    public JournalRepository() {
    }

    static {
        HibernateConnectionManager.initialize(new PropertyConfig());
    }

    private static final SessionFactory sessionFactory = HibernateConnectionManager.getSessionFactory();
    private static final Logger logger = LoggerFactory.getLogger(JournalRepository.class);

    @Override
    public void save(Journal entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            logger.info("Journal save transaction start");
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
            logger.info("Journal save transaction successful");
        } catch (HibernateException e) {
            logger.error("Journal save transaction error {}", e.toString());
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new JournalRepositoryException("Error in Journal save", e);
        }
    }

    @Override
    public List<Journal> findAll() {
        List<Journal> journals = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            logger.info("Journal find all transaction start");
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Journal> query = builder.createQuery(Journal.class);
            Root<Journal> root = query.from(Journal.class);
            query.select(root);
            journals = session.createQuery(query).getResultList();
            logger.info("Journal find all transaction successful");
        } catch (HibernateException e) {
            logger.error("Journal find all transaction error {}", e.toString());
            throw new RepositoryException("Error in find all journals", e);
        }
        return journals;
    }

    @Override
    public void delete(Journal entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            logger.info("Journal delete transaction start");
            transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
            logger.info("Journal delete transaction successful");
        } catch (HibernateException e) {
            logger.error("Journal delete transaction error {}", e.toString());
            if (transaction != null) {
                transaction.rollback();
            }
            throw new JournalRepositoryException("Error in journal deletion", e);
        }
    }

    @Override
    public Journal findById(long id) {
        Journal journal = null;
        try (Session session = sessionFactory.openSession()) {
            logger.info("Journal find by id {} transaction start", id);
            journal = session.get(Journal.class, id);
            logger.info("Journal find by id {} transaction successful", id);
        } catch (HibernateException e) {
            logger.error("Journal find by id {} transaction error {}", id, e.toString());
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
            logger.info("Journal delete by id {} transaction start", id);
            transaction = session.beginTransaction();
            session.remove(findById(id));
            transaction.commit();
            logger.info("Journal delete by id {} transaction successful", id);
        } catch (HibernateException e) {
            logger.error("Journal delete by id {} transaction successful error {}", id, e.toString());
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
            logger.info("Journal find by name {}, year {}, number {} transaction start", name, year, number);
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
            logger.info("Journal find by name {}, year {}, number {} transaction successful", name, year, number);
        } catch (HibernateException e) {
            logger.error("Journal find by name {}, year {}, number {} transaction error {}", name, year, number, e.toString());
            throw new JournalRepositoryException(String.format("Error in finding Journal with name %s, year %d and number %d", name, year, number), e);
        }
        return journals;
    }
}
