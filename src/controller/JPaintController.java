package controller;

import model.interfaces.IApplicationState;
import view.EventName;
import view.gui.PaintCanvas;
import view.interfaces.IUiModule;

import java.awt.*;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private PaintCanvas paintCanvas;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState, PaintCanvas paintCanvas) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.paintCanvas = paintCanvas;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> {applicationState.setActiveShape();paintCanvas.requestFocus();});
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> {applicationState.setActivePrimaryColor();paintCanvas.requestFocus();});
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> {applicationState.setActiveSecondaryColor();paintCanvas.requestFocus();});
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> {applicationState.setActiveShadingType();paintCanvas.requestFocus();});
        uiModule.addEvent(EventName.CHOOSE_START_POINT_ENDPOINT_MODE, () -> {applicationState.setActiveStartAndEndPointMode();paintCanvas.requestFocus();});
        uiModule.addEvent(EventName.COPY, () -> {applicationState.copy();paintCanvas.requestFocus();});
        uiModule.addEvent(EventName.PASTE, () -> {applicationState.paste();paintCanvas.requestFocus();});
        uiModule.addEvent(EventName.DELETE, () -> {applicationState.delete();paintCanvas.requestFocus();});
        uiModule.addEvent(EventName.UNDO, () -> {applicationState.undo();paintCanvas.requestFocus();});
        uiModule.addEvent(EventName.REDO, () -> {applicationState.redo();paintCanvas.requestFocus();});
    }
}
