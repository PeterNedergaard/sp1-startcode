package generate;


import dto.AddressDTO;
import dto.PersonDTO;
import dto.PhoneDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.*;

public class Main {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();




    public static void main(String[] args) {

        /*Address address1 = new Address("Gade","MoreInfo");
        Address address2 = new Address("Gade2","MoreInfo2");

        CityInfo cityInfo1 = new CityInfo("1100","By1");
        CityInfo cityInfo2 = new CityInfo("1200","By2");

        Person person1 = new Person("hej@hej","jens","jensen");
        Person person2 = new Person("kurt@kurt","kurt","kurtsen");

        Phone phone1 = new Phone("12345678","Nokia");
        Phone phone2 = new Phone("87654321","Samsung");


        Hobby hobby1 = new Hobby("Parkour", "Feels like spiderman");
        Hobby hobby2 = new Hobby("Ping pong","Inspired by Forrest Gump");

        address1.addCityInfo(cityInfo1);
        address2.addCityInfo(cityInfo2);


        hobby1.addPerson(person1);
        hobby2.addPerson(person1);

        person1.addAddress(address1);
        person2.addAddress(address2);

        person1.addPhone(phone1);
        person2.addPhone(phone2);

        AddressDTO addressDTO1 = new AddressDTO(address1);
        AddressDTO addressDTO2 = new AddressDTO(address2);

        PersonDTO personDTO1 = new PersonDTO(person1);
        PhoneDTO phoneDTO1 = new PhoneDTO(phone1);*/


        //System.out.println(gson.toJson(addressDTO1));
        //System.out.println(gson.toJson(addressDTO2));
        //System.out.println(gson.toJson(personDTO1));
        //System.out.println(gson.toJson(phoneDTO1));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

        generate(emf);


    }

    public static void generate(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();

        try {
            Person person1 = new Person("rabee@hotmail.dk","Rabee","Abla");
            Person person2 = new Person("peter@live.dk","Peter","Nedergaard");
            Person person3 = new Person("mo@yahoo.dk","Mohammed","Gheith");

            Hobby hobby1 = new Hobby("Parkour", "Feels like spiderman");
            Hobby hobby2 = new Hobby("Ping pong","Inspired by Forrest Gump");
            Hobby hobby3 = new Hobby("Board games","Use a pre-marked board");
            Hobby hobby4 = new Hobby("Bowling","is a target sport");
            Hobby hobby5 = new Hobby("Baking","is a method of preparing food");
            Hobby hobby6 = new Hobby("Card game","is any game using playing cards");
            Hobby hobby7 = new Hobby("Computer programmering","write and test code");
            Hobby hobby8 = new Hobby("Cue sport","played with a cue stick");
            Hobby hobby9 = new Hobby("Pole Dancing","Pole dance combines dance and acrobatics");
            Hobby hobby10 = new Hobby("Sewing","is the craft of fastening");
            Hobby hobby11 = new Hobby("Welding","a fabrication process that joins materials");
            Hobby hobby12 = new Hobby("Baseball","a game played with a bat");
            Hobby hobby13 = new Hobby("Basketball","is a game played between two teams");
            Hobby hobby14 = new Hobby("BMX","racing motocross");
            Hobby hobby15 = new Hobby("Cycling","is the use of bicycles for transport");

            Phone phone1 = new Phone("54843585","Nokia 3310i");
            Phone phone2 = new Phone("54853846","Nokia 6610");

            Address address1 = new Address("Rosenlundsvej 63", "Three plus five");
            Address address2 = new Address("Carlos Allé 52", "Sixteen plus nine");

            CityInfo cityInfo1 = new CityInfo("2791","Dragør");
            CityInfo cityInfo2 = new CityInfo("2000","København K");

            em.getTransaction().begin();

            em.persist(person1);
            em.persist(person2);
            em.persist(person3);


            em.persist(hobby1);
            em.persist(hobby2);
            em.persist(hobby3);
            em.persist(hobby4);
            em.persist(hobby5);
            em.persist(hobby7);

            em.persist(phone1);
            em.persist(phone2);

            em.persist(address1);
            em.persist(address2);

            em.persist(cityInfo1);
            em.persist(cityInfo2);

            hobby1.addPerson(person1);
            hobby1.addPerson(person2);
            hobby2.addPerson(person1);

            person2.addPhone(phone1);
            person1.addPhone(phone2);

            person3.addAddress(address1);
            person2.addAddress(address2);
            person1.addAddress(address1);

            address1.addCityInfo(cityInfo1);
            address2.addCityInfo(cityInfo2);




            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
