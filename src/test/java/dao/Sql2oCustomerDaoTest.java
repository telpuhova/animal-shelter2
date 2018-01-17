package dao;

import models.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

/**
 * Created by Guest on 1/17/18.
 */
public class Sql2oCustomerDaoTest {

    private Sql2oCustomerDao customerDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception{
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        customerDao = new Sql2oCustomerDao(sql2o);

        //keep connection open through entire test so it does not get erased
        conn = sql2o.open();

    }

    @After
    public void tearDowns() throws Exception {
        conn.close();
    }

    @Test
    public void add() throws Exception {
        Customer customer = new Customer("mike", "123456", "dogs", "yorkie");
        customerDao.add(customer);
        assertEquals(1, customerDao.getAll().size());
    }

    @Test
    public void addCorrectId() throws Exception {
        Customer customer = new Customer("mike", "123456", "dogs", "yorkie");
        Customer customer2 = new Customer("jack", "123456", "dogs", "yorkie");
        int originalId = customer2.getId();
        customerDao.add(customer);
        customerDao.add(customer2);

        assertNotEquals(originalId, customer2.getId());
    }

    @Test
    public void getAllEmpty() throws Exception {
        assertEquals(0, customerDao.getAll().size());
    }

    @Test
    public void getAllNotEmpty() throws Exception {
        Customer customer = new Customer("mike", "123456", "dogs", "yorkie");
        Customer customer2 = new Customer("jack", "123456", "dogs", "yorkie");
        Customer customer3 = new Customer("jane", "123456", "dogs", "yorkie");
        Customer customer4 = new Customer("john", "123456", "dogs", "yorkie");
        customerDao.add(customer);
        customerDao.add(customer2);
        customerDao.add(customer3);
        customerDao.add(customer4);
        assertEquals(4, customerDao.getAll().size());
    }

    @Test
    public void findById() throws Exception {
        Customer customer = new Customer("mike", "123456", "dogs", "yorkie");
        Customer customer2 = new Customer("jack", "123456", "dogs", "yorkie");
        Customer customer3 = new Customer("jane", "123456", "dogs", "yorkie");
        Customer customer4 = new Customer("john", "123456", "dogs", "yorkie");
        customerDao.add(customer);
        customerDao.add(customer2);
        customerDao.add(customer3);
        customerDao.add(customer4);

        assertEquals(customer2, customerDao.findById(2));
    }

}