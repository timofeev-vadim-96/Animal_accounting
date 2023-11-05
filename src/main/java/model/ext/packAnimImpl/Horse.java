package model.ext.packAnimImpl;

import model.HumanFriend;
import model.ext.PackAnimal;
import util.Date;

public class Horse extends HumanFriend implements PackAnimal {
    public Horse(String name, Date birthDay) {
        super(name, birthDay);
    }
}
