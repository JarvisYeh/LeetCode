package Algorithm.DataStructure;

import java.util.*;


public class Test716_MaxStack {
	// the tail(top) node of doubly linked list
	ListNode tail;
	// tree map stores all values and its corresponds ListNode
	// one value might have several list nodes, so the value of TreeMap is a list of ListNode
	TreeMap<Integer, List<ListNode>> valMap;
	int size;

	/** initialize your data structure here. */
	public Test716_MaxStack() {
		tail = null;
		valMap = new TreeMap<>();
		size = 0;
	}

	// O(log n) add into treeMap is log(n)
	public void push(int x) {
		ListNode newNode = new ListNode(x);
		// add node into TreeMap<val, list of nodes>
		List<ListNode> nodeList = valMap.getOrDefault(x, new ArrayList<>());
		nodeList.add(newNode);
		valMap.put(x, nodeList);
		// modify doubly linked list, add to head
		if (size == 0) {
			tail = newNode;
			size++;
			return;
		}
		tail.next = newNode;
		newNode.prev = tail;
		tail = newNode;
		size++;
	}

	// O(log n) remove from treeMap is log(n)
	public int pop() {
		if (size == 0) return -1;

		// pop the tail node
		ListNode popped = tail;
		if (popped.prev != null) {
			tail = popped.prev;
			tail.next = null;
			popped.prev = null;
		} else {
			tail = null;
		}

		// remove popped node from treemap
		// since it is the last one added to stack
		// it MUST be the last one in list (treeMap value list)
		List<ListNode> nodeList = valMap.get(popped.val);
		nodeList.remove(nodeList.size() - 1);
		if (nodeList.size() == 0) valMap.remove(popped.val);
		size--;
		return popped.val;
	}

	// O(1)
	public int top() {
		if (tail == null) return -1;
		return tail.val;
	}

	// O(log n), find max key in tree map is O(log n)
	public int peekMax() {
		if (size == 0) return -1;
		return valMap.lastKey();
	}

	// O(log n)
	public int popMax() {
		List<ListNode> nodeList = valMap.lastEntry().getValue();
		// the last node is most topped node among all nodes with same max values
		ListNode maxNode = nodeList.get(nodeList.size() - 1);

		// if the max node is tail, directly use pop()
		if (maxNode == tail) {
			return pop();
		}

		// remove node from tree map
		nodeList.remove(maxNode);
		if (nodeList.size() == 0) valMap.remove(maxNode.val);

		// remove node from doubly linked list
		if (maxNode.prev != null) {
			maxNode.prev.next = maxNode.next;
		}
		if (maxNode.next != null) {
			maxNode.next.prev = maxNode.prev;
		}
		maxNode.prev = null;
		maxNode.next = null;
		size--;
		return maxNode.val;
	}

	static class ListNode {
		int val;
		ListNode prev;
		ListNode next;
		public ListNode(int val) {
			this.val = val;
		}
	}

	public static void main(String[] args) {
		Test716_MaxStack s = new Test716_MaxStack();
		s.push(5);
//		s.push(1);
//		s.push(5);
//		System.out.println(s.top());
//		System.out.println(s.popMax());
//		System.out.println(s.top());
		System.out.println(s.peekMax());
		System.out.println(s.popMax());
//		System.out.println(s.pop());
//		System.out.println(s.top());
	}
}
