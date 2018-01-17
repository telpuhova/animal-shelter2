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

    List<Animal> findByBreed(String breed);

    List<Animal> findByType(String type);

    List<Animal> getAllAlpha();

    List<Animal> getAllDate();

    void updateCustomerId(int customerId, int id);
//    void deleteById(int id);
//
//    void clearAll();
}
