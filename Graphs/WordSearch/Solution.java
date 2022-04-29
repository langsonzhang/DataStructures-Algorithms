// Given an m x n grid of characters board and a string word, return true if word exists in the grid.

// The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

// Solved using DFS with backtracking.
class Solution {
    public boolean exist(char[][] board, String word) {
        char[] wordArray = word.toCharArray();
        // Check every tile for first character
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // If found run DFS
                if (board[i][j] == word.charAt(0) && dfs(board, wordArray, i, j, 0))
                    return true;
            }
        }
        return false;
    }
    
    public boolean dfs(char[][] board, char[] word, int row, int col, int index) {
        // check 1&2. col, row in range 3. correct letter 4. not discovered
        if (!inRange(row, 0, board.length-1) || !inRange(col, 0, board[0].length-1)
           || board[row][col] != word[index] || board[row][col] == '$')
            return false;
        // Match so return
        if (index >= word.length-1) 
            return true;
        // DFS visit + backtracking
        char temp = board[row][col];
        board[row][col] = '$';
        boolean ret =  dfs(board, word, row+1, col, index+1) ||
             dfs(board, word, row-1, col, index+1) ||
             dfs(board, word, row, col+1, index+1) ||
            dfs(board, word, row, col-1, index+1);
        board[row][col] = temp;
        return ret;
    }
    
    public boolean inRange(int n, int lo, int hi) {
        return n >= lo && n <= hi;
    }
}