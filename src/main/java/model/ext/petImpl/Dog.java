package model.ext.petImpl;

import model.HumanFriend;
import model.ext.Pet;
import util.Date;

public class Dog extends HumanFriend implements Pet {
    public Dog(String name, Date birthDay) {
        super(name, birthDay);
    }
}
