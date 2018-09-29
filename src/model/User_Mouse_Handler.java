package model;

import model.interfaces.MouseObserver;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class User_Mouse_Handler extends MouseAdapter {

    private List<MouseObserver> observers = new ArrayList();

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println(x+"     "+y);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println("Release from " + x+"     "+y);
    }

    public void addShape(MouseObserver observer){

        observers.add(observer);


    }


}
