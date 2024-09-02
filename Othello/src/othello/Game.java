package othello;



public class Game {
 public static void main(String[] args) {
	 
		
		//オセロに入れる駒
		final String empty = " ";
		final String white = "○";
		final String black = "×";
		
		 
		 //プレイヤー１作成
		 Player player1 = new Player("player1",white);
		 //プレイヤー2作成
		  Player player2 = new Player("player2",black);
		 //
		final String[][] field = new String[9][9];
	 
	    GameSystem game = new GameSystem();
	    //ゲームが終わるまで繰り返したいので
	      boolean end = false;
	    //置くところがない時のスキップ  
		  boolean playerSkip1 =false;
		  boolean playerSkip2 =false;
		//駒を置いた時の座標を受け取る変数
		  int[] xy = new int[2];

	  //初期化
	  for(int i = 0; i <= 8; i ++) {
		  String mozi = String.valueOf(i);
		  field[0][i] = mozi;
		  field[i][0] = mozi;
	  }
	  
	  for(int i = 1; i <= 8; i++) {	 
		  for(int j = 1; j <= 8; j++) {
			  field[i][j] = empty;
		  }
	  }
	  field[4][4] = white;
	  field[5][5] = white;
	  field[4][5] = black;
	  field[5][4] = black;
	 //
	  
	  game.view(field,empty);

	  while(end == false) {
	
		  playerSkip1 =false;
		  playerSkip2 =false;

		  //プレイヤー1の行動
		playerSkip1 =  game.skip(player1, field, white, black, empty);
		  if(playerSkip1 == true) {
			  xy = game.setOthelloPieces(player1,field,white,black,empty);
			  game.checkPieces(player1,field,xy,white,black,empty);
			  end = game.check(field, empty,white,black);
			  if(end == true) {
				  break;
			  }
			  game.view(field,empty);
		  }else {
			  System.out.println(player1.getName() + "置ける場所がないためスキップしました");
		  }
		     
		  //プレイヤー2の行動
		  playerSkip2 =  game.skip(player2, field, white, black, empty);
		  if(playerSkip2 == true) {
			  xy = game.setOthelloPieces(player2,field,white,black,empty);
			  game.checkPieces(player2,field,xy,white,black,empty);
			  end = game.check(field, empty,white,black);
			  if(end == true) {
				  break;
			  }
			  game.view(field,empty);			  
		  }else {
			  System.out.println(player2.getName() + "置ける場所がないためスキップしました");
			  if(playerSkip1 == false && playerSkip2 == false) {
				  end = game.check(field, empty,white,black);
			  }
		  }
	  }


	   
  
 }
}
