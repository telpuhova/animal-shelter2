package dao;

import models.Animal;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

/**
 * Created by Guest on 1/17/18.
 */
public class Sql2oAnimalDaoTest {
    private Sql2oAnimalDao animalDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception{
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        animalDao = new Sql2oAnimalDao(sql2o);

        //keep connection open through entire test so it does not get erased
        conn = sql2o.open();

    }

    @After
    public void tearDowns() throws Exception {
        conn.close();
    }

    @Test
    public void addingAnimal() throws Exception {
        Animal animal = new Animal("Fluffy", "neutral", "01/01/01", "Bunny", "Angora");
        animalDao.add(animal);
        assertEquals(1, animalDao.getAll().size());
    }

    @Test
    public void addingAnimalSetsId() throws Exception {
        Animal animal = new Animal("Fluffy", "neutral", "01/01/01", "Bunny", "Angora");
        int originalAnimalId = animal.getId();
        animalDao.add(animal);
        assertNotEquals(originalAnimalId, animal.getId());
    }

    @Test
    public void existingAnimalsCanBeFoundById() throws Exception {
        Animal animal = new Animal("Fluffy", "neutral", "01/01/01", "Bunny", "Angora");
        animalDao.add(animal);
        Animal foundAnimal = animalDao.findById(animal.getId());
        assertEquals(animal, foundAnimal);
    }

    @Test
    public void getAll_findsNoTasksWhenNoneArePresent_0() throws Exception {
        assertEquals(0, animalDao.getAll().size());
    }

    @Test
    public void getAll_returnsAllInstancesOfAnimal_List() throws Exception {
        Animal animal = new Animal("Fluffy", "neutral", "01/01/01", "Bunny", "Angora");
        Animal animal1 = new Animal("Edwin", "Vampire", "11/11/11", "Dragon", "Silver");
        animalDao.add(animal);
        animalDao.add(animal1);
        assertEquals(2, animalDao.getAll().size());
    }

    @Test
    public void setCustomerId_setsCustomerId_int() throws Exception {
        Animal animal = new Animal("Fluffy", "neutral", "01/01/01", "Bunny", "Angora");
        animalDao.add(animal);
        Animal foundAnimal = animalDao.findById(animal.getId());
        foundAnimal.setCustomerId(1);
        assertEquals(1, foundAnimal.getCustomerId());
    }
}