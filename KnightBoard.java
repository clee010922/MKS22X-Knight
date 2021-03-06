public class KnightBoard {

  private int[][] board;
  private int[][] moves = {{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};
  private int[][] opt;

  //@throws IllegalArgumentException when either parameter is negative.
  public KnightBoard(int startingRows, int startingCols) {
    if (startingRows < 0 || startingCols < 0) {
      throw new IllegalArgumentException();
    }
    board = new int[startingRows][startingCols];
    clear();
    opt = new int[startingRows][startingCols];
    initialize();
  }

  private void initialize() {
    for (int r = 0; r < opt.length; r++) {
      for (int c = 0; c < opt[0].length; c++) {
        for (int i = 0; i < moves.length; i++) {
          if (!(r+moves[i][0] < 0 || r+moves[i][0] >= opt.length || c+moves[i][1] < 0 || c+moves[i][1] >= opt[0].length))
            opt[r][c]++;
        }
      }
    }
  }

  /*
  public int[][] sortMoves(int r, int c) {
    int[][] allMoves = new int[opt[r][c]][2];
    int index = 0;
    for (int i = 0; i < moves.length; i++) {
      if (r+moves[i][0] >= 0 && r+moves[i][0] < opt.length && c+moves[i][1] >= 0 && c+moves[i][1] < opt[0].length) {
        allMoves[index] = moves[i];
        index++;
      }
    }
    for (int i = 1; i < allMoves.length; i++) {
      int[] temp = allMoves[i];
      while (i > 0 && opt[r+allMoves[i-1][0]][c+allMoves[i-1][1]] > opt[r+temp[0]][c+temp[1]]) {
        allMoves[i] = allMoves[i-1];
        i--;
      }
      allMoves[i] = temp;
    }
    return allMoves;
  }

  public boolean addKnight(int r, int c, int moveNumber) {
    if (r < 0 || r >= board.length || c < 0 || c >= board[0].length)
      return false;
    if (board[r][c] != 0)
      return false;
    board[r][c] = moveNumber;
    for (int i = 0; i < moves.length; i++) {
      if (!(r+moves[i][0] < 0 || r+moves[i][0] >= opt.length || c+moves[i][1] < 0 || c+moves[i][1] >= opt[0].length))
        opt[r+moves[i][0]][c+moves[i][1]]--;
    }
    return true;
  }

  public boolean removeKnight(int r, int c) {
    if (r < 0 || r > board.length || c < 0 || c > board[0].length)
      return false;
    if (board[r][c] == 0)
      return false;
    board[r][c] = 0;
    for (int i = 0; i < moves.length; i++) {
      if (!(r+moves[i][0] < 0 || r+moves[i][0] >= opt.length || c+moves[i][1] < 0 || c+moves[i][1] >= opt[0].length))
        opt[r+moves[i][0]][c+moves[i][1]]++;
    }
    return true;
  }

  public boolean solve(int startingRow, int startingCol) {
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[0].length; c++) {
        if (board[r][c] != 0)
          throw new IllegalStateException();
      }
    }
    if (startingRow < 0 || startingCol < 0 || startingRow >= board.length || startingCol >= board[0].length)
      throw new IllegalArgumentException();
    return solveH(startingRow, startingCol, 1);
  }

  //optimized version of solveH.
  private boolean solveH(int r, int c, int moveNumber) {
    if (moveNumber == board.length * board[0].length + 1)
      return true;
    if (addKnight(r, c, moveNumber)) {
      int[][] outgoingMoves = sortMoves(r, c);
      for (int i = 0; i < outgoingMoves.length; i++) {
        if (solveH(r+outgoingMoves[i][0], c+outgoingMoves[i][1], moveNumber+1))
          return true;
      }
      removeKnight(r, c);
    }
    return false;
  }
  */

  public void clear() {
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[0].length; c++) {
        board[r][c] = 0;
      }
    }
  }

  /*
  blank boards display 0's as underscores
  you get a blank board if you never called solve or
  when there is no solution
  */
  public String toString() {
    String result = "";
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[0].length; c++) {
        if (board[r][c] < 10) {
          result += " " + board[r][c] + " ";
        }
        else result += board[r][c] + " ";
      }
      result += "\n";
    }
    return result;
  }

  public String optToString() {
    String result = "";
    for (int r = 0; r < opt.length; r++) {
      for (int c = 0; c < opt[0].length; c++) {
        if (board[r][c] < 10) {
          result += " " + opt[r][c] + " ";
        }
        else result += opt[r][c] + " ";
      }
      result += "\n";
    }
    return result;
  }

  /*
  Modifies the board by labeling the moves from 1 (at startingRow,startingCol)
  up to the area of the board in proper knight move steps.
  @throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative
  or out of bounds.
  @returns true when the board is solvable from the specified starting position
  */


  public boolean solve(int startingRow, int startingCol) {
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[0].length; c++) {
        if (board[r][c] != 0)
          throw new IllegalStateException();
      }
    }
    if (startingRow < 0 || startingCol < 0 || startingRow >= board.length || startingCol >= board[0].length)
      throw new IllegalArgumentException();
    addKnight(startingRow, startingCol, 1);
    return solveH(startingRow, startingCol, 2);
  }


  /*
  @throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative
  or out of bounds.
  @returns the number of solutions from the starting position specified
  */
  public int countSolutions(int startingRow, int startingCol) {
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[0].length; c++) {
        if (board[r][c] != 0)
          throw new IllegalStateException();
      }
    }
    if (startingRow < 0 || startingCol < 0 || startingRow >= board.length || startingCol >= board[0].length)
      throw new IllegalArgumentException();
    addKnight(startingRow, startingCol, 1);
    return countSolutionsH(startingRow, startingCol, 2);
  }

  private int countSolutionsH(int row, int col, int moveNumber) {
    int numSolutions = 0;
    if (moveNumber == board.length * board[0].length + 1)
      return 1;
    for (int i = 0; i < moves.length; i++) {
      if (addKnight(row+moves[i][0], col+moves[i][1], moveNumber)) {
        numSolutions += countSolutionsH(row+moves[i][0], col+moves[i][1], moveNumber+1);
        removeKnight(row+moves[i][0], col+moves[i][1]);
      }
    }
    return numSolutions;
  }


  private boolean solveH(int row, int col, int moveNumber) {
    if (moveNumber == board.length * board[0].length + 1)
      return true;
    for (int i = 0; i < moves.length; i++) {
      if (addKnight(row+moves[i][0], col+moves[i][1], moveNumber)) {
        if (solveH(row+moves[i][0], col+moves[i][1], moveNumber+1)) {
          return true;
        }
        else removeKnight(row+moves[i][0], col+moves[i][1]);
      }
    }
    return false;
  }



  public boolean addKnight(int row, int col, int moveNumber) {
    if (row < 0 || row >= board.length || col < 0 || col >= board[0].length)
      return false;
    if (board[row][col] != 0)
      return false;
    board[row][col] = moveNumber;
    return true;
  }

  public boolean removeKnight(int row, int col) {
    if (row < 0 || row > board.length || col < 0 || col > board[0].length)
      return false;
    if (board[row][col] == 0)
      return false;
    board[row][col] = 0;
    return true;
  }

  //testcase must be a valid index of your input/output array
  public static void runTest(int i){

    KnightBoard b;
    int[]m =   {4,5,5,5,5};
    int[]n =   {4,5,4,5,5};
    int[]startx = {0,0,0,1,2};
    int[]starty = {0,0,0,1,2};
    int[]answers = {0,304,32,56,64};
    if(i >= 0 ){
      try{
        int correct = answers[i];
        b = new KnightBoard(m[i%m.length],n[i%m.length]);
        int ans  = b.countSolutions(startx[i],starty[i]);
        if(correct==ans){
          System.out.println("PASS board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans);
        }else{
          System.out.println("FAIL board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans+" vs "+correct);
        }
      }catch(Exception e){
        System.out.println("FAIL Exception case: "+i);
      }
    }
  }


  public static void main(String[] args) {
    /*
    KnightBoard knight = new KnightBoard(5, 5);
    //System.out.println(knight.solve(0, 0));
    //System.out.println(knight);
    System.out.println(knight.optToString());
    System.out.println(knight.countSolutions(0, 0));
    knight.clear();
    System.out.println(knight.solve(0, 0));
    System.out.println(knight);
    */
    runTest(0);
    runTest(1);
    runTest(2);
    runTest(3);
    runTest(4);
  }






}
