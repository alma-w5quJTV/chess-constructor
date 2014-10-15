package com.almasoft.chessconstructor.game;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import com.almasoft.chessconstructor.strategy.GameStream;
import com.almasoft.chessconstructor.strategy.State;
import com.google.common.base.Function;
import com.google.common.collect.Iterators;
/**
 * This stream mostly used for testing purposes, namely for controlling count of "isomorphic" States. Means that states that 
 * coincides after renumeration of consisting pieces
 * 
 * Do not use in for production purposes
 * 
 * @author Andriy_Kostin
 *
 */
public class CapturingGameStream implements GameStream{
  
  private Set<Solution> solutions = new TreeSet<Solution>();
  private int counter;

  @Override
  public void onGameReady(State state) {
    counter ++;
    solutions.add(new Solution(state));
  }
  public int getCounter() {
    return counter;
  }
  
  public Set<Solution> getSolutions() {
    return solutions;
  }

  private static class Solution implements Comparable<Solution>{
    private Solution(State st){
      solution = new Piece[st.size()];
      
      Iterator<Piece> i = Iterators.transform(st.getPlacedPiecesIterator(), new Function<com.almasoft.chessconstructor.model.Piece, Piece>(){
        @Override
        public Piece apply(com.almasoft.chessconstructor.model.Piece pp) {
          Piece retVal = new Piece();
          retVal.pieceType = pp.getType().getCode();
          retVal.x = pp.getPosition().getX();
          retVal.y = pp.getPosition().getY();
          return retVal;
        }
      });
      
      solution = Iterators.toArray(i, Piece.class);
      
      Arrays.sort(solution);
    }
    private Piece[] solution;
    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + Arrays.hashCode(solution);
      return result;
    }
    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Solution other = (Solution) obj;
      if (!Arrays.equals(solution, other.solution))
        return false;
      return true;
    }
    @Override
    public int compareTo(Solution o) {
      int retVal = o.solution.length - solution.length; 
      if(retVal == 0){
        for (int i = 0; i < solution.length; i++) {
          retVal = solution[i].compareTo(o.solution[i]);
          if(retVal != 0) break;
        }
      }
      return retVal;
    }
    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < solution.length; i++) {
        sb.append(solution[i]);
      }
      sb.append("\n");
      return sb.toString();
    }
  }
  private static class Piece implements Comparable<Piece>{
    private char pieceType;
    private int x;
    private int y;
    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + pieceType;
      result = prime * result + x;
      result = prime * result + y;
      return result;
    }
    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Piece other = (Piece) obj;
      if (pieceType != other.pieceType)
        return false;
      if (x != other.x)
        return false;
      if (y != other.y)
        return false;
      return true;
    }
    @Override
    public String toString() {
      return "[" + pieceType + " : " + (x + 1) + "," + (y +1) + "]";
    }
    @Override
    public int compareTo(Piece o2) {
      return Character.compare(pieceType, o2.pieceType) == 0 ? 
          (x == o2.x ? 
              Integer.compare(y, o2.y)
              :
              Integer.compare(x, o2.x) ) 
          : 
          Character.compare(pieceType, o2.pieceType);
    }
  }
}
