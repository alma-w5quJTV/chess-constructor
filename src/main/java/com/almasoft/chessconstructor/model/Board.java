package com.almasoft.chessconstructor.model;

import java.util.Iterator;

/**
 * TODO extract interface, get rid from boolean arrays.
 * @author andriy_kostin
 *
 */
public class Board {
  /**
   * TODO, it might me int[][] to increment and decrement.
   */
  private boolean[][] capturingState;
  private int dimentionX;
  private int dimentionY;
  
  public Board(Point dimention) {
    capturingState = new boolean[dimention.getY()][dimention.getX()];
    this.dimentionX = dimention.getX();
    this.dimentionY = dimention.getY();
  }
  public void allocate(Point p){
    capturingState[p.getY()][p.getX()] = true;
  }
  
  public Iterator<Point> createAllCellIterator(){
    return new BoardIterator();
  }
  /**
   * Simple iterator, that visits all Board cell from (0,0), (1, 0), ... , (N, 0), (0, 1)...
   * Means that we iterate y=0 line, then y=1 line, ... y=dimensionY -1 line
   * @author andriy_kostin
   *
   */
  private class BoardIterator implements Iterator<Point>{
    int x = -1;
    int y = 0;
    @Override
    
    
    public boolean hasNext() {
      return y < dimentionY - 1  || x < dimentionX - 1;
    }

    @Override
    public Point next() {
      if(x == dimentionX - 1){
        x = 0;
        y++;
      }else{
        x ++;
      }
      return new Point(x,y);
    }

    @Override
    public void remove() {
      throw new RuntimeException("Not supported");
    }
  }
}
