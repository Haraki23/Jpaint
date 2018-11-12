package view;

import controller.*;
import model.ShapeFactory;
import model.persistence.ApplicationState;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class User_Keyboard_Handler extends KeyAdapter {
    private ICommand command = null;
    private ShapeFactory shape;
    private ApplicationState AS;
    private char key_c;
    public User_Keyboard_Handler(ShapeFactory shape, ApplicationState AS) {
        super();
        this.shape = shape;
        this.AS = AS;
        this.key_c = key_c;
    }
    @Override
    public void keyPressed(KeyEvent key) {
        //Put the shape factory as a constructor in the create shape command which would take in the application state or a shape factory (preferable)
        key_c = key.getKeyChar();
        //System.out.println(key_c);
        switch (key_c) {
            case 'c':
                AS.copy();
                break;
            case 'v':
                AS.paste();
            case 'z':
                AS.undo();
                break;
            case 'd':
                AS.delete();
                break;
        }
    }
}
