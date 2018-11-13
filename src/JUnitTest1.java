import controller.CreateShapeCommand;
import controller.ICommand;
import controller.IJPaintController;
import controller.JPaintController;
import model.*;
import model.persistence.ApplicationState;
import org.junit.Test;
import view.DrawShapeHandler;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;
import static org.junit.Assert.assertEquals;

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
    IShape shape_rect = new Rectangle(appState);
    IShape genshape = new Rectangle(appState);
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

        assertEquals(appState.getJList().getShapeList().size(), 3);
    }
}
