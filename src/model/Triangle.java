package model;

import controller.BoundingBox;
import controller.JPoint;
import model.interfaces.MouseObserver;
import model.persistence.ApplicationState;
import view.gui.PaintCanvas;

import java.awt.*;
import java.util.UUID;

    public class Triangle implements IShape, MouseObserver {
        private JPoint point;
        private ShapeType shapetype;
        private ShapeColor primary;
        private ShapeColor secondary;
        private ShapeShadingType shadingtype;
        private UUID Shape_ID = UUID.randomUUID();
        private BoundingBox BB;
        private int pointmover = 20;
        private int sX;
        private int sY;
        private int eX;
        private int eY;
        private ApplicationState AS;

        public Triangle(ApplicationState AS) {
            this.AS = AS;
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

        public Triangle(int x, int y, int ex, int ey, BoundingBox Bound_Box, ShapeColor activePrimaryColor, ShapeColor activeSecondaryColor, ShapeShadingType activeShapeShadingType, ShapeType ST) {
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
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
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
            return new Triangle(sX + pointmover, sY + pointmover, eX + pointmover, eY + pointmover, BB, primary, secondary, shadingtype, shapetype);
        }

        @Override
        public String getShapeType() {
            return shapetype.toString();
        }

        public void setPrimary(){
            primary = AS.getActivePrimaryColor();
        }

        public void setSecondary(){
            secondary = AS.getActiveSecondaryColor();
        }

        @Override
        public ShapeColor getPrimary() {
            return primary;
        }

        @Override
        public ShapeColor getSecondary() {
            return secondary;
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
            int diffx = (eX - sX);
            int diffxh = diffx / 2;
            int diffy = (eY - sY);
            int lX = Math.min(eX, sX);
            int lY = Math.min(eY, sY);
            int [] X = new int[]{lX + diffxh, lX, lX + diffx};
            int [] Y = new int[]{lY, lY + diffy, lY + diffy};
            Graphics2D g2d = PC.getGraphics2D();
            //We obtain our color from the color legend
            g2d.setColor(ColorLegend.getColor(primary.toString()));
            //We will set our stroke size here
            g2d.setStroke(new BasicStroke(2));
            //Case 1
            if(sX < eX && sY < eY){
            if (this.shadingtype.equals(shadingtype.OUTLINE)) {
                g2d.drawPolygon(X, Y, 3);
            } else if (this.shadingtype.equals(shadingtype.FILLED_IN)) {
                g2d.fillPolygon(X, Y, 3);
            } else if (this.shadingtype.equals(shadingtype.OUTLINE_AND_FILLED_IN)) {
                g2d.fillPolygon(X, Y, 3);
                g2d.setColor(ColorLegend.getColor(secondary.toString()));
                g2d.drawPolygon(X, Y, 3);
            }
            }
            //Case 2
            else if (sX > eX && eY<sY ){
                X = new int[]{lX - diffxh, lX, lX - diffx};
                Y = new int[]{lY, lY - diffy, lY - diffy};
                if (this.shadingtype.equals(shadingtype.OUTLINE)) {
                    g2d.drawPolygon(X, Y, 3);
                } else if (this.shadingtype.equals(shadingtype.FILLED_IN)) {
                    g2d.fillPolygon(X, Y, 3);
                } else if (this.shadingtype.equals(shadingtype.OUTLINE_AND_FILLED_IN)) {
                    g2d.fillPolygon(X, Y, 3);
                    g2d.setColor(ColorLegend.getColor(secondary.toString()));
                    g2d.drawPolygon(X, Y, 3);
                }
            }
            //Case 3
            else if (sX > eX && sY<eY ){
                X = new int[]{lX - diffxh, lX, lX - diffx};
                Y = new int[]{lY, lY + diffy, lY + diffy};
                if (this.shadingtype.equals(shadingtype.OUTLINE)) {
                    g2d.drawPolygon(X, Y, 3);
                } else if (this.shadingtype.equals(shadingtype.FILLED_IN)) {
                    g2d.fillPolygon(X, Y, 3);
                } else if (this.shadingtype.equals(shadingtype.OUTLINE_AND_FILLED_IN)) {
                    g2d.fillPolygon(X, Y, 3);
                    g2d.setColor(ColorLegend.getColor(secondary.toString()));
                    g2d.drawPolygon(X, Y, 3);
                }
            }
            //Case 4
            else{
                X = new int[]{lX + diffxh, lX, lX + diffx};
                Y = new int[]{lY, lY - diffy, lY - diffy};
                if (this.shadingtype.equals(shadingtype.OUTLINE)) {
                    g2d.drawPolygon(X, Y, 3);
                } else if (this.shadingtype.equals(shadingtype.FILLED_IN)) {
                    g2d.fillPolygon(X, Y, 3);
                } else if (this.shadingtype.equals(shadingtype.OUTLINE_AND_FILLED_IN)) {
                    g2d.fillPolygon(X, Y, 3);
                    g2d.setColor(ColorLegend.getColor(secondary.toString()));
                    g2d.drawPolygon(X, Y, 3);
                }
            }
        }
    }