package model;

public class Shape {
    private static int id_new;
    private int id;

    public Shape(){
        id_new = id++;
    }

    public void update(){

        System.out.println("Shape Id is" + id);

    }


}
