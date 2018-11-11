package model;

import controller.BoundingBox;
import controller.JPoint;
import model.interfaces.MouseObserver;
import model.persistence.ApplicationState;
import view.gui.PaintCanvas;

import java.awt.*;
import java.util.UUID;

public class Rectangle implements IShape, MouseObserver {
    private int area;
    private JPoint point;
    private ShapeType shapetype;
    private ShapeColor primary;
    private ShapeColor secondary;
    private ShapeShadingType shadingtype;
    private UUID Shape_ID = UUID.randomUUID();
    private BoundingBox BB;
    private int pointmover;
    int w;
    int h;
    private int sX;
    private int sY;
    private int eX;
    private int eY;

    public Rectangle(ApplicationState AS){
        this.primary = AS.getActivePrimaryColor();
        this.secondary = AS.getActiveSecondaryColor();
        this.shadingtype = AS.getActiveShapeShadingType();
        this.sX = AS.getstartx();
        this.sY = AS.getstarty();
        this.eX = AS.getendx();
        this.eY = AS.getendy();
        this.BB = new BoundingBox(sX,sY,eX,eY);
        this.point = new JPoint();
    }

    public Rectangle(int x, int y, BoundingBox Bound_Box, ShapeColor activePrimaryColor, ShapeColor activeSecondaryColor, ShapeShadingType activeShapeShadingType){
        this.sX = x;
        this.sY = y;
        this.point.setstartx(sX);
        this.point.setstarty(sX);
        this.point.setendx(sX);
        this.point.setendy(sX);
        this.BB = Bound_Box;
        this.primary = activePrimaryColor;
        this.secondary = activeSecondaryColor;
        this.shadingtype = activeShapeShadingType;
    }


    @Override
    public JPoint getPoint() {
        return point;
    }

    public int getsx(){return sX;}

    public String getShapeType(){return shapetype.toString();}

    public UUID getShape_ID(){return Shape_ID;}

    public BoundingBox getBB(){return BB;}

    @Override
    public void set_new_pts(JPoint set) {
        //Original Shape Points
        int Sx = point.getstartx();
        int Sy = point.getstarty();
        int Fx = point.getendx();
        int Fy = point.getendy();
        int ex = set.getstartx();
        int ey = set.getstarty();
        int fex = set.getendx();
        int fey = set.getendy();
        int deltax = fex-ex;
        int deltay = fey-ey;
        //Initialize out final points
        int nSx = deltax+Sx;
        int nSy = deltay+Sy;
        int nFx = deltax+Fx;
        int nFy = deltay+Fy;
        //System.out.println("Our Sx " + Sx + " Our Sy " + Sy + " Our Fx " + Fx +" Our Fy "+ Fy);
        point.setstartx(nSx);
        point.setendx(nFx);
        point.setstarty(nSy);
        point.setendy(nFy);
        //System.out.println("Our Sx " + nSx + " Our Sy " + nSy + " Our Fx " + nFx +" Our Fy "+ nFy);
        this.BB = new BoundingBox(nSx,nSy,nFx,nFy);
    }
    public JPoint return_new_pts(){
        //Original Shape Points
        JPoint return_pt = new JPoint();
        //int Sx = point.getstartx();
        //int Sy = point.getstarty();
        //int Fx = point.getendx();
        //int Fy = point.getendy();
        //Initialize out final points
        int nSx = pointmover+sX;
        int nSy = pointmover+sY;
        int nFx = pointmover+eX;
        int nFy = pointmover+eY;
        pointmover+=20;
        //Reset our pointmover
        if(pointmover > 400){
            pointmover = 200;
        }
        //System.out.println("Our Sx " + Sx + " Our Sy " + Sy + " Our Fx " + Fx +" Our Fy "+ Fy);
        return_pt.setstartx(nSx);
        return_pt.setendx(nFx);
        return_pt.setstarty(nSy);
        return_pt.setendy(nFy);
        //System.out.println("Our Sx " + nSx + " Our Sy " + nSy + " Our Fx " + nFx +" Our Fy "+ nFy);
        return return_pt;
    }

    @Override
    public void draw(PaintCanvas PC) {
        int ix = sX;
        int iy = sY;
        int fx = eX;
        int fy = eY;
        //Calculate our width
        w = Math.abs(fx-ix);
        //Calculate our height
        h = Math.abs(fy-iy);
        //We set our graphics 2d object
        Graphics2D g2d = PC.getGraphics2D();
        //We obtain our color from the color legend
        g2d.setColor(ColorLegend.getColor(primary.toString()));
        //We will set our stroke size here
        g2d.setStroke(new BasicStroke(2));
        //Case 1
        if ((iy < fy) && (ix < fx)){
            if(shadingtype.equals(ShapeShadingType.OUTLINE)){
                g2d.drawRect(ix, iy, w, h);
            }
            else if(shadingtype.equals(ShapeShadingType.FILLED_IN)) {
                g2d.fillRect(ix, iy, w, h);
            }
            else if(shadingtype.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)){
                g2d.fillRect(ix, iy, w, h);
                g2d.setColor(ColorLegend.getColor(secondary.toString()));
                g2d.drawRect(ix, iy, w, h);
            }
        }
        //Case 2
        else if ((ix>fx) && (iy>fy)){
            if(shadingtype.equals(ShapeShadingType.OUTLINE)){
                g2d.drawRect(fx, fy, w, h);
            }
            else if(shadingtype.equals(ShapeShadingType.FILLED_IN)) {
                g2d.fillRect(fx, fy, w, h);
            }
            else if(shadingtype.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)){
                g2d.fillRect(fx, fy, w, h);
                g2d.setColor(ColorLegend.getColor(secondary.toString()));
                g2d.drawRect(fx, fy, w, h);
            }
        }
        //Case 3
        else if ((ix<fx) && (iy > fy)){
            if(shadingtype.equals(ShapeShadingType.OUTLINE)){
                g2d.drawRect(fx-w, fy, w, h);
            }
            else if(shadingtype.equals(ShapeShadingType.FILLED_IN)) {
                g2d.fillRect(fx-w, fy, w, h);
            }
            else if(shadingtype.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)){
                g2d.fillRect(fx-w, fy, w, h);
                g2d.setColor(ColorLegend.getColor(secondary.toString()));
                g2d.drawRect(fx-w, fy, w, h);
            }
        }
        //Case 4
        else if ((ix>fx) && (iy < fy)){
            if(shadingtype.equals(ShapeShadingType.OUTLINE)){
                g2d.drawRect(ix-w, iy, w, h);
            }
            else if(shadingtype.equals(ShapeShadingType.FILLED_IN)) {
                g2d.fillRect(ix-w, iy, w, h);
            }
            else if(shadingtype.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)){
                g2d.fillRect(ix-w, iy, w, h);
                g2d.setColor(ColorLegend.getColor(secondary.toString()));
                g2d.drawRect(ix-w, iy, w, h);
            }
        }

    }

}