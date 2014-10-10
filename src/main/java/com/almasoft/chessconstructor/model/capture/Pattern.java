package com.almasoft.chessconstructor.model.capture;

import com.almasoft.chessconstructor.model.Point;

/**
 * Represents capturing of some subset of points in the Board. For example, KNIGHT can capture 8 points. and Queen 4 lines.
 * 
 * Capture can be united. For example capture of Queen is equal capture of Rook + Bishop 
 * 
 * @author andriy_kostin
 * TODO rename to Pattern
 */
public interface Pattern {
  
  /**
   * Test whether argument point is captured. 
   * @param p
   * @return
   */
  boolean belongsToPattern(Point p, Point origin);
}
