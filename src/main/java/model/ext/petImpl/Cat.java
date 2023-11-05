package model.ext.petImpl;

import model.HumanFriend;
import model.ext.Pet;
import util.Date;

public class Cat extends HumanFriend implements Pet {
    public Cat(String name, Date birthDay) {
        super(name, birthDay);
    }
}
