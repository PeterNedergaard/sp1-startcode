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
            Person person3 = new Person("jens@mail.com","Jens","Hansen");
            Person person4 = new Person("Kurt@email.org","Kurt","Svensen");
            Person person5 = new Person("Mustafa@mulle@com","Mustafa","Kristensen");
            Person person6 = new Person("Lars@jysk.dk","Lars","Larsen");
            Person person7 = new Person("Ahmed@sha7ata.uae","Ahmed","Madsen");
            Person person8 = new Person("Kim@larsen.dk","Kim","Larsen");

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
            Phone phone3 = new Phone("12345678","Brick 900");
            Phone phone4 = new Phone("87654321","Samsung S41");
            Phone phone5 = new Phone("72861928","NotAPhone");
            Phone phone6 = new Phone("26105148","Applesung");
            Phone phone7 = new Phone("46197261","Dumbphone");
            Phone phone8 = new Phone("62904617","Apple Iphone 14");

            Address address1 = new Address("Rosenlundsvej 63", "Three plus five");
            Address address2 = new Address("Carlos Allé 52", "Sixteen plus nine");
            Address address3 = new Address("Area 51 street", "Aliens");
            Address address4 = new Address("King street 42", "Kings only");
            Address address5 = new Address("Moms Basement 21", "Let me out");

            CityInfo cityInfo1 = new CityInfo("2791","Dragør");
            CityInfo cityInfo2 = new CityInfo("2000","København K");
            CityInfo cityInfo3 = new CityInfo("2400","København NV");
            CityInfo cityInfo4 = new CityInfo("9800","Hørring");
            CityInfo cityInfo5 = new CityInfo("5700","Svendborg");

            em.getTransaction().begin();

            em.persist(person1);
            em.persist(person2);
            em.persist(person3);
            em.persist(person4);
            em.persist(person5);
            em.persist(person6);
            em.persist(person7);
            em.persist(person8);


            em.persist(hobby1);
            em.persist(hobby2);
            em.persist(hobby3);
            em.persist(hobby4);
            em.persist(hobby5);
            em.persist(hobby6);
            em.persist(hobby7);
            em.persist(hobby8);
            em.persist(hobby9);
            em.persist(hobby10);
            em.persist(hobby11);
            em.persist(hobby12);
            em.persist(hobby13);
            em.persist(hobby14);
            em.persist(hobby15);

            em.persist(phone1);
            em.persist(phone2);
            em.persist(phone3);
            em.persist(phone4);
            em.persist(phone5);
            em.persist(phone6);
            em.persist(phone7);
            em.persist(phone8);

            em.persist(address1);
            em.persist(address2);
            em.persist(address3);
            em.persist(address4);
            em.persist(address5);

            em.persist(cityInfo1);
            em.persist(cityInfo2);
            em.persist(cityInfo3);
            em.persist(cityInfo4);
            em.persist(cityInfo5);

            hobby1.addPerson(person1);
            hobby1.addPerson(person2);
            hobby5.addPerson(person3);
            hobby6.addPerson(person4);
            hobby6.addPerson(person5);
            hobby6.addPerson(person6);
            hobby9.addPerson(person7);
            hobby11.addPerson(person7);
            hobby15.addPerson(person8);

            person1.addPhone(phone1);
            person2.addPhone(phone2);
            person3.addPhone(phone3);
            person4.addPhone(phone4);
            person5.addPhone(phone5);
            person6.addPhone(phone6);
            person7.addPhone(phone7);
            person8.addPhone(phone8);

            person1.addAddress(address1);
            person2.addAddress(address1);
            person3.addAddress(address2);
            person4.addAddress(address2);
            person5.addAddress(address3);
            person6.addAddress(address3);
            person7.addAddress(address4);
            person8.addAddress(address5);

            address1.addCityInfo(cityInfo1);
            address2.addCityInfo(cityInfo2);
            address3.addCityInfo(cityInfo3);
            address4.addCityInfo(cityInfo4);
            address5.addCityInfo(cityInfo5);



            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
