package controller;

public class JPoint {
    //Initial X & Y
    private int ix;
    private int iy;
    //Final X & Y
    private int fx;
    private int fy;

   public JPoint(){
        //Initial X & Y
        this.ix = ix;
        this.iy = iy;
        //Final X & Y
        this.fx = fx;
        this.fy = fy;
    }

    public void setstartx(int ax){ix = ax;}
    public void setstarty(int ay){iy = ay;}
    public void setendx(int bx){fx = bx;}
    public void setendy(int by){fy = by;}
    public int getstartx(){return ix;}
    public int getstarty(){
        return iy;
    }
    public int getendx(){
        return fx;
    }
    public int getendy(){ return fy; }
}
