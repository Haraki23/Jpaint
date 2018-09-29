package model;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class User_Mouse extends MouseAdapter {

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
}
