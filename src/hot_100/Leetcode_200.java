package hot_100;

import utils.UnionFind;

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 *  
 *
 * 示例 1:
 *
 * 输入:
 * [
 * ['1','1','1','1','0'],
 * ['1','1','0','1','0'],
 * ['1','1','0','0','0'],
 * ['0','0','0','0','0']
 * ]
 * 输出: 1
 * 示例 2:
 *
 * 输入:
 * [
 * ['1','1','0','0','0'],
 * ['1','1','0','0','0'],
 * ['0','0','1','0','0'],
 * ['0','0','0','1','1']
 * ]
 * 输出: 3
 * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Leetcode_200 {

    public static void main(String[] args) {
        char[][] grid =  {
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}
        };
        Solution s = new Solution();
        System.out.println(s.numIslands(grid));
    }
}

class Solution {

    public int numIslands(char[][] grid) {
        if (grid.length == 0) return 0;

        UnionFind uf = new UnionFind(grid.length * grid[0].length);
        int index = 0;
        int zero = 0;
        for (char[] chars : grid) {
            for (char c : chars) {
                if (c == '0') {
                    zero++;
                    index++;
                    continue;
                }
                Integer right = getRight(grid, index);
                Integer bottom = getBottom(grid, index);

                if (right != null) {
                    if (getChar(grid, right) == '1') {
                        uf.merge(index, right);
                    }
                }
                if (bottom != null) {
                    if (getChar(grid, bottom) == '1') {
                        uf.merge(index, bottom);
                    }
                }
                index++;
            }
        }
        return uf.getCount() - zero;
    }

    private Integer getRight(char[][] grid, int index) {
        int x = index % grid[0].length;
        int y = index / grid[0].length;
        if (x < grid[0].length - 1) {
            return y * grid[0].length + x + 1;
        }
        return null;
    }

    private Integer getBottom(char[][] grid, int index) {
        int x = index % grid[0].length;
        int y = index / grid[0].length;
        if (y < grid.length - 1) {
            return (y + 1) * grid[0].length + x;
        }
        return null;
    }

    private Character getChar(char[][] grid, int index) {
        int x = index % grid[0].length;
        int y = index / grid[0].length;
        return grid[y][x];
    }
}
