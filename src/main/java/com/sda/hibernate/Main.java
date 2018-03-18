package com.sda.hibernate;

import com.sda.hibernate.enitity.BookEntity;
import com.sda.hibernate.enitity.CategoryEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.xml.bind.Marshaller;
import java.math.BigDecimal;
import java.util.List;

public class Main {

    private static final SessionFactory sessionFactory;

    static {

        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();

        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }

    public static void main(String[] args) {

        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthor("Filip Mel");
        bookEntity.setTitle("Programowanie vol 1");

        Transaction tx = null;
        Session session = getSession();

        tx = session.beginTransaction();
        session.save(bookEntity);
        tx.commit();

        BookEntity bookEntity1 = new BookEntity();
        bookEntity1.setAuthor("Zdzichu");
        bookEntity1.setTitle("lalal");
        bookEntity1.setPageCount(1234);

        BookEntity bookEntity2 = new BookEntity();
        bookEntity2.setAuthor("Ola");
        bookEntity2.setTitle("Poradnik");
        bookEntity2.setIsbn("333");

        BookEntity bookEntity3 = new BookEntity();
        bookEntity3.setTitle("Ksiazka 1");
        bookEntity3.setAuthor("Zenek");
        bookEntity3.setPrice(BigDecimal.valueOf(34.213).setScale(4));

        tx = session.beginTransaction();
        session.save(bookEntity1);
        session.save(bookEntity2);
        session.save(bookEntity3);
        tx.commit();

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("low");

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setName("high");

        CategoryEntity categoryEntity2 = new CategoryEntity();
        categoryEntity2.setName("med");

        tx = session.beginTransaction();
        session.save(categoryEntity);
        session.save(categoryEntity1);
        session.save(categoryEntity2);

        tx.commit();

        List<CategoryEntity> categoryEntityList = session.createCriteria(CategoryEntity.class).list();


        for (CategoryEntity el : categoryEntityList) {
            System.out.println("Category: " + el.getName() + " " + el.getId());
        }

        List<BookEntity> bookEntityList = session.createCriteria(BookEntity.class).list();

        for (BookEntity data : bookEntityList) {

            System.out.println("Autor: " + data.getAuthor());
            System.out.println("Title " + data.getTitle());
        }


    }
}
