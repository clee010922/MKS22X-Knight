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
  @throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative
  or out of bounds.
  */
  public boolean solve(int startingRow, int startingCol) {
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[0].length; c++) {
        if (board[r][c] != 0)
          throw new IllegalStateException();
      }
    }
    if (startingRow < 0 || startingCol < 0)
      throw new IllegalArgumentException();
  }

  /*
  @throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative
  or out of bounds.
  */
  public int countSolutions(int startingRow, int startingCol) {

  }

  private boolean solveH(int row, int col, int level) {
    //level is the # of the knight
  }

  /*
  public static void main(String[] args) {
    KnightBoard knight = new KnightBoard(3, 3);
    System.out.println(knight);
  }
  */


}
