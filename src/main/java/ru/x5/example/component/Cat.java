package ru.x5.example.component;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.x5.example.annotation.Cache;
import ru.x5.example.model.Action;

@Component
public class Cat {

    @Autowired
    private Bowl blow;
    private final AtomicInteger happiness = new AtomicInteger(0);
    private static final String catName = "Кот Борис";

    @Cache
    public Action sleep(String dream) {
        String actionString = catName + " спит";
        Action action = new Action();

        actionString += dream != null ? ", ему снится сон про " + dream : " ему не снятся сны";
        action.setFirstAction(actionString);
        action.setResult(dream != null);

        return action;
    }

    public Action eat() {
        String actionString;
        Action action = new Action();

        if (blow.getFeed() != null) {
            actionString = catName + " поел, кот доволен";
            action.setResult(true);
        } else {
            actionString = catName + " пришел, а еды нет, кот огорчился";
            action.setResult(false);
        }

        action.setFirstAction(actionString);
        return action;
    }

    public Action sad() {
        Action action = new Action(catName + " грустит");
        action.setResult(false);
        return action;
    }

    public Action sick() {
        decrementHappiness();
        throw new RuntimeException(catName + " заболел у него RuntimeException");
    }

    public int getHappiness() {
        return happiness.get();
    }

    public void incrementHappiness() {
        happiness.incrementAndGet();
    }

    public void decrementHappiness() {
        happiness.decrementAndGet();
    }
}
