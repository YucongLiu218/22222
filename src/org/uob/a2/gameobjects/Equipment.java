package org.uob.a2.gameobjects;

public class Equipment extends Item implements Usable {
    private final int power;

    public Equipment(String name, String description, int power) {
        super(name, description);
        this.power = power;
    }

    public UseInformation use() {
        return new UseInformation("You used " + getName() + ". Its power is " + power + ".", power);
    }
}
