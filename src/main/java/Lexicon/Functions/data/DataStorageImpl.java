package Lexicon.Functions.data;

import Lexicon.Functions.model.Person;
import Lexicon.Functions.util.PersonGenerator;

import java.time.LocalDate;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Collections.sort;

public class DataStorageImpl implements DataStorage {
    private static final DataStorage INSTANCE;

    static {
        INSTANCE = new DataStorageImpl();
    }

    public final List<Person> personList;

    public DataStorageImpl() {
        personList = PersonGenerator.getInstance().generate(1000);
    }

    static DataStorage getInstance() {
        return INSTANCE;
    }


    @Override
    public List<Person> findMany(Predicate<Person> filter) {
        List<Person> result = new ArrayList<>();
        for (Person person : personList) {
            if (filter.test(person)) {
                result.add(person);
            }
        }
        return result;
    }

    @Override
    public Person findOne(Predicate<Person> filter) {
        for (Person person : personList) {
            if (filter.test(person)) {
                for (Person value : personList) {
                    if (value.equals(person)) {
                        return person;
                    }

                }
            }
        }
        return null;
    }

    @Override
    public String findOneAndMapToString(Predicate<Person> filter, Function<Person, String> personToString) {
        String result = null;
        for (Person person : personList) {
            if (filter.test(person)) {
                for (Person value : personList) {
                    if (value.equals(person)) {
                        personToString.apply(value);
                        String name = "Name: "+value.getFirstName();
                        LocalDate bod = value.getBirthDate();
                        result = name.concat(" born "+ bod);
                        return result;
                    }
                }
            }

        }
        return result;
    }

    @Override
    public List<String> findManyAndMapEachToString(Predicate<Person> filter, Function<Person, String> personToString) {
        List<Person> result = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        for (Person person : personList) {
            if (filter.test(person)) {
                result.add(person);
                personToString.apply(person);
                String name = person.getFirstName();
                int age= person.getAge();
                String combined = name.concat(" "+age + " Years");

                strings.add(combined);
            } }
                        return strings;
    }

    @Override
    public void findAndDo(Predicate<Person> filter, Consumer<Person> consumer) {
        List<Person> result = new ArrayList<>();
        for (Person person : personList) {
            if (filter.test(person)) {
                result.add(person);
                consumer.accept(person);
              for (int i = 0 ; i < result.size(); i++){
                  System.out.println(result.get(i));
              }
            }}

    }

    @Override
    public List<Person> findAndSort(Comparator<Person> comparator) {

        List<Person> result = new ArrayList<>();
        for(Person person : personList){
            if (person.getFirstName().startsWith("A")){
                result.add(person);
                sort(result,comparator);
            } }
        return result;
    }



    @Override
    public List<Person> findAndSort(Predicate<Person> filter, Comparator<Person> comparator) {
        List<Person> result = new ArrayList<>();
        for(Person person : personList){
            if (filter.test(person)){
                result.add(person);
                sort(result,comparator);
            } }
        return result; }
}
