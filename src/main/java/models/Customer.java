package models;

/**
 * Created by Guest on 1/17/18.
 */
public class Customer {
    private int id;
    private String name;
    private String phone;
    private String typePreference;
    private String breedPreference;
    public Customer(String name, String phone, String typePreference, String breedPreference) {
        this.name = name;
        this.phone = phone;
        this.typePreference = typePreference;
        this.breedPreference = breedPreference;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTypePreference() {
        return typePreference;
    }

    public void setTypePreference(String typePreference) {
        this.typePreference = typePreference;
    }

    public String getBreedPreference() {
        return breedPreference;
    }

    public void setBreedPreference(String breedPreference) {
        this.breedPreference = breedPreference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (!name.equals(customer.name)) return false;
        if (!phone.equals(customer.phone)) return false;
        if (typePreference != null ? !typePreference.equals(customer.typePreference) : customer.typePreference != null)
            return false;
        return breedPreference != null ? breedPreference.equals(customer.breedPreference) : customer.breedPreference == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + (typePreference != null ? typePreference.hashCode() : 0);
        result = 31 * result + (breedPreference != null ? breedPreference.hashCode() : 0);
        return result;
    }
}
