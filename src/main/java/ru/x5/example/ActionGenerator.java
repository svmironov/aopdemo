package ru.x5.example;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.x5.example.component.Cat;
import ru.x5.example.model.Action;

public class ActionGenerator implements Runnable {

    static final Logger logger = LogManager.getLogger(ActionGenerator.class);

    private Cat cat;
    private Random random;

    public ActionGenerator(Cat cat) {
        this.cat = cat;
        this.random = new Random();
    }

    @Override
    public void run() {
        generate(0);
    }

    private void generate(int i) {
        Action action;

        try {
            if (i >= 0 && i <= 3) {
                action = cat.eat();
            } else if (i > 3 && i <= 5) {
                action = cat.sleep(null);
            } else if (i == 6) {
                action = cat.sad();
            } else if (i > 6 && i < 9) {
                action = cat.eat();
            } else {
                action = cat.sick();
            }

        } catch (Exception e) {
            action = new Action();
        }


        if (action.getFirstAction() != null) {
            logger.info(action.getFirstAction());
        }

        if (action.getSecondAction() != null) {
            logger.info(action.getSecondAction());
        }

        if (action.getResult() != null) {
            if (action.getResult()) {
                cat.incrementHappiness();
            } else {
                cat.decrementHappiness();
            }
            logger.info("Current happiness index: " + cat.getHappiness());
            logger.info("---");
        }



        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            logger.error("Error executing action", e);
        }

        generate(random.nextInt(12));
    }
}
