// Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.

// A region is captured by flipping all 'O's into 'X's in that surrounded region.
    
// Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
// Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
// Explanation: Surrounded regions should not be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.


// Solution: dfs along the border and mark all '0's. Change marked 'O's to 'X's.
class Solution {
    
    public void solve(char[][] board) {
        
        // do top and bottom rows
        for (int x = 0; x < board[0].length; x++) {
            if (board[0][x] == 'O') {
                dfs(board, x, 0);                
            }
            if (board[board.length-1][x] == 'O') {
                dfs(board, x, board.length-1);                
            }
        }
        
        // do left and right columns
        for (int y = 0; y < board.length; y++) {
            if (board[y][0] == 'O') {
                dfs(board, 0, y);
            }
            if (board[y][board[0].length-1] == 'O') {
                dfs(board, board[0].length-1, y);
            }
        }
        
        // produce the final board;
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                if (board[y][x] == 'O') {
                    board[y][x] = 'X';
                }
                if (board[y][x] == '!') {
                    board[y][x] = 'O';
                }
            }
        }
    }
    
    public void dfs(char[][] board, int x, int y) {
        board[y][x] = '!';
        
        int dx = 1;
        int dy = 0;
        
        for (int i = 0; i < 4; i++) {
            if (inRange(x+dx, 0, board[0].length-1) && inRange(y+dy, 0, board.length-1)) {
                char neighbour = board[y+dy][x+dx];
                if (neighbour == 'O') {
                    dfs(board, x+dx, y+dy);
                }
            }
            
            // 90º rotation
            int temp = dx;
            dx = -dy;
            dy = temp;
        }
    }
    
     public boolean inRange(int a, int lo, int hi) {
        return a >= lo && a <= hi;
    }
    
}