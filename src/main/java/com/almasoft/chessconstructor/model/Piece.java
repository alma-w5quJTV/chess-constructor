package com.almasoft.chessconstructor.model;

import com.almasoft.chessconstructor.utils.Assert;


public class Piece {
  private final PieceType type;
  private Point position;
  
  public Piece(PieceType type){
    this.type = type;
  }
  public PieceType getType() {
    return type;
  }
  public Point getPosition() {
    return position;
  }
  public void setPosition(Point position) {
    this.position = position;
  }
  
  /**
   * Tests whether this Piece captures given argument point.
   * @param p
   * @return
   */
  public boolean doesCapture(Point p){
    Assert.assertNotNull(p);
    return type.getCapture().belongsToPattern(p, position);
  }
  @Override
  public String toString() {
    return "Piece [type=" + type + ", position=" + position + "]";
  }
}
