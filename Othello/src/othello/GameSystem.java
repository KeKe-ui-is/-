package othello;

import java.util.Scanner;

public class GameSystem {
	
	 //表示
	 public void view(String[][] field,String empty) {
		  for(int i = 0; i <= 8; i++) {
			  for(int j = 0; j <= 8; j++) {
				  if(j == 8) {
					  System.out.println(" " + field[i][j] + " |");
					  System.out.println("――――――――――――――――――――――――――――――――――――――");
				  }else {
					  System.out.print(" " + field[i][j] + " |");
					  
				  }
			  }
		  }
	 }
  
	
	 //入力するメソッド

	 public int[] setOthelloPieces(Player p,String[][] field,String white,String black,String empty) {
		   
		 System.out.println(p.getName() +"のターンあなたの駒は " + p.getPieces() +" です");
		 //駒を指定して置く処理
		  Scanner player = new Scanner(System.in);
		  
		  System.out.println("縦方向の数字を入力してください");
          int tate = player.nextInt();
			  tate = checkTate(tate,player);
				 
		   System.out.println("横方向の数字を入力してください");
		   int yoko = player.nextInt();
		   yoko = checkTate(yoko,player);

		   boolean setAble = booCheck(p ,field,tate,yoko,white,black,empty);
		   //置く予定の所が空でないと置けなくする
		    while(!field[tate][yoko].equals(empty) || setAble == false) {
		    	System.out.println("その場所には置けません");
		    	System.out.println("再度入力してください");
		 	   System.out.println("縦方向の数字を入力してください");
			    tate = player.nextInt();
				System.out.println("横方向の数字を入力してください");
				yoko = player.nextInt();
		 	   tate = checkTate(tate,player);
		 	  setAble = booCheck(p ,field,tate,yoko,white,black,empty);
		 	   yoko = checkTate(yoko,player);
		 	  setAble = booCheck(p ,field,tate,yoko,white,black,empty);
		 		if(field[tate][yoko].equals(empty)&& setAble == true) {
		 			break;
		 		}
		    	
		    }
		   //受け取ったプレイヤーによっておく駒を判別
		   if(p.getName().equals("player1")) {
			   field[tate][yoko] = white;	
		   }else if(p.getName().equals("player2")) {
			   field[tate][yoko] = black;	    
		   }
		   int[] tateYoko = new int[2];
		   tateYoko[0] = tate;
		   tateYoko[1] = yoko;
		   System.out.println("ターン終了");
		   //座標を返す
		   return tateYoko;
	 }
	   //数字が不正だとやり直す
	 public int checkTate(int tate, Scanner player) {
	 	   while(tate < 0 || tate > 8) {
	 		   System.out.println("その数値は入力できません再度入力してください");
	 		   System.out.println("縦方向の数字を入力してください");
	 		    tate = player.nextInt();
	 	   }
	 	   return tate; 
	 }
	 
	 public int checkYoko(int yoko, Scanner player) {
	 	   while(yoko < 0 || yoko > 8) {
	 		   System.out.println("その数値は入力できません再度入力してください");
	 		   System.out.println("横方向の数字を入力してください");
	 		    yoko = player.nextInt();
	 	   }
	 	   return yoko;
	 }
	  
	 //オセロのルールで置けない所があるか判定
     public boolean booCheck(Player p,String[][] field,int tate,int yoko,String white,String black,String empty) {
   	  boolean setAble = false;
   	  
		  String pieces = " ";
		  String enemyPieces = " ";
		  switch(p.getName()) {
		  case "player1":
			  pieces = white;
			  enemyPieces = black;
			  break;
		  case "player2":
			  pieces = black;
			  enemyPieces = white;
		  }	  
   	  while(setAble == false) {
   		  setAble = booDown(field,tate,yoko,pieces,enemyPieces,empty);
   		   if(setAble == true) {
   			   break;
   		   }
   		  setAble = booUp(field,tate,yoko,pieces,enemyPieces,empty);
   		   if(setAble == true) {
   			   break;
   		   }
   		  setAble = booRight(field,tate,yoko,pieces,enemyPieces,empty);
   		   if(setAble == true) {
   			   break;
   		   }
   		  setAble = booLeft(field,tate,yoko,pieces,enemyPieces,empty);
   		   if(setAble == true) {
   			   break;
   		   }
   		  setAble = booRightUp(field,tate,yoko,pieces,enemyPieces,empty);
   		   if(setAble == true) {
   			   break;
   		   }
   		  setAble = booRightDown(field,tate,yoko,pieces,enemyPieces,empty);
   		   if(setAble == true) {
   			   break;
   		   }
   		  setAble = booLeftUp(field,tate,yoko,pieces,enemyPieces,empty);
   		   if(setAble == true) {
   			   break;
   		   }
   		  setAble = booLeftDown(field,tate,yoko,pieces,enemyPieces,empty);
   		   if(setAble == true) {
   			   break;
   		   }
   		  break;
   	  }
   	  return setAble;
     }
   
			  //したの処理
			   public boolean  booDown(String[][] field,int tate,int yoko,String pieces,String enemyPieces,String empty) {
				  if(tate < 8) {
					  String next = field[tate + 1][yoko];
					   if(next.equals(enemyPieces)) {
						   for(int i = 2; true; i++) {
							   //空の時の処理　か突き当り
							   if(tate + i > 8 || field[tate + i][yoko].equals(empty)) {
								   break;
								  //自分の駒だった時 
							   }else if(field[tate + i][yoko].equals(pieces)) {
								   return true;
							   }
						   }
					   }   
				    }   
				  return false;
		     	 }
   

				  //
				  
				  //うえの処理
			   public boolean booUp(String[][] field,int tate,int yoko,String pieces,String enemyPieces,String empty) {
				  if(tate > 1){
					  String next = field[tate - 1][yoko];
					   if(next.equals(enemyPieces)) {
						   for(int i = 2; true; i++) {
							   //空の時の処理　か　突き当りの時
							   if(tate - i < 1 || field[tate - i][yoko].equals(empty)) {
								   break;
								  //自分の駒だった時 
							   }else if(field[tate - i][yoko].equals(pieces)) {
								   return true;
							   }
						   }
					   }
				    }
				  return false;
		         }
				  
				  //みぎの処理
		      public boolean booRight(String[][] field,int tate,int yoko,String pieces,String enemyPieces,String empty) {
				  if(yoko < 8){
					  String next = field[tate][yoko + 1];
					   if(next.equals(enemyPieces)) {
						   for(int i = 2; true; i++) {
							   //空の時の処理　か　突き当り
							   if(yoko + i > 8  || field[tate][yoko + i].equals(empty)) {
								   break;
								  //自分の駒だった時 
							   }else if(field[tate][yoko + i].equals(pieces)) {
								   return true;
					        }
				         }
			          }
				   }	
				  return false;
		        }
				  //ひだりの処理
				   public boolean booLeft(String[][] field,int tate,int yoko,String pieces,String enemyPieces,String empty) {
				  if(yoko > 1) {
					  String next = field[tate][yoko -1];
					   if(next.equals(enemyPieces)) {
						   for(int i = 2; true; i++) {
							   //空の時の処理　か	突き当り
							   if(yoko - i < 1 ||field[tate][yoko - i].equals(empty)) {
								   break;
								  //自分の駒だった時 
							   }else if(field[tate][yoko - i].equals(pieces)) {
								   return true;
							   }
						   }
					   }
				     } 
				  return false;
				  }
				  //みぎうえの処理
				   public boolean booRightUp(String[][] field,int tate,int yoko,String pieces,String enemyPieces,String empty) {
				  if(tate > 1 && yoko < 8) {
					  String next = field[tate - 1][yoko + 1];
					  if(next.equals(enemyPieces)) {
						  for(int i = 2; true; i++) {
							  if((tate - i <= 0 || yoko + i >8) ||field[tate - i][yoko + i].equals(empty)) {
								  break;
							  }else if(field[tate - i][yoko + i].equals(pieces)) {
								  return true;
							  } 
						  }
					   }
				    } 
				  return false;
				 }
				  //みぎしたの処理
				   public boolean booRightDown(String[][] field,int tate,int yoko,String pieces,String enemyPieces,String empty) {
				  if(tate < 8  && yoko < 8) {
					  String next = field[tate + 1][yoko + 1];
					  if(next.equals(enemyPieces)) {
						  for(int i = 2; true; i++) {
							  if((tate + i > 8 || yoko + i > 8) ||field[tate + i][yoko + i].equals(empty)) {
								  break;
							  }else if(field[tate + i][yoko + i].equals(pieces)) {
								  return true;
							  }			  
						  }
					   }
				    }
				  return false;
				 } 
				  
				  //ひだりしたの処理
				   public boolean booLeftDown(String[][] field,int tate,int yoko,String pieces,String enemyPieces,String empty) {
					   if(tate < 8  && yoko > 1) {
						   String next = field[tate + 1][yoko - 1];
						   if(next.equals(enemyPieces)) {
							   for(int i = 2; true; i++) {
								   if((tate + i > 8 || yoko - i < 1) ||field[tate + i][yoko - i].equals(empty)) {
									   break;
								   }else if(field[tate + i][yoko - i].equals(pieces)) {
										   return true;
							   }
						   }
					   }
  					 }
					   return false;
				  }
				  //ひだりうえの処理
				   public boolean booLeftUp(String[][] field,int tate,int yoko,String pieces,String enemyPieces,String empty) {
					   if(tate > 1  && yoko > 1) {
						   String next = field[tate - 1][yoko - 1];
						   if(next.equals(enemyPieces)) {
							   for(int i = 2; true; i++) {
								   if((tate - i < 1 || yoko - i < 1) ||field[tate - i][yoko - i].equals(empty)) {
									   break;
								   }else if(field[tate - i][yoko - i].equals(pieces)) {
									   return true;
								   }
							   }
						    }
					     }
					   return false;
				      }
				   
					  //置くところがないとスキップされる処理
					  public boolean skip(Player p,String[][]field,String white,String black,String empty) {
						  boolean skip = false;
						  for(int tate = 1; tate < 9; tate++) {
							  for(int yoko = 1; yoko < 9; yoko++) {
								   skip = booCheck(p ,field,tate,yoko,white,black,empty);
								   if(skip == true) {
									   return skip;
								   }
							  }
						  }
						  return skip;
					  }
					  
			       
	 
	 
	 //盤面が空かチェックする
	  public boolean check(String[][] field,String empty,String white,String black) {
		  int kara = 0;
		  int whiteCount = 0;
		  int blackCount = 0;
		  for(int i = 1; i <= 8; i++) {
			  for(int j = 1; j <= 8; j++) {
				  if(field[i][j].equals(empty)){
					  kara++;
				  }else if(field[i][j].equals(white)) {
					  whiteCount++;
				  }else if(field[i][j].equals(black)){
					  blackCount++;
				  }
			  }
		  }
		  System.out.println("空の数は" + kara + "こ");
		  if(kara == 0 || whiteCount == 0 || blackCount == 0) {
			  System.out.println("白の数" +  whiteCount);
			  System.out.println("黒の数" +  blackCount);
			  if(whiteCount > blackCount) {
				  System.out.println("プレイヤー1の勝利");
			  }else if(blackCount > whiteCount) {
				  System.out.println("プレイヤー2の勝利");
			  }
			  return true;
		  }else {
			  return false;
		  }
	  }
	  

	 //挟まれたひっくり返す処理
	  public void checkPieces(Player p,String[][] field,int[] xy,String white,String black, String empty) {
		  int tate =xy[0];
		  int yoko =xy[1];
		  String pieces = " ";
		  String enemyPieces = " ";
		  switch(p.getName()) {
		  case "player1":
			  pieces = white;
			  enemyPieces = black;
			  break;
		  case "player2":
			  pieces = black;
			  enemyPieces = white;
		  }	  
		  
		  up(field,tate,yoko,pieces,enemyPieces,empty);
		  down(field,tate,yoko,pieces,enemyPieces,empty);
		  right(field,tate,yoko,pieces,enemyPieces,empty);
		  left(field,tate,yoko,pieces,enemyPieces,empty);
		  rightUp(field,tate,yoko,pieces,enemyPieces,empty);
		  rightDown(field,tate,yoko,pieces,enemyPieces,empty);
		  leftUp(field,tate,yoko,pieces,enemyPieces,empty);
		  leftDown(field,tate,yoko,pieces,enemyPieces,empty);
		  
	  }
	  
	
	    
	
	  
	  //したの処理
	   public void down(String[][] field,int tate,int yoko,String pieces,String enemyPieces,String empty) {
		  if(tate < 8) {
			  String next = field[tate + 1][yoko];
			   if(next.equals(enemyPieces)) {
				   for(int i = 2; true; i++) {
					   //空の時の処理　か突き当り
					   if(tate + i > 8 || field[tate + i][yoko].equals(empty)) {
						   break;
						  //自分の駒だった時 
					   }else if(field[tate + i][yoko].equals(pieces)) {
						   for(int k = 1; k < i;k++) {
							   field[tate + k][yoko] = pieces;
						   }
					   }
				   }
			   }   
		    }   
     	 }
		  //
		  
		  //うえの処理
	   public void up(String[][] field,int tate,int yoko,String pieces,String enemyPieces,String empty) {
		  if(tate > 1){
			  String next = field[tate - 1][yoko];
			   if(next.equals(enemyPieces)) {
				   for(int i = 2; true; i++) {
					   //空の時の処理　か　突き当りの時
					   if(tate - i < 1 || field[tate - i][yoko].equals(empty)) {
						   break;
						  //自分の駒だった時 
					   }else if(field[tate - i][yoko].equals(pieces)) {
						   for(int k = 1; k < i;k++) {
							   field[tate - k][yoko] = pieces;
						   }
					   }
				   }
			   }
		    }
         }
		  
		  //みぎの処理
      public void right(String[][] field,int tate,int yoko,String pieces,String enemyPieces,String empty) {
		  if(yoko < 8){
			  String next = field[tate][yoko + 1];
//			  System.out.println(next);
			   if(next.equals(enemyPieces)) {
				   for(int i = 2; true; i++) {
					   //空の時の処理　か　突き当り
					   if(yoko + i > 8  || field[tate][yoko + i].equals(empty)) {
						   break;
						  //自分の駒だった時 
					   }else if(field[tate][yoko + i].equals(pieces)) {
						   for(int k = 1; k < i;k++) {
							   field[tate][yoko + k] = pieces;
					   }
			        }
		         }
	          }
		   }			   
        }
		  //ひだりの処理
		   public void left(String[][] field,int tate,int yoko,String pieces,String enemyPieces,String empty) {
		  if(yoko > 1) {
			  String next = field[tate][yoko -1];
			   if(next.equals(enemyPieces)) {
				   for(int i = 2; true; i++) {
					   //空の時の処理　か	突き当り
					   if(yoko - i < 1 ||field[tate][yoko - i].equals(empty)) {
						   break;
						  //自分の駒だった時 
					   }else if(field[tate][yoko - i].equals(pieces)) {
						   for(int k = 1; k < i;k++) {
							   field[tate][yoko - k] = pieces;
						   }
					   }
				   }
			   }
		     }  
		  }
		  //みぎうえの処理
		   public void rightUp(String[][] field,int tate,int yoko,String pieces,String enemyPieces,String empty) {
		  if(tate > 1 && yoko < 8) {
			  String next = field[tate - 1][yoko + 1];
			  if(next.equals(enemyPieces)) {
				  for(int i = 2; true; i++) {
					  if((tate - i <= 0 || yoko + i >8) ||field[tate - i][yoko + i].equals(empty)) {
						  break;
					  }else if(field[tate - i][yoko + i].equals(pieces)) {
						  for(int k = 1; k < i; k++) {
							  field[tate -k][yoko + k] = pieces;
						  }
					  } 
				  }
			   }
		    } 
		 }
		  //みぎしたの処理
		   public void rightDown(String[][] field,int tate,int yoko,String pieces,String enemyPieces,String empty) {
		  if(tate < 8  && yoko < 8) {
			  String next = field[tate + 1][yoko + 1];
			  if(next.equals(enemyPieces)) {
				  for(int i = 2; true; i++) {
					  if((tate + i > 8 || yoko + i > 8) ||field[tate + i][yoko + i].equals(empty)) {
						  break;
					  }else if(field[tate + i][yoko + i].equals(pieces)) {
						  for(int k = 1; k < i; k++) {
							  field[tate + k][yoko + k] = pieces;
						  }
					  }			  
				  }
			   }
		    }
		 } 
		  
		  //ひだりしたの処理
		   public void leftDown(String[][] field,int tate,int yoko,String pieces,String enemyPieces,String empty) {
			   if(tate < 8  && yoko > 1) {
				   String next = field[tate + 1][yoko - 1];
				   if(next.equals(enemyPieces)) {
					   for(int i = 2; true; i++) {
						   if((tate + i > 8 || yoko - i < 1) ||field[tate + i][yoko - i].equals(empty)) {
							   break;
						   }else if(field[tate + i][yoko - i].equals(pieces)) {
							   for(int k = 1; k < i; k++) {
								   field[tate + k][yoko - k] = pieces;
							   }
						   }
						   
					   }
				   }
			   }
		   }
		  //ひだりうえの処理
		   public void leftUp(String[][] field,int tate,int yoko,String pieces,String enemyPieces,String empty) {
			   if(tate > 1  && yoko > 1) {
				   String next = field[tate - 1][yoko - 1];
				   if(next.equals(enemyPieces)) {
					   for(int i = 2; true; i++) {
						   if((tate - i < 1 || yoko - i < 1) ||field[tate - i][yoko - i].equals(empty)) {
							   break;
						   }else if(field[tate - i][yoko - i].equals(pieces)) {
							   for(int k = 1; k < i; k++) {
								   field[tate - k][yoko - k] = pieces;
							   }
						   }
					   }
				    }
			     }
		      }
	       }
