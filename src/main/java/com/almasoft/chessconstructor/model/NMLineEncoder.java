package com.almasoft.chessconstructor.model;

public class NMLineEncoder {
  public static final byte VERTICAL_LINE_MASK = 0;
  public static final byte HORISONTAL_LINE_MASK = 1;
  /**
   * Lines of form y = x + a 
   */
  public static final byte DIAGONAL_LINE_MASK = 2;
  /**
   * Lines of form y = -x + a 
   */
  public static final byte CO_DIAGONAL_LINE_MASK = 3;
  
  private char[][] container;
  private final int offset;
  public NMLineEncoder(Point dimention) {
    container = new char[4][dimention.getY() + dimention.getX()];
    offset = dimention.getY() - 1;
  }
  public void markLine(int x, int y, byte lineType){
    container[lineType][encodeIndex(x, y, lineType)] ++;
  }
  public void markLine(int x, int y, byte lineType, boolean isMark){
    if(isMark){
      markLine(x, y,lineType);
    }else{
      unmarkLine(x, y,lineType);
    }
  }
  public void unmarkLine(int x, int y, byte lineType){
    container[lineType][encodeIndex(x, y, lineType)] --;
  }
  public boolean isLineMarked(int x, int y, byte lineType){
    return container[lineType][encodeIndex(x, y, lineType)] > 0;
  }
  
  private int encodeIndex(int x, int y, byte lineType){
    if(lineType == VERTICAL_LINE_MASK){
      return offset + x;
    }else if(lineType == HORISONTAL_LINE_MASK){
      return y;
    }else if(lineType == DIAGONAL_LINE_MASK){
      return offset + x - y;
    }else if(lineType == CO_DIAGONAL_LINE_MASK){
      return x + y;
    }
    else{
      throw new IllegalStateException();
    }
  }
}
