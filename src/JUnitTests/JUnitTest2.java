package JUnitTests;

import controller.*;
import controller.Commands.CreateShapeCommand;
import controller.Commands.DeleteShapeCommand;
import controller.Commands.ICommand;
import controller.Interfaces.IJPaintController;
import model.Factories.ShapeFactory;
import model.Interfaces.IShape;
import model.Persistence.ApplicationState;
import org.junit.Test;
import view.Handlers.DrawShapeHandler;
import view.GUI.Gui;
import view.GUI.GuiWindow;
import view.GUI.PaintCanvas;
import view.Interfaces.IGuiWindow;
import view.Interfaces.IUiModule;

import static org.junit.Assert.assertEquals;
//This JUnit test will determine if we are succesfully removing shapes using our DeleteShape command when we have shapes selected
public class JUnitTest2 {
    //PaintCanvas and GUI
    PaintCanvas paintCanvas = new PaintCanvas();
    IGuiWindow guiWindow = new GuiWindow(paintCanvas);
    IUiModule uiModule = new Gui(guiWindow);
    //Draw and MouseHandler
    DrawShapeHandler draw = new DrawShapeHandler(paintCanvas);
    ApplicationState appState = new ApplicationState(uiModule, draw);
    IJPaintController controller = new JPaintController(uiModule, appState, paintCanvas);
    ShapeFactory shape = new ShapeFactory(appState, draw);
    //The first UnitTest will check if we are adding shapes successfully to our shapelist using our ShapeFactory
    @Test
    public void testremoveShape() {
        controller.setup();
        ICommand command;
        //A Unit Test to Check if we are Adding shapes to Main Shape List
        command = new CreateShapeCommand(shape, appState.getASPoint(), appState.getJList());
        //We will run the command three times to make sure we added three shapes.
        command.run();
        command.run();
        command.run();
        //We will first add all the shapes to our Selected List for our test
        for(IShape sshape: appState.getJList().getShapeList()){
            appState.getSJList().registerObserver(sshape);
        }
        //We will run the remove command three times to make sure we deleted our shapes
        command = new DeleteShapeCommand(appState, draw);
        command.run();
        command.run();
        command.run();
        //Should be empty now
        assertEquals(0, appState.getJList().getShapeList().size());
    }

}
