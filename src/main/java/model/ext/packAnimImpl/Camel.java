package model.ext.packAnimImpl;

import model.HumanFriend;
import model.ext.PackAnimal;
import util.Date;

public class Camel extends HumanFriend implements PackAnimal {
    public Camel(String name, Date birthDay) {
        super(name, birthDay);
    }
}
