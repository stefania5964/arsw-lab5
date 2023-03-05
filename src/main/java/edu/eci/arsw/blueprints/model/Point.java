package edu.eci.arsw.blueprints.model;

/**
 *
 * @author hcadavid
 */
public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString(){
        return "Point{ "+"("+x+","+y+")"+"}";
    }

    @Override
    public boolean equals(Object obj) {
        boolean flag = false;
        Point p = (Point)obj;
        if(p.getY()==this.y && p.getX()==this.x){
            flag = true;
        }
        return flag;
    }
}
