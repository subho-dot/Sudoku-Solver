public class SudokuSolver {

  private static final int gridSize = 9;
  public static void main(String[] args){

    int[][] subox ={
      {1,9,8,0,0,7,0,4,0},
      {0,0,0,0,1,0,3,0,0},
      {3,0,0,0,0,5,0,0,1},
      {8,0,0,0,7,1,0,0,0},
      {0,6,3,0,0,0,1,8,0},
      {0,0,0,9,6,0,0,0,2},
      {6,0,0,2,0,0,0,0,8},
      {0,0,4,0,5,0,0,0,0},
      {0,2,0,7,0,0,4,1,3}
    };
    System.out.println("\nSudoku to be solved:\n");
    displayGame(subox);
    if(mainSolver(subox)){
      System.out.println("\nSolved Successfully\n");
      displayGame(subox);
    }
    else{
      System.out.println("This board is unsolvable");
    }
    
  }
    private static void displayGame(int[][] subox) {
      for(int row=0;row<gridSize;row++){
        if(row%3==0 && row!=0){
          System.out.println("-----------");
        }
        for(int col=0;col<gridSize;col++){
          if(col%3==0 && col!=0){
            System.out.print("|");
          }
          System.out.print(subox[row][col]);
        }
        System.out.println();
      }
  }
    private static boolean ifInRow(int[][] subox,int number,int row){
      for(int i = 0;i<gridSize;i++){
        if(subox[row][i]==number){
          return true;
        }
      }
      return false;
    }

    private static boolean ifInColumn(int[][] subox,int number,int column){
      for(int i = 0;i<gridSize;i++){
        if(subox[i][column]==number){
          return true;
        }
      }
      return false;
    }

    private static boolean ifInBox(int[][] subox,int number,int row, int column){
      int newRow=row-row%3;
      int newColumn=column-column%3;

      for(int i=newRow;i<newRow+3;i++){
        for(int j=newColumn;j<newColumn+3;j++){
          if(subox[i][j]==number){
            return true;
          }
        }
      }
      return false;
    }
    private static boolean isValidPut(int[][] subox,int number,int row,int column){
      return !ifInRow(subox,number,row) && !ifInColumn(subox,number,column) &&!ifInBox(subox,number,row,column);
    }

    private static boolean mainSolver(int[][] subox){
      for(int row=0;row<gridSize;row++){
        for(int col=0;col<gridSize;col++){
          if(subox[row][col]==0){
            for(int trialNumber=1;trialNumber<=gridSize;trialNumber++){
              if(isValidPut(subox,trialNumber,row,col)){
                subox[row][col] = trialNumber;

                if(mainSolver(subox)){
                  return true;
                }
                else{
                  subox[row][col]=0;
                }
              }
            }
            return false;
          }
        }
      }
      return true;
    }

}
