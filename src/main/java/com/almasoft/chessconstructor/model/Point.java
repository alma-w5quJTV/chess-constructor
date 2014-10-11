package com.almasoft.chessconstructor.model;

/**
 * Point in the board. We use Cartesian coordinate system, namely 0-based I-quadrant like it's shown 
 * in the diagram 
 * 
 * <code>
 *    y
 *    ^
 *    |
 *   ...
 *  (0,3), (1,3),  ... 
 *  (0,2), (1,2),  ... 
 *  (0,1), (1,1),  ... 
 *  (0,0), (1,0), (2,0), (3,0),... ---> x
 * </code>
 * 
 * @author andriy_kostin
 *
 */
public class Point {
  private int x;
  private int y;
  
  public static final Point VECTOR_1_0 = new Point(1,0);
  public static final Point VECTOR_1_1 = new Point(1,1);
  public static final Point VECTOR_0_1 = new Point(0,1);
  
  public static final Point VECTOR_m1_1 = new Point(-1,1);
  public static final Point VECTOR_m1_0 = new Point(-1,0);
  public static final Point VECTOR_m1_m1 = new Point(-1,-1);
  public static final Point VECTOR_0_m1 = new Point(0,-1);
  public static final Point VECTOR_1_m1 = new Point(1,-1);
  
  public static final Point PLUS_Y = new Point(0,1);
  public static final Point PLUS_XY = new Point(1,1);
  public static final Point MINUS_XY = new Point(1,-1);
  
  public Point() {}
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
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
  public String toString() {
    return "[x=" + x + ", y=" + y + "]";
  }
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + x;
    result = prime * result + y;
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Point other = (Point) obj;
    if (x != other.x)
      return false;
    if (y != other.y)
      return false;
    return true;
  }
}
