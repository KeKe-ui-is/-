package othello;

public class Player {
  private String name;
  private String pieces;
  
  public Player(String name,String pieces) {
	  this.name = name;
	  this.pieces = pieces;
  }
  
  public String getName() {
	  return this.name;
  }
  
  public String getPieces() {
	  return this.pieces;
  }
}
