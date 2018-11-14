package JUnitTests;

import controller.*;
import controller.Commands.CreateShapeCommand;
import controller.Commands.ICommand;
import controller.Interfaces.IJPaintController;
import model.Factories.ShapeFactory;
import model.Persistence.ApplicationState;
import org.junit.Test;
import view.Handlers.DrawShapeHandler;
import view.GUI.Gui;
import view.GUI.GuiWindow;
import view.GUI.PaintCanvas;
import view.Interfaces.IGuiWindow;
import view.Interfaces.IUiModule;

import static org.junit.Assert.assertEquals;
//This JUnit test will determine if we have any issues when passing in a null and tests our NullDrawShape Handler Class
public class JUnitTest3 {
    //PaintCanvas and GUI
    PaintCanvas paintCanvas = new PaintCanvas();
    IGuiWindow guiWindow = new GuiWindow(paintCanvas);
    IUiModule uiModule = new Gui(guiWindow);
    //Draw and MouseHandler
    DrawShapeHandler draw = new DrawShapeHandler(paintCanvas);
    ApplicationState appState = new ApplicationState(uiModule, draw);
    IJPaintController controller = new JPaintController(uiModule, appState, paintCanvas);
    @Test
    public void testremoveShape() {
        //We will pass a null value to our ShapeFactory since in it's constructor it is supposed to handle it because of the Null Object Pattern
        ShapeFactory shape = new ShapeFactory(appState, null);
        controller.setup();
        ICommand command;
        //We will create a shape as usual but we will see if we actually hit a Null Pointer Exception this time
        command = new CreateShapeCommand(shape, appState.getASPoint(), appState.getJList());
        command.run();
        //This Unit test will test if our Null Object Pattern will handle if we pass in draw as a null object to our shapeFactory
        //Should have 1 shape now
        assertEquals(1, appState.getJList().getShapeList().size());
    }

}
