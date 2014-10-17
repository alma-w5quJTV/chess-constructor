package com.almasoft.chessconstructor.game;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import com.almasoft.chessconstructor.model.Point;
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
  protected int counter;

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
  public StringBuilder print(Point dimention) throws IOException{
    StringBuilder out = new StringBuilder();
    for (Iterator<Solution> i = solutions.iterator(); i.hasNext();) {
      print(out, i.next(), dimention);
    }
    return out;
  }

  public void print(Appendable out, Solution solution, Point dimention) throws IOException {
    Arrays.sort(solution.pieces, new Comparator<Piece>(){
      @Override
      public int compare(Piece o1, Piece o2) {
        return o1.y == o2.y ? (o1.x - o2.x) : o1.y - o2.y;
      }});
    Piece[] pieces = solution.pieces;
    int x = 0;
    int y = 0;
    int p = 0;
    out.append("--------\n");
    
    for(int i = 0; i < dimention.getX() * dimention.getY(); i++){
      if(p < pieces.length && pieces[p].x == x && pieces[p].y == y){
        out.append(" ").append(pieces[p].pieceType)/*.append(p)*/.append(" ");
        p++;
      }else{
        out.append(" . ");
      }
      x = (x + 1) % dimention.getX();
      if(x == 0){
        y ++;
        out.append("|\n");
      }
    }
  }

  public static class Solution implements Comparable<Solution>{
    public Solution(State st){
      pieces = new Piece[st.size()];
      
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
      
      pieces = Iterators.toArray(i, Piece.class);
      
      Arrays.sort(pieces);
    }
    private Piece[] pieces;
    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + Arrays.hashCode(pieces);
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
      if (!Arrays.equals(pieces, other.pieces))
        return false;
      return true;
    }
    @Override
    public int compareTo(Solution o) {
      int retVal = o.pieces.length - pieces.length; 
      if(retVal == 0){
        for (int i = 0; i < pieces.length; i++) {
          retVal = pieces[i].compareTo(o.pieces[i]);
          if(retVal != 0) break;
        }
      }
      return retVal;
    }
    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < pieces.length; i++) {
        sb.append(pieces[i]);
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
