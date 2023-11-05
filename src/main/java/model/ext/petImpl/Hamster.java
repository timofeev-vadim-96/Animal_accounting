package model.ext.petImpl;

import model.HumanFriend;
import model.ext.Pet;
import util.Date;

public class Hamster extends HumanFriend implements Pet {
    public Hamster(String name, Date birthDay) {
        super(name, birthDay);
    }
}
