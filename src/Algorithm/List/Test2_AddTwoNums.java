package Algorithm.List;
import util.ListNode;

public class Test2_AddTwoNums {

	public static void main(String[] args) {
		ListNode temp1 = new ListNode(1);
		temp1.next = new ListNode(2);
		temp1.next.next = new ListNode(3);

		ListNode temp2 = new ListNode(3);
		temp2.next = new ListNode(2);
		temp2.next.next = new ListNode(1);
		
		ListNode res = addTwoNumbers(temp1, temp2);

		System.out.println(res.value);
		System.out.println(res.next.value);
		System.out.println(res.next.next.value);
		

	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode temp1 = l1, temp2 = l2;
		ListNode head = new ListNode(0);
		ListNode current = head;
		int carry = 0;

		while (temp1 != null || temp2 != null) {
			int x = (temp1 == null) ? 0 : temp1.value;
			int y = (temp2 == null) ? 0 : temp2.value;
			int sum = (x + y + carry) % 10;
			carry = (x + y + carry) / 10;
			current.next = new ListNode(sum);
			current = current.next;
			if (temp1 != null) {
				temp1 = temp1.next;
			}
			if (temp2 != null) {
				temp2 = temp2.next;
			}
		}
		if (carry > 0) {
			current.next = new ListNode(carry);
		}
		return head.next;
	}

}