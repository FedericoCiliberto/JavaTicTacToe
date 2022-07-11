package game;

public class Tabella {
	
	int[][] matrice= new int[3][3];
	int turn=1; //parte il giocatore 1
	//player1=1 player2=2 casella vuota=0
	int counter=0; //conto di mosse fatte. Quando arriva a 9, pareggio.
	
	Tabella(){	
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				matrice[i][j]=0;
			}
		}
	}
	
	public void reset() {
	
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				matrice[i][j]=0;				}
		}
		turn=1;
		counter=0;
		
	}
	
	/**
	 * 
	 * true= mossa riuscita
	 * false= errore, mossa da ripetere
	 */
	public boolean draw(int x,int y,int p) {
		if((p==1 || p==2) && x>=0 && x<=2 && y>=0 && y<=2) {
			if(matrice[y][x]==0) {
				matrice[y][x]=p;
				counter++;
				if(turn==1) {
					turn=2;
				}else if(turn==2) {
					turn=1;
				}
				return true;
			}
			
		}
		return false;
	}
	
	public int[][] getMatrice(){
		return matrice;
	}
	
	public int getTurn(){
		return turn;
	}
	
	/*
	 * Questo metodo ritorna 0 se nessuno ha vinto, 1 o 2 se ha vinto giocatore 1 o giocatore 2.
	 * Inoltre evidenzia la zona della vittoria sostituendo 9 alle caselle in cui il giocatore vincente
	 * ha vinto.
	 * Tanto quale giocatore abbia vinto è deducibile dal valore ritornato
	 */
	public int checkWin() {
		int i,j,counter1=0,counter2=0;
		
		for(i=0;i<3;i++) {
			for(j=0;j<3;j++) {
				if(matrice[i][j]==1) {
					counter1++;
				}else if(matrice[i][j]==2) {
					counter2++;
				}
			}
			
			if(counter1==3) {
				for(j=0;j<3;j++) {
					matrice[i][j]=9;  // 9=vittoria!
				}
				return 1;
			}
			
			if(counter2==3) {
				for(j=0;j<3;j++) {
					matrice[i][j]=9;  // 9=vittoria!
				}
				return 2;
			}
			counter1=0;counter2=0;
		}
		
		for(j=0;j<3;j++) {
			for(i=0;i<3;i++) {
				if(matrice[i][j]==1) {
					counter1++;
				}else if(matrice[i][j]==2) {
					counter2++;
				}
			}
			
			if(counter1==3) {
				for(i=0;i<3;i++) {
					matrice[i][j]=9;  // 9=vittoria!
				}
				return 1;
			}
			
			if(counter2==3) {
				for(i=0;i<3;i++) {
					matrice[i][j]=9;  // 9=vittoria!
				}
				return 2;
			}
			counter1=0;counter2=0;
		}
		
		if(matrice[0][0]== matrice[1][1] && matrice[1][1]==matrice[2][2] && matrice[0][0]!=0) {
			int k=matrice[0][0];
			matrice[0][0]=9;
			matrice[1][1]=9;
			matrice[2][2]=9;
			return k;
		}
		
		if(matrice[0][2]==matrice[1][1] && matrice[1][1]==matrice[2][0] && matrice[0][2]!=0) {
			int k=matrice[0][2];
			matrice[0][2]=9;
			matrice[1][1]=9;
			matrice[2][0]=9;
			return k;
		}
		return 0;
		
		
	}
	
	public int getValue(int x,int y) {
		return matrice[y][x];
	}
	
	public int getCounter() {
		return counter;
	}

}














