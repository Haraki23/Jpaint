package view;

import model.ShapeFactory;
import model.interfaces.MouseObserver;
import model.persistence.ApplicationState;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class User_Mouse_Handler extends MouseAdapter {
    ApplicationState appState;
    private List<MouseObserver> observers = new ArrayList();
    //Initial X & Y
    public int ix = 0;
    public int iy = 0;
    //Final X & Y
    public int fx = 0;
    public int fy = 0;
    //Width and Height
    public int w = 0;
    public int h = 0;
    //Released
    public boolean released = false;
    public User_Mouse_Handler(ApplicationState appState){
        this.appState = appState;
        this.ix = ix;
        this.iy = iy;
        this.fx = fx;
        this.fy = fy;
        this.w = w;
        this.h = h;
        this.released = released;

    }



    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        ix = x;
        ix = y;
        System.out.println(x+"     "+y);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ShapeFactory shape = new ShapeFactory(appState);
        int x = e.getX();
        int y = e.getY();
        fx = x;
        fy = y;
        System.out.println("Release from " + x+"     "+y);
        //System.out.println("Width and Height " + fx + "   " + fy);
            released = true;
            shape.createRect(Get_WH_On_Release());
    }

    //Obtains the XY values ont he initial press
    public int[] Get_XY_On_Press (){
        int[] xy = new int[2];
        xy[0] = ix;
        xy[1] = iy;
        return xy;
    }
    //Return the Width and Height of the Object
    public int[] Get_WH_On_Release (){
        int[] wh = new int[2];
        w = Math.abs(fx-ix);
        h = Math.abs(fy-iy);
        wh[0] = Math.abs(fx-ix);
        wh[1] = Math.abs(fy-iy);
        return wh;
    }

    public void addShape(MouseObserver observer){

        observers.add(observer);


    }


}
