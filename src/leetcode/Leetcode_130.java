package leetcode;

import utils.UnionFind;

import java.util.Arrays;

/**
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 *
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 * 示例:
 *
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:
 *
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/surrounded-regions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Leetcode_130 {

    public static void main(String[] args) {
        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'},
        };

        Leetcode_130 solution = new Leetcode_130();
        solution.solve(board);
        System.out.println(Arrays.deepToString(board));
    }

    // Solution
    public void solve(char[][] board) {
        if (board.length == 0) return;

        int dummy = board.length * board[0].length;
        UnionFind uf = new UnionFind(dummy + 1);

        int index = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // 先将四周的'O'与虚拟节点连接
                if (i == 0 || i == board.length - 1 || j == 0 || j == board[i].length - 1) {
                    uf.merge(index, dummy);
                } else {
                    int top = getTop(board, index);
                    int bottom = getBottom(board, index);
                    int left = getLeft(board, index);
                    int right = getRight(board, index);

                    if (board[i][j] == 'O') {
                        if (getChar(board, top) == 'O') {
                            uf.merge(index, top);
                        }
                        if (getChar(board, bottom) == 'O') {
                            uf.merge(index, bottom);
                        }
                        if (getChar(board, left) == 'O') {
                            uf.merge(index, left);
                        }
                        if (getChar(board, right) == 'O') {
                            uf.merge(index, right);
                        }
                    }
                }
                index++;
            }
        }
        index = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'O') {
                    if (!uf.isConnected(index, dummy)) {
                        board[i][j] = 'X';
                    }
                }
                index++;
            }
        }
    }

    private int getLeft(char[][] grid, int index) {
        int x = index % grid[0].length;
        int y = index / grid[0].length;
        return y * grid[0].length + x - 1;
    }

    private int getRight(char[][] grid, int index) {
        int x = index % grid[0].length;
        int y = index / grid[0].length;
        return y * grid[0].length + x + 1;
    }

    private int getTop(char[][] grid, int index) {
        int x = index % grid[0].length;
        int y = index / grid[0].length;
        return (y - 1) * grid[0].length + x;
    }

    private int getBottom(char[][] grid, int index) {
        int x = index % grid[0].length;
        int y = index / grid[0].length;
        return (y + 1) * grid[0].length + x;
    }

    private Character getChar(char[][] grid, int index) {
        int x = index % grid[0].length;
        int y = index / grid[0].length;
        return grid[y][x];
    }
}
