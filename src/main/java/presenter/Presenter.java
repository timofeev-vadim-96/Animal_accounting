package presenter;

import com.google.gson.Gson;
import dao.DB;
import model.HumanFriend;
import model.ext.packAnimImpl.Camel;
import model.ext.packAnimImpl.Donkey;
import model.ext.packAnimImpl.Horse;
import model.ext.petImpl.Cat;
import model.ext.petImpl.Dog;
import model.ext.petImpl.Hamster;
import util.Counter;
import util.Date;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Presenter {
    DB<HumanFriend> dataBase;

    public Presenter() {
        this.dataBase = new DB<>();
    }

    public void run() {
        if (dataBase.createJsonDataBase()) initiate();
        fillListFromLocalDB(dataBase.getAnimals());
    }

    public void saveChanges() {
        dataBase.saveChanges();
    }

    public boolean compareData() {
        List<HumanFriend> originalData = new LinkedList<>();
        fillListFromLocalDB(originalData);
        return originalData.equals(dataBase.getAnimals());
    }

    public void addNewAnimal(HumanFriend animal) {
        dataBase.add(animal);
    }

    public void showAnimals() {
        List<HumanFriend> animals = dataBase.getAnimals();
        for (int i = 0; i < animals.size(); i++) {
            System.out.printf("Index: %s. %s\n", i, animals.get(i));
        }
    }

    public void remove(int index) {
        dataBase.remove(index);
    }

    public int animalsQuantity() {
        return dataBase.getAnimals().size();
    }

    public void teachAnimalNewCommand(String command, int index) {
        dataBase.getAnimals().get(index).learnNewCommand(command);
    }

    public List<String> getCommands(int index) {
        return dataBase.getAnimals().get(index).getCommands();
    }

    //for main
    //Только в том случае, если локальная база данных не существовала
    public void initiate() {
        Cat cat1 = new Cat("Пушистик", new Date(28, 2, 2023));
        Cat cat2 = new Cat("Селин", new Date(2, 1, 2022));
        Dog dog1 = new Dog("Люциан", new Date(6, 6, 2021));
        Dog dog2 = new Dog("Балрог", new Date(20, 7, 2019));
        Hamster hamster1 = new Hamster("Печенька", new Date(2, 8, 2023));
        Hamster hamster2 = new Hamster("Булочка", new Date(10, 2, 2023));
        Horse horse1 = new Horse("Звездный ветер", new Date(11, 8, 2011));
        Horse horse2 = new Horse("Лунная грация", new Date(3, 9, 2009));
        Camel camel1 = new Camel("Горбаш", new Date(18, 5, 2001));
        Camel camel2 = new Camel("Верблюжка", new Date(13, 3, 2005));
        Donkey donkey1 = new Donkey("Мирро", new Date(3, 10, 2015));
        Donkey donkey2 = new Donkey("Гаррет", new Date(22, 12, 2017));
        dataBase
                .add(cat1)
                .add(cat2)
                .add(dog1)
                .add(dog2)
                .add(hamster1)
                .add(hamster2)
                .add(horse1)
                .add(horse2)
                .add(camel1)
                .add(camel2)
                .add(donkey1)
                .add(donkey2);
        dataBase.saveChanges();
    }

    public void fillListFromLocalDB(List<HumanFriend> list) {
        list.clear();
        try
                (Scanner scanner = new Scanner(new File(dataBase.getPath()));
                 Counter counter = new Counter()) {
            while (scanner.hasNext()) {
                counter.add();
                Gson gson = new Gson();
                String nextLine = scanner.nextLine();
                if (nextLine.contains("\"type\":\"Cat\"")) {
                    Cat cat = gson.fromJson(nextLine, Cat.class);
                    list.add(cat);
                } else if (nextLine.contains("\"type\":\"Dog\"")) {
                    Dog dog = gson.fromJson(nextLine, Dog.class);
                    list.add(dog);
                } else if (nextLine.contains("\"type\":\"Hamster\"")) {
                    Hamster hamster = gson.fromJson(nextLine, Hamster.class);
                    list.add(hamster);
                } else if (nextLine.contains("\"type\":\"Horse\"")) {
                    Horse horse = gson.fromJson(nextLine, Horse.class);
                    list.add(horse);
                } else if (nextLine.contains("\"type\":\"Camel\"")) {
                    Camel camel = gson.fromJson(nextLine, Camel.class);
                    list.add(camel);
                } else if (nextLine.contains("\"type\":\"Donkey\"")) {
                    Donkey donkey = gson.fromJson(nextLine, Donkey.class);
                    list.add(donkey);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCounter() {
        int res;
        try (Counter counter = new Counter()) {
            res = counter.getCounter();
        }
        return res;
    }
}
