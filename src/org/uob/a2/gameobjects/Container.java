package org.uob.a2.gameobjects;

import java.util.ArrayList;
import java.util.List;

public class Container extends Feature {
    private final List<Item> items;

    public Container(String name, String description) {
        super(name, description);
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public boolean removeItem(String itemName) {
        return items.removeIf(item -> item.getName().equalsIgnoreCase(itemName));
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    public String getDescription() {
        StringBuilder description = new StringBuilder(super.getDescription());
        if (!items.isEmpty()) {
            description.append(" It contains: ");
            items.forEach(item -> description.append(item.getName()).append(", "));
        } else {
            description.append(" It is empty.");
        }
        return description.toString();
    }
}
