package app.library.storage;

import app.library.config.HibernateConnectionManager;
import app.library.config.PropertyConfig;
import app.library.exceptions.AuthorRepositoryException;
import app.library.exceptions.BookRepositoryException;
import app.library.model.Book;
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

public class BookRepository implements BookRepositoryCustom<Book> {

    static {
        HibernateConnectionManager.initialize(new PropertyConfig());
    }
    private static final Logger logger = LoggerFactory.getLogger(BookRepository.class);
    private static final SessionFactory sessionFactory = HibernateConnectionManager.getSessionFactory();

    @Override
    public void save(Book entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            logger.info("Book save transaction start");
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
            logger.info("Book save transaction successful");
        } catch (HibernateException e) {
            logger.error("Book save transaction error {}", e.toString());
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new BookRepositoryException("Cannot save a book", e);
        }
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            logger.info("Book find all transaction start");
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Book> query = builder.createQuery(Book.class);
            Root<Book> root = query.from(Book.class);
            query.select(root);
            books = session.createQuery(query).getResultList();
            logger.info("Book find all transaction successful");
        } catch (HibernateException e) {
            logger.error("Book find all transaction error {}", e.toString());
            throw new BookRepositoryException("Cannot find all books", e);
        }
        return books;
    }

    @Override
    public void delete(Book entity) {
        delete(entity.getId());
    }

    @Override
    public Book findById(long id) {
        Book book = null;
        try (Session session = sessionFactory.openSession()) {
            logger.info("Book find by id {} transaction start", id);
            book = session.get(Book.class, id);
            logger.info("Book find by id {} transaction successful", id);
        } catch (HibernateException e) {
            logger.error("Book find by id {} transaction error {}", id, e.toString());
            throw new BookRepositoryException(String.format("Error in find author by id %d", id), e);
        }
        return book;
    }

    @Override
    public void saveAll(List<Book> entities) {
        entities.forEach(this::save);
    }

    @Override
    public void print() {
        findAll().forEach(System.out::println);
    }

    @Override
    public Book update(Book entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new AuthorRepositoryException("Error in book update", e);
        }
        return findById(entity.getId());
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            logger.info("Book delete by id {} transaction start", id);
            transaction = session.beginTransaction();
            session.remove(findById(id));
            transaction.commit();
            logger.info("Book delete by id {} transaction successful", id);
        } catch (HibernateException e) {
            logger.error("Book delete by id {} transaction error {}", id, e.toString());
            if (transaction != null) {
                transaction.rollback();
            }
            throw new BookRepositoryException("Error in book deletion", e);
        }
    }

    @Override
    public List<Book> findBooksByAuthorId(Long authorId) {
        List<Book> books = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            logger.info("Book find by author id {} transaction start", authorId);
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Book> query = builder.createQuery(Book.class);
            Root<Book> root = query.from(Book.class);

            query.select(root)
                    .where(builder.equal(root.get("authorId"), authorId));

            books = session.createQuery(query).getResultList();
            logger.info("Book find by author id {} transaction successful", authorId);
        } catch (HibernateException e) {
            logger.error("Book find by author id {} transaction error {}", authorId, e.toString());
            throw new BookRepositoryException(String.format("Error in find book by author ID %d", authorId), e);
        }
        return books;
    }

    @Override
    public List<Book> findBooksByName(String name) {
        List<Book> books = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            logger.info("Book find by name {} transaction start", name);
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Book> query = builder.createQuery(Book.class);
            Root<Book> root = query.from(Book.class);
            query.select(root)
                    .where(builder.equal(root.get("name"), name));

            books = session.createQuery(query).getResultList();
            logger.info("Book find by name {} transaction successful", name);
        } catch (HibernateException e) {
            logger.error("Book find by name {} transaction error {}", name, e.toString());
            throw new BookRepositoryException(String.format("Error in find book by name %s", name), e);
        }
        return books;
    }
}
