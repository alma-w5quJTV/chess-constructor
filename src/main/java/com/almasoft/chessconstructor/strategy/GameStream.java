package com.almasoft.chessconstructor.strategy;

public interface GameStream {
  /**
   * Called when new valid non-conflicting state found.
   * @param state object reflects positions. Users of this method should not modify state.
   * 
   */
  void onGameReady(State state);
}
