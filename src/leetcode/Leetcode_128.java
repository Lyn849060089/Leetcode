package leetcode;

import utils.UnionFind;

import java.util.HashMap;

/**
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 *
 * 要求算法的时间复杂度为 O(n)。
 *
 * 示例:
 *
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Leetcode_128 {

    public static void main(String[] args) {

    }

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        UnionFind uf = new UnionFind(nums.length);

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i] - 1)) {
                uf.merge(i, map.get(nums[i] - 1));
            }
        }
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int temp = uf.getCount(i);
            if (temp > max) {
                max = temp;
            }
        }
        return max;
    }
}
