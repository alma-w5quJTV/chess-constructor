package com.almasoft.chessconstructor.game;


import org.junit.Assert;
import org.junit.Test;

import com.almasoft.chessconstructor.base.GuiceBaseTest;
import com.almasoft.chessconstructor.model.PieceType;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.inject.Inject;

public class GameTest extends GuiceBaseTest {
  
  @Inject Game game;
  
  @Test public void testGamePopulation(){
    
    game.putPiece(PieceType.KING, 1).putPiece(PieceType.QUEEN, 1).putPiece(PieceType.ROOK, 2);
    Multiset<PieceType> bag = HashMultiset.create();
    
    PieceType pt = null;
    while((pt = game.takeNextPiece()) != null){
      bag.add(pt);
    }
    Assert.assertEquals(1, bag.count(PieceType.KING));
    Assert.assertEquals(1, bag.count(PieceType.QUEEN));
    Assert.assertEquals(2, bag.count(PieceType.ROOK));
    
    Assert.assertEquals(4, bag.size());
  }
}
