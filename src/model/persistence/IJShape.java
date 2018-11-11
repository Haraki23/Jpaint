package model.persistence;


import model.IShape;

import java.util.ArrayList;

public interface IJShape {
    void registerObserver(IShape shape);
    void removeObserver(IShape shape);
    ArrayList<IShape> getShapeList();
    void clear_list();
}
