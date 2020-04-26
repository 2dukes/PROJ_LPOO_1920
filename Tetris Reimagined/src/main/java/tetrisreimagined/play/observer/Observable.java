package tetrisreimagined.play.observer;

import tetrisreimagined.play.model.Block;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable<T> {
    private List<Observer<T>> observers;

    public Observable() {
        this.observers = new ArrayList<>();
    }

    public void addObserver(Observer<T> observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer<T> observer) {
        observers.remove(observer);
    }

    public void notifyObservers(T subject) {
        for (Observer<T> observer : observers)
            observer.changed(subject);
    }

    public abstract void removeArenaBlocks(List<Block> toRemove); // temos q tirar isto daqui
}