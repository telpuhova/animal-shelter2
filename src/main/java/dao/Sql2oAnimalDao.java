package dao;

import models.Animal;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

/**
 * Created by Guest on 1/17/18.
 */
public class Sql2oAnimalDao implements AnimalDao{

    private final Sql2o sql2o;

    public Sql2oAnimalDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Animal animal){
        String sql = "INSERT INTO animals (name, gender, dateOfAdmission, type, breed) VALUES (:name, :gender, :dateOfAdmission, :type, :breed)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .bind(animal)
//                    .addParameter("name", animal.getName())
//                    .addParameter("gender", animal.getGender())
//                    .addParameter("dateOfAdmission", animal.getD())
//                    .addParameter("gender", animal.getGender())
//                    .addParameter("gender", animal.getGender())
//                    .addColumnMapping("DESCRIPTION", "description")
//                    .addColumnMapping("CATEGORYID", "categoryId")
//                    .addColumnMapping("CREATEDAT", "createdAt")
                    .executeUpdate()
                    .getKey();
            animal.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Animal> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM animals")
                    .executeAndFetch(Animal.class);
        }
    }

    @Override
    public Animal findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM animals WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Animal.class);
        }
    }


}
