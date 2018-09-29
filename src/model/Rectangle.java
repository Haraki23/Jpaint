package model;

import java.time.chrono.IsoChronology;

public class Rectangle implements IShape {
    private static int id_new;
    private int id;
    private final int area;

    public Rectangle(int area){
        this.area = area;
        id_new = id++;
    }

    public void update(){
        System.out.println("Shape Id is" + id);
    }

    public String getString(){
        return "Rectangle";
    }

}
