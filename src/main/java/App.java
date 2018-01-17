import dao.Sql2oAnimalDao;
import dao.Sql2oCustomerDao;
import models.Animal;
import models.Customer;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

/**
 * Created by Guest on 1/17/18.
 */
public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        String connectionString = "jdbc:h2:~/animalshelter.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        Sql2oAnimalDao animalDao = new Sql2oAnimalDao(sql2o);
        Sql2oCustomerDao customerDao = new Sql2oCustomerDao(sql2o);

        //get: homepage
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<String, Object>();

            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/display/by-type", (req, res) -> {
            Map<String, Object> model = new HashMap<String, Object>();

            String type = req.queryParams("type");
            List animals = animalDao.findByType(type);
            model.put("animals", animals);
            return new ModelAndView(model, "display.hbs");
        }, new HandlebarsTemplateEngine());

        get("/display/by-breed", (req, res) -> {
            Map<String, Object> model = new HashMap<String, Object>();

            String breed = req.queryParams("breed");

            List animals = animalDao.findByBreed(breed);
            model.put("animals", animals);

            return new ModelAndView(model, "display.hbs");
        }, new HandlebarsTemplateEngine());

        get("/display/alpha", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List animals = animalDao.getAllAlpha();
            model.put("animals", animals);
            return new ModelAndView(model, "display.hbs");
        }, new HandlebarsTemplateEngine());

        get("/display/date", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List animals = animalDao.getAllDate();
            model.put("animals", animals);
            return new ModelAndView(model, "display.hbs");
        }, new HandlebarsTemplateEngine());

        get("/new-animal", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
//
//            String name = request.queryParams("name");
//            String gender = request.queryParams("gender");
//            String dateOfAdmission = request.queryParams("dateOfAdmission");
//            String type = request.queryParams("type");
//            String breed = request.queryParams("breed");
//            Animal animal = new Animal(name, gender, dateOfAdmission, type, breed);
//            animalDao.add(animal);

            return new ModelAndView(model, "new-animal.hbs");
        }, new HandlebarsTemplateEngine());

        post("/new-animal", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();

            String name = request.queryParams("name");
            String gender = request.queryParams("gender");
            String dateOfAdmission = request.queryParams("dateOfAdmission");
            String type = request.queryParams("type");
            String breed = request.queryParams("breed");
            Animal animal = new Animal(name, gender, dateOfAdmission, type, breed);
            animalDao.add(animal);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/new-customer", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();

            String name = request.queryParams("name");
            String phone = request.queryParams("phone");
            String typePreference = request.queryParams("typePreference");
            String breedPreference = request.queryParams("breedPreference");

            Customer customer = new Customer(name, phone, typePreference, breedPreference);
            customerDao.add(customer);

            return new ModelAndView(model, "new-customer.hbs");
        }, new HandlebarsTemplateEngine());

        post("/new-customer", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();

            String name = request.queryParams("name");
            String phone = request.queryParams("phone");
            String typePreference = request.queryParams("typePreference");
            String breedPreference = request.queryParams("breedPreference");

            Customer customer = new Customer(name, phone, typePreference, breedPreference);
            customerDao.add(customer);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/display-customers", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List customers = customerDao.getAll();
            model.put("customers", customers);
            return new ModelAndView(model, "display-customer.hbs");
        }, new HandlebarsTemplateEngine());

        get("/display/by-breed-preference", (req, res) -> {
            Map<String, Object> model = new HashMap<String, Object>();

            String breedPreference = req.queryParams("breedPreference");
            List customers = customerDao.getByBreedPreference(breedPreference);
            model.put("customers", customers);
            return new ModelAndView(model, "display-customer.hbs");
        }, new HandlebarsTemplateEngine());

        get("/adopt/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();

            int idToFind = Integer.parseInt(request.params("id"));

            Animal foundAnimal = animalDao.findById(idToFind);
            foundAnimal.setCustomerId(Integer.parseInt(request.queryParams("customerId")));


            animalDao.updateCustomerId(Integer.parseInt(request.queryParams("customerId")), foundAnimal.getId());
            List<Animal> animals = animalDao.getAllAlpha();
            model.put("animals", animals);
            return new ModelAndView(model, "display.hbs");
        }, new HandlebarsTemplateEngine());
    }
}