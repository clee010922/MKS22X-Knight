public class KnightBoard {

  private int[][] board;

  //@throws IllegalArgumentException when either parameter is negative.
  public KnightBoard(int startingRows, int startingCols) {
    if (startingRows < 0 || startingCols < 0) {
      throw new IllegalArgumentException();
    }
    board = new int[startingRows][startingCols];
    clear();
  }

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
    return solveH(startingRow, startingCol, 1);
  }

  /*
  @throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative
  or out of bounds.
  @returns the number of solutions from the starting position specified
  */
  //public int countSolutions(int startingRow, int startingCol) {

  //}

  private boolean solveH(int row, int col, int moveNumber) {
    board[row][col] = moveNumber;
    int[] moves = {1, 2, 1, -2, -1, 2, -1, -2, 2, 1, 2, -1, -2, 1, -2, -1};
    
    /*
    if (row-2 >= 0 && col+1 < board[0].length)
      return solveH(row-2, col+1, moveNumber+1);
    if (row-1 >= 0 && col+2 < board[0].length)
      return solveH(row-1, col+2, moveNumber+1);
    if (row+1 < board.length && col+2 < board[0].length)
      return solveH(row+1, col+2, moveNumber+1);
    if (row+2 < board.length && col+1 < board[0].length)
      return solveH(row+2, col+2, moveNumber+1);
    if (row+2 < board.length && col-1 >= 0)
      return solveH(row+2, col-1, moveNumber+1);
    if (row+1 < board.length && col-2 >= 0)
      return solveH(row+1, col-2, moveNumber+1);
    if (row-1 >= 0 && col-2 >= 0)
      return solveH(row-1, col-2, moveNumber+1);
    if (row-2 >= 0 && col-1 >= 0)
      return solveH(row-2, col-1, moveNumber+1);
      */
  }

  /*
  public static void main(String[] args) {
    KnightBoard knight = new KnightBoard(3, 3);
    System.out.println(knight);
  }
  */


}
