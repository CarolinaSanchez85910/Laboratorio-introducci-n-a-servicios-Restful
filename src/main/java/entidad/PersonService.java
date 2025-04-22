package entidad;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("service")
public class PersonService {

    private static Map<Integer, Person> persons = new HashMap<>();

    static {
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            int id = i + 1;
            person.setId(id);
            person.setFullName("My wonderful Person " + id);
            person.setAge(new Random().nextInt(40) + 1);
            persons.put(id, person);
        }
    }

    public static Map<Integer, Person> getPersons() {
        return persons;
    }

    public static Person getPersonById(int id) {
        return persons.get(id);
    }

    public static void addPerson(Person person) {
        persons.put(person.getId(), person);
    }

    public static void deletePerson(int id) {
        persons.remove(id);
    }

    public static Person updatePerson(Person person) {
        persons.put(person.getId(), person);
        return person;
    }
    
    @GET
    @Path("/getPersonByIdXml/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Person getPersonByIdXML (@PathParam("id") int id) {
        return persons.get(id);
    }
    
    @GET
    @Path("/getPErsonByIdJson/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPersonByIdJson (@PathParam("id") int id) {
        return persons.get(id);
    }
    
    @GET
    @Path("/getAllPersonsInXML")
    @Produces(MediaType.APPLICATION_XML)
    public List<Person> getAllPersonsInXML () {
        return new ArrayList<Person>(persons.values());
    }
    
    @GET
    @Path("/getAllPersonsInJson")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAllPersonsInJson(){
        return new ArrayList<Person>(persons.values());
    }
    
    @POST
    @Path("/addPersonsInJson")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Person addPersonsInJson(Person person){
        System.out.println(person.getId());
        persons.put(new Integer(person.getId()), person);
        return person;
    }
            
}
