package util;

import model.HumanFriend;
import model.ext.packAnimImpl.Camel;
import model.ext.packAnimImpl.Donkey;
import model.ext.packAnimImpl.Horse;
import model.ext.petImpl.Cat;
import model.ext.petImpl.Dog;
import model.ext.petImpl.Hamster;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AnimalFabric {

    /**
     * Создание животного по принципу дизайн-паттерна "Фабрика"
     */
    public HumanFriend createNewAnimal(int choiceOfTypeAnimal, List<String> animal) {
        try (Counter counter = new Counter()) {
            counter.add();
        } catch (IOException e) {
            System.out.println("Падение приложения из-за состояния счетчика...");
            e.printStackTrace();
        }

        List<String> date = Arrays.asList(animal.get(1).split("\\."));
        int day = Integer.parseInt(date.get(0));
        int month = Integer.parseInt(date.get(1));
        int year = Integer.parseInt(date.get(2));

        if (choiceOfTypeAnimal == 1) {
            return new Cat(animal.get(0), new Date(day, month, year));
        } else if (choiceOfTypeAnimal == 2) {
            return new Dog(animal.get(0), new Date(day, month, year));
        } else if (choiceOfTypeAnimal == 3) {
            return new Hamster(animal.get(0), new Date(day, month, year));
        } else if (choiceOfTypeAnimal == 4) {
            return new Horse(animal.get(0), new Date(day, month, year));
        } else if (choiceOfTypeAnimal == 5) {
            return new Camel(animal.get(0), new Date(day, month, year));
        } else return new Donkey(animal.get(0), new Date(day, month, year));
    }
}
