class Solution {
    public boolean exist(char[][] board, String word) {
        char[] wordArray = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0) && dfs(board, wordArray, i, j, 0))
                    return true;
            }
        }
        return false;
    }
    
    public boolean dfs(char[][] board, char[] word, int row, int col, int index) {
        if (!inRange(row, 0, board.length-1) || !inRange(col, 0, board[0].length-1)
           || board[row][col] != word[index] || board[row][col] == '$')
            return false;
        if (index >= word.length-1) 
            return true;
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