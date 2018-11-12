package model;

import controller.BoundingBox;
import controller.JPoint;
import model.interfaces.MouseObserver;
import model.persistence.ApplicationState;
import view.gui.PaintCanvas;

import java.awt.*;
import java.util.UUID;

public class Rectangle implements IShape, MouseObserver {
    private JPoint point;
    private ShapeType shapetype;
    private ShapeColor primary;
    private ShapeColor secondary;
    private ShapeShadingType shadingtype;
    private UUID Shape_ID = UUID.randomUUID();
    private BoundingBox BB;
    private int pointmover = 20;
    int w;
    int h;
    private int sX;
    private int sY;
    private int eX;
    private int eY;
    private ApplicationState AS;

    public Rectangle(ApplicationState AS) {
        this.AS=AS;
        this.primary = AS.getActivePrimaryColor();
        this.secondary = AS.getActiveSecondaryColor();
        this.shadingtype = AS.getActiveShapeShadingType();
        this.shapetype = AS.getActiveShapeType();
        this.sX = AS.getstartx();
        this.sY = AS.getstarty();
        this.eX = AS.getendx();
        this.eY = AS.getendy();
        this.BB = new BoundingBox(sX, sY, eX, eY);
        this.point = new JPoint();
    }

    public Rectangle(int x, int y, int ex, int ey, BoundingBox Bound_Box, ShapeColor activePrimaryColor, ShapeColor activeSecondaryColor, ShapeShadingType activeShapeShadingType, ShapeType ST) {
        this.sX = x;
        this.sY = y;
        this.eX = ex;
        this.eY = ey;
        this.BB = Bound_Box;
        this.primary = activePrimaryColor;
        this.secondary = activeSecondaryColor;
        this.shadingtype = activeShapeShadingType;
        this.shapetype = ST;
    }


    @Override
    public JPoint getPoint() {
        return point;
    }

    public void printall() {
        System.out.println(shapetype.toString()+" ShapeID [" + getShape_ID().toString()+ "] | (sX,Sy,eX,eY): " + sX +","+ sY + "," + eX + "," + eY +" | Primary [" + this.primary.toString()+"] | Secondary [" + this.secondary.toString()+"]" );
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    @Override
    public IShape copyShape() {
        //We want to set an offset here for pasting our points
        //Set the offset to the shape
        pointmover += 10;
        //Reset our pointmover
        if (pointmover > 400) {
            pointmover = 200;
        }
        return new Rectangle(sX + pointmover, sY + pointmover, eX+pointmover, eY+pointmover, BB, primary, secondary, shadingtype, shapetype);
    }

    public UUID getShape_ID() {
        return Shape_ID;
    }

    public BoundingBox getBB() {
        return BB;
    }

    @Override
    public void set_new_pts(JPoint set) {
        int ex = set.getstartx();
        int ey = set.getstarty();
        int fex = set.getendx();
        int fey = set.getendy();
        int deltax = fex - ex;
        int deltay = fey - ey;
        //Initialize out final points
        int nSx = deltax + sX;
        int nSy = deltay + sY;
        int nFx = deltax + eX;
        int nFy = deltay + eY;
        //System.out.println("Our Sx " + Sx + " Our Sy " + Sy + " Our Fx " + Fx +" Our Fy "+ Fy);
        sX = nSx;
        sY = nSy;
        eX = nFx;
        eY = nFy;
        //System.out.println("Our Sx " + nSx + " Our Sy " + nSy + " Our Fx " + nFx +" Our Fy "+ nFy);
        this.BB = new BoundingBox(nSx, nSy, nFx, nFy);
    }

    public void movepos(int deltax, int deltay) {

        //Initialize out final points
        int nSx = sX - deltax;
        int nSy = sY - deltay;
        int nFx = eX - deltax;
        int nFy = eY - deltay;
        //System.out.println("Our Sx " + Sx + " Our Sy " + Sy + " Our Fx " + Fx +" Our Fy "+ Fy);
        sX = nSx;
        sY = nSy;
        eX = nFx;
        eY = nFy;
        //System.out.println("Our Sx " + nSx + " Our Sy " + nSy + " Our Fx " + nFx +" Our Fy "+ nFy);
        this.BB = new BoundingBox(nSx, nSy, nFx, nFy);

    }

    public void moveneg(int deltax, int deltay) {

        //Initialize out final points
        int nSx = sX + deltax;
        int nSy = sY + deltay;
        int nFx = eX + deltax;
        int nFy = eY + deltay;
        //System.out.println("Our Sx " + Sx + " Our Sy " + Sy + " Our Fx " + Fx +" Our Fy "+ Fy);
        sX = nSx;
        sY = nSy;
        eX = nFx;
        eY = nFy;
        //System.out.println("Our Sx " + nSx + " Our Sy " + nSy + " Our Fx " + nFx +" Our Fy "+ nFy);
        this.BB = new BoundingBox(nSx, nSy, nFx, nFy);

    }

    @Override
    public void draw(PaintCanvas PC) {
        int ix = sX;
        int iy = sY;
        int fx = eX;
        int fy = eY;
        //Calculate our width
        this.w = Math.abs(eX - sX);
        //Calculate our height
        this.h = Math.abs(eY - sY);
        //We set our graphics 2d object
        Graphics2D g2d = PC.getGraphics2D();
        //We obtain our color from the color legend
        g2d.setColor(ColorLegend.getColor(primary.toString()));
        //We will set our stroke size here
        g2d.setStroke(new BasicStroke(2));
        //Case 1
        if ((iy < fy) && (ix < fx)) {
            if (shadingtype.equals(ShapeShadingType.OUTLINE)) {
                g2d.drawRect(ix, iy, w, h);
            } else if (shadingtype.equals(ShapeShadingType.FILLED_IN)) {
                g2d.fillRect(ix, iy, w, h);
            } else if (shadingtype.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)) {
                g2d.fillRect(ix, iy, w, h);
                g2d.setColor(ColorLegend.getColor(secondary.toString()));
                g2d.drawRect(ix, iy, w, h);
            }
        }
        //Case 2
        else if ((ix > fx) && (iy > fy)) {
            if (shadingtype.equals(ShapeShadingType.OUTLINE)) {
                g2d.drawRect(fx, fy, w, h);
            } else if (shadingtype.equals(ShapeShadingType.FILLED_IN)) {
                g2d.fillRect(fx, fy, w, h);
            } else if (shadingtype.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)) {
                g2d.fillRect(fx, fy, w, h);
                g2d.setColor(ColorLegend.getColor(secondary.toString()));
                g2d.drawRect(fx, fy, w, h);
            }
        }
        //Case 3
        else if ((ix < fx) && (iy > fy)) {
            if (shadingtype.equals(ShapeShadingType.OUTLINE)) {
                g2d.drawRect(fx - w, fy, w, h);
            } else if (shadingtype.equals(ShapeShadingType.FILLED_IN)) {
                g2d.fillRect(fx - w, fy, w, h);
            } else if (shadingtype.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)) {
                g2d.fillRect(fx - w, fy, w, h);
                g2d.setColor(ColorLegend.getColor(secondary.toString()));
                g2d.drawRect(fx - w, fy, w, h);
            }
        }
        //Case 4
        else if ((ix > fx) && (iy < fy)) {
            if (shadingtype.equals(ShapeShadingType.OUTLINE)) {
                g2d.drawRect(ix - w, iy, w, h);
            } else if (shadingtype.equals(ShapeShadingType.FILLED_IN)) {
                g2d.fillRect(ix - w, iy, w, h);
            } else if (shadingtype.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)) {
                g2d.fillRect(ix - w, iy, w, h);
                g2d.setColor(ColorLegend.getColor(secondary.toString()));
                g2d.drawRect(ix - w, iy, w, h);
            }
        }

    }

}