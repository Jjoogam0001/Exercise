package Lexicon.Functions;

import Lexicon.Functions.data.DataStorage;
import Lexicon.Functions.data.DataStorageImpl;
import Lexicon.Functions.model.Gender;
import Lexicon.Functions.model.Person;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Exercises {
    private final static DataStorage storage = DataStorage.INSTANCE;

    /*
       1.	Find everyone that has firstName: “Erik” using findMany().
    */
    public static void exercise1(String message){
        System.out.println(message);
        Predicate<Person> names = n -> n.getFirstName().equals("Erik");
        List<Person> many = storage.findMany(names);
        System.out.println(many);
        System.out.println("----------------------");
    }

    /*
        2.	Find all females in the collection using findMany().
     */
    public static void exercise2(String message){
        System.out.println(message);
        Predicate<Person> allfemales = n -> n.getGender().equals(Gender.FEMALE);
        List<Person> many = storage.findMany(allfemales);
        System.out.println(many);
        System.out.println("----------------------");
    }

    /*
        3.	Find all who are born after (and including) 2000-01-01 using findMany().
     */
    public static void exercise3(String message){
        System.out.println(message);
        //Write your code here
        Predicate<Person> peoplebornafter2000 = n -> n.getBirthDate().isAfter(LocalDate.parse("2000-01-01"));
        List<Person> many = storage.findMany(peoplebornafter2000);
        System.out.println(many);
        System.out.println("----------------------");

    }

    /*
        4.	Find the Person that has an id of 123 using findOne().
     */
    public static void exercise4(String message){
        System.out.println(message);
        Predicate<Person> sinlgeperson = c -> c.getId() == 123;
        Person one = storage.findOne(sinlgeperson);
        System.out.println(one);
        System.out.println("----------------------");


    }



    /*
        5.	Find the Person that has an id of 456 and convert to String with following content:
            “Name: Nisse Nilsson born 1999-09-09”. Use findOneAndMapToString().
     */
    public static void exercise5(String message){
        System.out.println(message);
        Predicate<Person> sinlgeperson = c -> c.getId() == 456;
        Function<Person, String> joinString = a -> {
            String name =  a.getFirstName();
            LocalDate dob =  a.getBirthDate();
            String result = name.concat(String.valueOf(dob));
            return result;
        };
        String oneAndMapToString = storage.findOneAndMapToString(sinlgeperson, joinString);
        System.out.println(oneAndMapToString);


        System.out.println("----------------------");
    }

    /*
        6.	Find all male people whose names start with “E” and convert each to a String using findManyAndMapEachToString().
     */
    public static void exercise6(String message){
        System.out.println(message);
        Predicate<Person> listofnameswithE = c-> c.getFirstName().startsWith("E")
                && c.getLastName().startsWith("E");
        Function<Person, String> fuc = b->{
           String firstName =  b.getFirstName();
           String secondName = b. getLastName();
           return firstName.concat(" :"+secondName);
        };
        System.out.println(storage.findManyAndMapEachToString(listofnameswithE,fuc));


        System.out.println("----------------------");
    }

    /*
        7.	Find all people who are below age of 10 and convert them to a String like this:
            “Olle Svensson 9 years”. Use findManyAndMapEachToString() method.
     */
    public static void exercise7(String message){
        System.out.println(message);
        //Write your code here
        Predicate<Person> listofnameunder10 = c-> c.getAge() < 10;
        Function<Person, String> fuc = b->{
            String firstName =  b.getFirstName();
            String secondName = b. getLastName();
            return firstName.concat(" "+secondName);
        };
        System.out.println(storage.findManyAndMapEachToString(listofnameunder10,fuc));
        System.out.println("----------------------");
    }

    /*
        8.	Using findAndDo() print out all people with firstName “Ulf”.
     */
    public static void exercise8(String message){
        System.out.println(message);
        //Write your code here
        Predicate<Person> firstNamuLF = C -> C.getFirstName().equals("Ulf");
        Consumer<Person> printAll = Person::toString;
        storage.findAndDo(firstNamuLF,printAll);
        System.out.println("----------------------");
    }

    /*
        9.	Using findAndDo() print out everyone who have their lastName contain their firstName.
     */
    public static void exercise9(String message){
        System.out.println(message);
        //Write your code here
        Predicate<Person> firstNamuLF = C -> C.getLastName().contains(C.getFirstName());
        Consumer<Person> printAll = Person::toString;
        storage.findAndDo(firstNamuLF,printAll);
        System.out.println("");
        System.out.println("----------------------");


    }

    /*
        10.	Using findAndDo() print out the firstName and lastName of everyone whose firstName is a palindrome.
     */
    public static void exercise10(String message){
        System.out.println(message);
        //Write your code here
        Predicate<Person> firstNamuLF = C -> {
            String rev = " ";
            int length = C.getLastName().length();
            for (int i = length-1; i >= 0;i-- ){
                rev =  rev + C.getLastName().charAt(i);
                if (C.getLastName().equals(rev)){
                    return true;
                } }
          return false;
        };
        Consumer<Person> printAll = System.out::println;
        storage.findAndDo(firstNamuLF,printAll);
        System.out.println("----------------------");


    }

    /*
        11.	Using findAndSort() find everyone whose firstName starts with A sorted by birthdate.
     */
    public static void exercise11(String message) {
        System.out.println(message);
        //Write your code here
        Comparator<Person> comparator = Comparator.comparing(c -> c.getBirthDate());
        System.out.println(storage.findAndSort(comparator));
    }

    /*
        12.	Using findAndSort() find everyone born before 1950 sorted reversed by lastest to earliest.
     */
    public static void exercise12(String message){
        System.out.println(message);
        //Write your code here
        Predicate<Person>list = p->p.getBirthDate().isBefore(LocalDate.parse("1950-01-01"));
        Comparator<Person> comparator = Comparator.reverseOrder();
        System.out.println(storage.findAndSort(list,comparator));

        System.out.println("----------------------");
    }

    /*
        13.	Using findAndSort() find everyone sorted in following order: lastName > firstName > birthDate.
     */
    public static void exercise13(String message){
        System.out.println(message);
        //Write your code here
        Predicate<Person>list = p->p.getBirthDate().isAfter(LocalDate.parse("1700-01-01"));
        Comparator<Person> comparator = Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName).thenComparing(Person::getBirthDate);
        System.out.println(storage.findAndSort(list,comparator));
        System.out.println("----------------------");


    }



}
