package com.almasoft.chessconstructor.game;

import com.almasoft.chessconstructor.strategy.GameStream;
import com.almasoft.chessconstructor.strategy.State;

public class SimpleGameStream implements GameStream{

  private int counter;

  @Override
  public void onGameReady(State state) {
    counter ++;
  }
  public int getCounter() {
    return counter;
  }
}
