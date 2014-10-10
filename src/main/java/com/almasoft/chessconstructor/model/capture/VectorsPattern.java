package com.almasoft.chessconstructor.model.capture;

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
    return false;
  }
}
