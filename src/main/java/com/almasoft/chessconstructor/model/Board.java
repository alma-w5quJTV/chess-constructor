package com.almasoft.chessconstructor.model;

/**
 * TODO extract interface, get rid from boolean arrays.
 * @author andriy_kostin
 *
 */
public class Board {
  private boolean[][] capturingState;
  
  public Board(Point dimention) {
    capturingState = new boolean[dimention.getY()][dimention.getX()];
  }
  public void allocate(Point p){
    capturingState[p.getY()][p.getX()] = true;
  }
}
