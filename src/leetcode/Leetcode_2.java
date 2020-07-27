package leetcode;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Leetcode_2 {

    /**
     * 时间复杂度 O(max(m,n))
     *  假设 m 和 n 分别表示 l1 和 l2 的长度，上面的算法最多重复 max(m, n) 次。
     * 空间复杂度 O(max(m,n))
     *  新列表的长度最多为 max(m,n) + 1。
     *
     * @param l1 第一个链表
     * @param l2 第二个链表
     * @return 保存两个链表和的链表
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummyhead = new ListNode(0); // 将当前结点初始化为返回列表的哑结点。
        int carry = 0; // 将进位初始化为0。
        ListNode p = l1, q = l2, curr = dummyhead; // 将p和q分别初始化为列表l1和l2的头部。
        // 遍历列表l1和l2直至到达它们的尾端。
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0; // 将x设为结点p的值。如果p已经到达l1的末尾，则将其值设置为0。
            int y = (q != null) ? q.val : 0; // 将y设为结点q的值。如果q已经到达l2的末尾，则将其值设置为0。
            int sum = x + y + carry;
            carry = sum / 10; // 更新进位
            curr.next = new ListNode(sum % 10); // 创建一个新节点保存当前和的个位
            curr = curr.next; // 前进到下一个节点
            // 移动p和q到下一个节点
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyhead.next;
    }
}
