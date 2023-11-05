package model.ext.packAnimImpl;

import model.HumanFriend;
import model.ext.PackAnimal;
import util.Date;

public class Donkey extends HumanFriend implements PackAnimal {
    public Donkey(String name, Date birthDay) {
        super(name, birthDay);
    }
}
