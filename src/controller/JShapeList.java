package controller;

import java.util.ArrayList;
import java.util.Stack;

import model.IShape;
import model.persistence.IJShape;

public class JShapeList implements IJShape {
    ArrayList<IShape> Jshapes;
    Stack<IShape> trackLast;

    public JShapeList() {
        this.Jshapes = new ArrayList<>();
        this.trackLast = new Stack<>();
    }

    @Override
    public void registerObserver(IShape shape) {
        Jshapes.add(shape);
    }

    @Override
    public void removeObserver(IShape shape) {
        Jshapes.remove(shape);
    }

    public ArrayList<IShape> getShapeList() {
        return Jshapes;
    }

    public void clear_list() {
        Jshapes.clear();
    }

    public void removeLast() {
        if (Jshapes != null && !Jshapes.isEmpty()) {
            trackLast.push(Jshapes.get(Jshapes.size() - 1));
            Jshapes.remove(Jshapes.size() - 1);
        }
    }

    public void addLast() {
        Jshapes.add(trackLast.pop());
    }

}