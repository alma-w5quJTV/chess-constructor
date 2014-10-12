package com.almasoft.chessconstructor.model.capture;

import com.almasoft.chessconstructor.model.Board;
import com.almasoft.chessconstructor.model.Point;

/**
 * Line capturing, means all points in the form <code>origin + k*vector<code>
 * 
 * in particular case, in chess we have the following list of valid vector values:
 * (1,0), (0,1) -- for Rook
 * (1,1), (1,-1) -- for Bishop
 * (1,1), (1,-1), (1,0), (0,1) -- for Queen
 * 
 * @author andriy_kostin
 */
public class VectorsPattern implements Pattern {
  private Point[] vectors;
  
  public VectorsPattern(Point... vectors) {
    this.vectors = vectors;  
  }
  public Point[] getVectors() {
    return vectors;
  }
  
  @Override
  public boolean belongsToPattern(Point p, Point origin) {
    Point normalized = new Point(p.getX() - origin.getX(), p.getY() - origin.getY());
    
    for (Point v : vectors) {
      if(v.getY() * normalized.getX() == v.getX() * normalized.getY()) return true;
    }
    return false;
  }
  @Override
  public void visit(Board board, Point origin, Visitor v) {
    Point dimension = board.getDimention();
    int dimX = dimension.getX(); 
    int dimY = dimension.getY();
    for (Point vector : vectors) {
      if(vector.getX() == 0){
        for (int y = 0; y < dimY; y++) {
          v.visit(origin.getX(), y);
        }
      }else{
        int k = vector.getY() / vector.getX();//it is ok here, in this particular task (but ugly, FIXME)
        for (int x = 0; x < dimX; x++) {
          int y = origin.getY() + k * (x - origin.getX());
          if(y >= 0 && y < dimY){
            v.visit(x, y);
          }
        }
      }
    }
  }
}
