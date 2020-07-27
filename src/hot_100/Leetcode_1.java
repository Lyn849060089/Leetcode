package hot_100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Leetcode_1 {

    public static void main(String[] args) throws Exception {
        Leetcode_1 solution1 = new Leetcode_1();
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        long start = System.currentTimeMillis();
        int[] res = solution1.twoSum(nums, target);
        long end = System.currentTimeMillis() - start;
        System.out.println("用时：" + end + "ms");
        System.out.println(Arrays.toString(res));
    }

    /**
     * 一遍哈希表法
     *
     * 时间复杂度 O(n)
     *  只遍历了一次，表中的每次查找只花费 O(1) 的时间
     * 空间复杂度 O(n)
     *  哈希表最多储存 n 个元素
     *
     * @param nums 已知数组
     * @param target 目标值
     * @return 返回的数组下表
     * @throws Exception 无解
     */
    public int[] twoSum(int[] nums, int target) throws Exception {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int _target = target - nums[i];
            if (map.containsKey(_target)) {
                return new int[] {map.get(_target), i};
            }
            map.put(nums[i], i);
        }
        throw new Exception("Not found");
    }
}
