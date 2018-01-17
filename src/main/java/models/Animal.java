package models;

/**
 * Created by Guest on 1/17/18.
 */
public class Animal {
    private int id;
    private String name;
    private String gender;
    private String dateOfAdmission;
    private String type;
    private String breed;

    public Animal(String name, String gender, String dateOfAdmission, String type, String breed){
        this.name = name;
        this.gender = gender;
        this.dateOfAdmission = dateOfAdmission;
        this.type = type;
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getDateOfAdmission() {
        return dateOfAdmission;
    }

    public String getType() {
        return type;
    }

    public String getBreed() {
        return breed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDateOfAdmission(String date) {
        this.dateOfAdmission = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Animal animal = (Animal) o;

        if (!name.equals(animal.name)) return false;
        if (gender != null ? !gender.equals(animal.gender) : animal.gender != null) return false;
        if (dateOfAdmission != null ? !dateOfAdmission.equals(animal.dateOfAdmission) : animal.dateOfAdmission != null)
            return false;
        if (type != null ? !type.equals(animal.type) : animal.type != null) return false;
        return breed != null ? breed.equals(animal.breed) : animal.breed == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (dateOfAdmission != null ? dateOfAdmission.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (breed != null ? breed.hashCode() : 0);
        return result;
    }
}
