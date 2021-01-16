package Lexicon.Functions.data;

import Lexicon.Functions.model.Person;
import Lexicon.Functions.util.PersonGenerator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class DataStorageImpl implements DataStorage {
    private static final DataStorage INSTANCE;

    static {
        INSTANCE = new DataStorageImpl();
    }

    private final List<Person> personList;

    private DataStorageImpl(){
        personList = PersonGenerator.getInstance().generate(1000);
    }

    static DataStorage getInstance(){
        return INSTANCE;
    }


    @Override
    public List<Person> findMany(Predicate<Person> filter) {
        List<Person> result = new ArrayList<>();
        for(Person person : personList){
            if(filter.test(person)){
                result.add(person);
            }
        }
        return result;
    }

    @Override
    public Person findOne(Predicate<Person> filter) {
        return null;
    }

    @Override
    public String findOneAndMapToString(Predicate<Person> filter, Function<Person, String> personToString){
        return null;
    }

    @Override
    public List<String> findManyAndMapEachToString(Predicate<Person> filter, Function<Person, String> personToString){
        return null;
    }

    @Override
    public void findAndDo(Predicate<Person> filter, Consumer<Person> consumer){
    }

    @Override
    public List<Person> findAndSort(Comparator<Person> comparator){
        return null;
    }

    @Override
    public List<Person> findAndSort(Predicate<Person> filter, Comparator<Person> comparator){
        return null;
    }
}