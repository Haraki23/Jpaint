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

public class JUnitTests {
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
    @Test
    public void testregisterObserver() {
        controller.setup();
        //A Unit Test to Check if we are Adding shapes to Main Shape List
        appState.getJList().registerObserver(shape_rect);

        assertEquals(appState.getJList().getShapeList().size(), 1);
    }

    @Test
    public void testremoveObserver() {
        controller.setup();
        //A Unit Test to Check if we are Removing Shapes from Main Shape List
        appState.getJList().removeObserver(shape_rect);

        assertEquals(appState.getJList().getShapeList().size(), 0);
    }

    @Test
    public void testshapeTypes() {
        controller.setup();
        //A Unit Test to Check if we are Removing Shapes from Main Shape List
        appState.setActiveShapeTester(ShapeType.RECTANGLE);
        genshape = new Rectangle(appState);
        assertEquals("RECTANGLE", genshape.getShapeType());
        appState.setActiveShapeTester(ShapeType.ELLIPSE);
        genshape = new Ellipse(appState);
        assertEquals("ELLIPSE", genshape.getShapeType());
        appState.setActiveShapeTester(ShapeType.TRIANGLE);
        genshape = new Triangle(appState);
        assertEquals("TRIANGLE", genshape.getShapeType());
        }

}
