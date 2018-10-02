package view;

import model.ShapeColor;
import model.ShapeFactory;
import model.interfaces.MouseObserver;
import model.persistence.ApplicationState;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class User_Mouse_Handler extends MouseAdapter {
    ApplicationState appState;
    Graphics2D G;
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
    public User_Mouse_Handler(ApplicationState appState, Graphics2D G){
        this.appState = appState;
        this.ix = ix;
        this.iy = iy;
        this.fx = fx;
        this.fy = fy;
        this.w = w;
        this.h = h;
        this.G = G;

    }
//Create rect through a command object being instatiated in here
    @Override
    public void mousePressed(MouseEvent e) {
        ix = e.getX();
        iy = e.getY();
        System.out.println(ix+"     "+iy);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //Put the shape factory as a construvtor in the create shape command which would take in the application state or a shape factory (preferable)
        ShapeFactory shape = new ShapeFactory(appState);
        fx = e.getX();
        fy = e.getY();
        w = Math.abs(fx-ix);
        h = Math.abs(fy-iy);
        //System.out.println("Release from " + x + "     "+y);
        //System.out.println("Width and Height " + fx + "   " + fy);
        shape.createRect(Get_WH_On_Release());
        DrawShape(G);
    }
    //Draw shape in graphics 2D
    //Take in the paint canvas instead of the graphics 2D object
    public void DrawShape(Graphics2D G2){
        G2.setColor(Color.blue);
        System.out.println(ix+"   "+iy);
        if (iy < fy){
            G2.fillRect(ix, iy, w, h);
            G2.drawRect(ix, iy, w, h);
        }
        else{
            G2.fillRect(fx, fy, w, h);
            G2.drawRect(fx, fy, w, h);
        }
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
        wh[0] = w;
        wh[1] = h;
        return wh;
    }

    public void addShape(MouseObserver observer){

        observers.add(observer);


    }


}
