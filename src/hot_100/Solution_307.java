package hot_100;

import java.util.Arrays;

/**
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 *
 * update(i, val) 函数可以通过将下标为 i 的数值更新为 val，从而对数列进行修改。
 *
 * 示例:
 *
 * Given nums = [1, 3, 5]
 *
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * 说明:
 *
 * 数组仅可以在 update 函数下进行修改。
 * 你可以假设 update 函数与 sumRange 函数的调用次数是均匀分布的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/range-sum-query-mutable
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_307 {

    public static void main(String[] args) {
        int[] arr = {0, -8};
        NumArray numArray = new NumArray(arr);
        numArray.update(0, 3);
        System.out.println(numArray.sumRange(1, 1));
    }
}

class NumArray {

    private final int[] data;
    private final int[] tree;

    public NumArray(int[] nums) {
        data = new int[nums.length];
        System.arraycopy(nums, 0, data, 0, nums.length);
        tree = new int[4 * nums.length];
        if (nums.length > 0) {
            buildSegmentTree(0, 0, data.length - 1);
        }
        System.out.println(Arrays.toString(tree));
    }

    public void update(int i, int val) {
        data[i] = val;
        set(0, 0, data.length - 1, i, val);
        System.out.println(Arrays.toString(tree));
    }

    public int sumRange(int i, int j) {
        return query(0, 0, data.length - 1, i, j);
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int mid = l + (r - l) / 2;

        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        tree[treeIndex] = tree[leftTreeIndex] + tree[rightTreeIndex];
    }

    private int query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int mid = l + (r - l) / 2;

        if (queryL >= mid + 1) {
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }

        int leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        int rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return leftResult + rightResult;
    }

    private void set(int treeIndex, int l, int r, int index, int val) {
        if (l == r) {
            tree[treeIndex] = val;
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int mid = l + (r - l) / 2;

        if (index >= mid + 1) {
            set(rightTreeIndex, mid + 1, r, index, val);
        } else {
            set(leftTreeIndex, l, mid, index, val);
        }

        tree[treeIndex] = tree[leftTreeIndex] + tree[rightTreeIndex];
    }
}
