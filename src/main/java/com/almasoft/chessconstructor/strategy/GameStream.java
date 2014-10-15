package com.almasoft.chessconstructor.strategy;

/**
 * Stream that is used for handling result of Strategy.
 * 
 * @author Andriy_Kostin
 *
 */
public interface GameStream {
  /**
   * Called when new valid non-conflicting state found.
   * @param state object reflects positions. Users of this method should not modify state.
   * 
   */
  void onGameReady(State state);
  /**
   * 
   * @return number of times when onGameReady method was called. In some 
   * cases (various strategy implementations) this number can be greater than actual number of distinct solutions
   */
  int getCounter();
}
