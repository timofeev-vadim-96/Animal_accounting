package model;

import util.Date;

import java.util.ArrayList;
import java.util.List;

public abstract class HumanFriend {
    protected Date birthDay;
    protected String name;
    protected List<String> commands;
    protected String type;

    public HumanFriend(String name, Date birthDay) {
        this.birthDay = birthDay;
        this.name = name;
        this.commands = new ArrayList<>();
        this.type = this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return String.format("Вид: %s, Имя: %s, день рождения: %s",
                this.type, this.name, this.birthDay);
    }

    public boolean learnNewCommand (String command){
        return this.commands.add(command);
    }

    public String getName() {
        return name;
    }

    public List<String> getCommands() {
        return commands;
    }

    @Override
    public boolean equals(Object obj) {
        HumanFriend hf = (HumanFriend) obj;
        return this.name.equals(hf.name) &&
                this.birthDay.equals(hf.birthDay) &&
                this.commands.equals(hf.commands) &&
                this.type.equals(hf.type);
    }
}
