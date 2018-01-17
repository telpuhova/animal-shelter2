package dao;

import models.Animal;

import java.util.List;

/**
 * Created by Guest on 1/17/18.
 */
public interface AnimalDao {
    void add(Animal animal);

    List<Animal> getAll();

    Animal findById(int id);

//    void update(int id, String name, String gender, String date, String type, String breed);
//
//    void deleteById(int id);
//
//    void clearAll();
}
