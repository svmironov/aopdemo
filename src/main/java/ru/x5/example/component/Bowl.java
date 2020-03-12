package ru.x5.example.component;

import org.springframework.stereotype.Component;

@Component
public class Bowl {

    private Object feed;

    public void setFeed(Object feed) {
        this.feed = feed;
    }

    public Object getFeed() {
        return feed;
    }

}
