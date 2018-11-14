package JUnitTests;

import controller.Commands.CreateShapeCommand;
import controller.Commands.ICommand;
import controller.Interfaces.IJPaintController;
import controller.JPaintController;
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

//This JUnit Test is testing our create shape command and making sure we are successfully creating shapes
public class JUnitTest1 {
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
    public void testcreateShape() {
        controller.setup();
        ICommand command;
        //A Unit Test to Check if we are Adding shapes to Main Shape List
        command = new CreateShapeCommand(shape, appState.getASPoint(), appState.getJList());
        //We will run the command three times to make sure we added three shapes.
        command.run();
        command.run();
        command.run();

        assertEquals(3, appState.getJList().getShapeList().size());
    }
}
