package app.library.storage;

import app.library.config.HibernateConnectionManager;
import app.library.config.PropertyConfig;
import app.library.model.Author;
import app.library.exceptions.AuthorRepositoryException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AuthorRepository implements AuthorRepositoryCustom<Author> {

    static {
        HibernateConnectionManager.initialize(new PropertyConfig());
    }
    private static final Logger logger = LoggerFactory.getLogger(AuthorRepository.class);
    private static final SessionFactory sessionFactory = HibernateConnectionManager.getSessionFactory();

    @Override
    @Transactional
    public void save(Author entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            logger.info("Author save transaction start");
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
            logger.info("Author save transaction successful");
        } catch (HibernateException e) {
            logger.error("Author save transaction error {}", e.toString());
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new AuthorRepositoryException("Save didn't work. Please try again.", e);
        }

    }

    @Override
    public List<Author> findAll() {
        List<Author> authors;
        try (Session session = sessionFactory.openSession()) {
            logger.info("Author find all transaction start");
            Transaction transaction = session.beginTransaction();
            authors = session.createQuery("from Author", Author.class).getResultList();
            transaction.commit();
            logger.info("Author find all transaction successful");
        } catch (HibernateException e) {
            logger.error("Author find all transaction error {}", e.toString());
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
            logger.info("Author find by id {} transaction start", id);
            Transaction transaction = session.beginTransaction();
            author = session.get(Author.class, id);
            transaction.commit();
            logger.info("Author find by id {} transaction successful", id);
        } catch (HibernateException e) {
            logger.error("Author find by id {} transaction error {}", id, e.toString());
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
            logger.info("Author delete by id {} transaction start", id);
            transaction = session.beginTransaction();
            session.remove(findById(id));
            transaction.commit();
            logger.info("Author delete by id {} transaction successful", id);
        } catch (HibernateException e) {
            logger.error("Author delete by id {} transaction error {}", id,  e.toString());
            if (transaction != null) {
                transaction.rollback();
            }
            throw new AuthorRepositoryException(String.format("Cannot delete author's entity with id = %d", id), e);
        }
    }

    @Override
    public List<Author> findByEmail(String email) {
        List<Author> authors = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            logger.info("Author find by email {} transaction start", email);
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Author> query = builder.createQuery(Author.class);
            Root<Author> root = query.from(Author.class);

            query.select(root)
                    .where(builder.equal(root.get("email"), email));

            authors = session.createQuery(query).getResultList();
            logger.info("Author find by email {} transaction successful", email);
        } catch (HibernateException e) {
            logger.info("Author find by email {} transaction error {}", email, e.toString());
            throw new AuthorRepositoryException(String.format("Error when find author by email. %s", email), e);
        }
        return authors;
    }
}
