package companies;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Amazon {
	static class ListNode {

		ListNode next;
		int val;

		ListNode(int val) {
			this.val = val;
			next = null;
		}
	}

	static class Point implements Comparable<Point> {

		public double getDistance() {
			return distance;
		}

		public int compareTo(Point other) {
			return distance <= other.distance ? 0 : 1;
		}

		public String toString() {
			return (new StringBuilder("[x,y] : ")).append(x).append(",").append(y).toString();
		}

		private double x;
		private double y;
		private double distance;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
			distance = Math.pow(x, 2D) + Math.pow(y, 2D);
		}
	}

	static class TNode {

		TNode left;
		TNode right;
		int val;

		TNode(int val) {
			this.val = val;
			left = right = null;
		}
	}

	public Amazon() {
	}

	public static void main(String args[]) {
		findPathInMatrix();
	}

	static void findMaxElement() {
		int a[] = { 9, 6, 11, 8, 10, 5, 4, 13, 93, 14 };
		System.out.println(Arrays.toString(findMaxElement(a, 4).toArray()));
	}

	static ArrayList findMaxElement(int in[], int k) {
		ArrayList res = new ArrayList();
		if (in.length < k) {
			int max = -1;
			int ai[];
			int l = (ai = in).length;
			for (int j = 0; j < l; j++) {
				int e = ai[j];
				max = Math.max(max, e);
			}

			res.add(Integer.valueOf(max));
			return res;
		}
		Deque dq = new ArrayDeque();
		for (int ctr = 0; ctr < k; ctr++) {
			while (!dq.isEmpty() && in[((Integer) dq.peekLast()).intValue()] < in[ctr])
				dq.pollLast();
			System.out.println((new StringBuilder("Adding ")).append(ctr).toString());
			dq.addLast(Integer.valueOf(ctr));
		}

		System.out.println(dq);
		for (int i = k - 1; i < in.length; i++) {
			System.out.println((new StringBuilder("i ")).append(i).append(" i-k ").append(i - k).toString());
			for (; !dq.isEmpty() && ((Integer) dq.peekFirst()).intValue() < (i - k) + 1; dq.pollFirst())
				;
			for (; !dq.isEmpty() && in[((Integer) dq.peekLast()).intValue()] < in[i]; dq.pollLast())
				;
			dq.addLast(Integer.valueOf(i));
			res.add(Integer.valueOf(in[((Integer) dq.peekFirst()).intValue()]));
		}

		return res;
	}

	static void mergeLL() {
		LinkedList l1 = new LinkedList();
		LinkedList l2 = new LinkedList();
		l1.add(Integer.valueOf(2));
		l1.add(Integer.valueOf(4));
		l1.add(Integer.valueOf(6));
		l1.add(Integer.valueOf(8));
		l1.add(Integer.valueOf(10));
		l2.add(Integer.valueOf(1));
		l2.add(Integer.valueOf(3));
		l2.add(Integer.valueOf(5));
		l2.add(Integer.valueOf(7));
		l2.add(Integer.valueOf(9));
		LinkedList res = mergeLL(l1, l2);
		System.out.println(Arrays.toString(res.toArray()));
	}

	static LinkedList mergeLL(LinkedList l1, LinkedList l2) {
		LinkedList res = new LinkedList();
		while (l1.size() > 0 && l2.size() > 0)
			if (((Integer) l1.peek()).intValue() < ((Integer) l2.peek()).intValue())
				res.addLast((Integer) l1.remove());
			else
				res.addLast((Integer) l2.remove());
		if (l1.size() > 0)
			res.addAll(l1);
		if (l2.size() > 0)
			res.addAll(l2);
		return res;
	}

	private static void doOverLap() {
		System.out
				.println(doOverLap(new Point(-3D, 0.0D), new Point(3D, 4D), new Point(0.0D, -2D), new Point(9D, -1D)));
	}

	private static boolean doOverLap(Point l1, Point r1, Point l2, Point r2) {
		if (r1.x < l2.x || l1.x > r2.x)
			return false;
		return r1.y >= l2.y && l1.y <= r2.y;
	}

	private static void kClosest() {
		Collection points = new ArrayList();
		Point origin = new Point(0.0D, 0.0D);
		points.add(new Point(1.0D, 1.0D));
		points.add(new Point(1.0D, 3D));
		points.add(new Point(-1D, 1.0D));
		points.add(new Point(-1D, 3D));
		points.add(new Point(1.0D, -1D));
		points.add(new Point(3D, -1D));
		points.add(new Point(-1D, -1D));
		points.add(new Point(-1D, 3D));
		points.add(new Point(2D, 2D));
		Collection closestPoints = kClosest(points, 5);
		System.out.print(closestPoints);
	}

	private static Collection kClosest(Collection points, int k) {
		PriorityQueue pq = new PriorityQueue(k, Collections.reverseOrder());
		for (Iterator iterator = points.iterator(); iterator.hasNext();) {
			Point p = (Point) iterator.next();
			if (pq.size() < k)
				pq.offer(p);
			else if (((Point) pq.peek()).distance > p.distance) {
				pq.poll();
				pq.offer(p);
			}
		}

		return pq;
	}

	static void findPathInMatrix() {
		int matrix[][] = { { 1, 1, 9 } };
		int rows = matrix.length;
		int cols = matrix[0].length;
		boolean mat[][] = new boolean[rows][cols];
		boolean b = findPathInMatrix(matrix, mat, 0, 0, rows, cols);
		System.out.println((new StringBuilder("Found ")).append(b).toString());
	}

	static boolean findPathInMatrix(int matrix[][], boolean mat[][], int r, int c, int rows, int cols) {
		if (r < 0 || c < 0 || r >= rows || c >= cols || matrix[r][c] == 0 || mat[r][c])
			return false;
		mat[r][c] = true;
		if (matrix[r][c] == 9)
			return true;
		boolean found = false;
		int ai[][];
		int j = (ai = dirs).length;
		for (int i = 0; i < j; i++) {
			int dir[] = ai[i];
			found = findPathInMatrix(matrix, mat, r + dir[0], c + dir[1], rows, cols);
			if (found)
				return found;
		}
		return found;
	}

	static void sortedInsert() {
		ListNode head = new ListNode(5);
		head.next = new ListNode(1);
		head.next.next = new ListNode(4);
		head.next.next.next = new ListNode(8);
		head.next.next.next.next = new ListNode(14);
		ListNode res = sortedInsert(null, new ListNode(5));
		sortedInsert(res, new ListNode(5));
		for (; res != null; res = res.next)
			System.out.print((new StringBuilder(String.valueOf(res.val))).append("->").toString());

	}

	public static ListNode sortedInsert(ListNode head, ListNode inNode) {
		ListNode current = head;
		if (inNode == null)
			return null;
		if (head == null) {
			inNode.next = inNode;
			return inNode;
		}
		for (; current.next != head; current = current.next)
			if (current.val < inNode.val && inNode.val <= current.next.val) {
				ListNode temp = current.next;
				current.next = inNode;
				current.next.next = temp;
				return head;
			}

		if (current.val < inNode.val) {
			current.next = inNode;
			current.next.next = head;
			return head;
		}
		if (inNode.val <= head.val) {
			current.next = inNode;
			current.next.next = head;
			head = inNode;
			return head;
		} else {
			return null;
		}
	}

	public static List spiralOrder() {
		int matrix[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		List res = new ArrayList();
		int rows = matrix.length;
		if (rows == 0)
			return res;
		int cols = matrix[0].length;
		if (cols == 0)
			return res;
		int r = 0;
		int c = 0;
		int elems = rows * cols;
		int dir = 0;
		int minR = 1;
		int maxR = rows - 1;
		int minC = 0;
		int maxC = cols - 1;
		for (; elems > 0; elems--) {
			res.add(Integer.valueOf(matrix[r][c]));
			if (dir == 0) {
				if (++c > maxC) {
					dir = 1;
					r++;
					c--;
					maxC--;
				}
			} else if (dir == 1) {
				if (++r > maxR) {
					dir = 2;
					r--;
					c--;
					maxR--;
				}
			} else if (dir == 2) {
				if (--c < minC) {
					c++;
					dir = 3;
					r--;
					minC++;
				}
			} else if (dir == 3 && --r < minR) {
				r++;
				dir = 0;
				c++;
				minR++;
			}
		}

		int e;
		for (Iterator iterator = res.iterator(); iterator.hasNext(); System.out.print(e))
			e = ((Integer) iterator.next()).intValue();

		return res;
	}

	static void isSubTree() {
		TNode root = new TNode(20);
		root.left = new TNode(1);
		root.right = new TNode(2);
		root.left.left = new TNode(8);
		root.left.right = new TNode(4);
		root.right.left = new TNode(5);
		root.right.right = new TNode(6);
		System.out.println(isSubTreeN(root, new TNode(22)));
	}

	static boolean isSubTreeN(TNode root, TNode cur) {
		StringBuilder rootPre = new StringBuilder("");
		preorder(root, rootPre);
		StringBuilder rootIn = new StringBuilder("");
		preorder(root, rootIn);
		StringBuilder curPre = new StringBuilder("");
		preorder(cur, curPre);
		StringBuilder curIn = new StringBuilder("");
		preorder(cur, curIn);
		return rootPre.indexOf(curPre.toString()) > 0 && rootIn.indexOf(curIn.toString()) > 0;
	}

	static void inOrder(TNode node, StringBuilder sb) {
		if (node == null) {
			sb.append("#");
			return;
		} else {
			inOrder(node.left, sb);
			sb.append(node.val);
			inOrder(node.right, sb);
			return;
		}
	}
	static void preorder(TNode root, StringBuilder sb) {

		if (root == null) {
			sb.append("#");
			return;
		} else {
			sb.append(root.val);
			preorder(root.left, sb);
			preorder(root.right, sb);
			return;
		}
	}

	static boolean checkAllNodes(TNode root, TNode cur) {
		if (root == null && cur == null)
			return true;
		if (root == null || cur == null)
			return false;
		boolean r = isSubTree(root, cur);
		if (r)
			return r;
		r = isSubTree(root.right, cur);
		if (r)
			return r;
		r = isSubTree(root.left, cur);
		if (r)
			return r;
		else
			return false;
	}

	static boolean isSubTree(TNode root, TNode cur) {
		if (root == null && cur == null)
			return true;
		if (root == null || cur == null)
			return false;
		boolean is = false;
		if (root.val == cur.val)
			is = isSubTree(root.left, cur.left) && isSubTree(root.right, cur.right);
		return is;
	}

	static void treetest() {
		TNode root = new TNode(20);
		root.left = new TNode(1);
		root.right = new TNode(2);
		root.left.left = new TNode(8);
		root.left.right = new TNode(4);
		root.right.left = new TNode(5);
		root.right.right = new TNode(6);
		System.out.println((new StringBuilder("min sum is ")).append(findSum(root, 0, 0, maxHeight(root))).toString());
	}

	static int findSum(TNode node, int sum, int curHeight, int maxHeight) {
		if (node == null) {
			if (curHeight == maxHeight)
				return sum;
			else
				return 0x7fffffff;
		} else {
			curHeight++;
			int left = findSum(node.left, sum + node.val, curHeight, maxHeight);
			int right = findSum(node.right, sum + node.val, curHeight, maxHeight);
			return Math.min(left, right);
		}
	}

	static int maxHeight(TNode node) {
		if (node == null) {
			return 0;
		} else {
			int l = maxHeight(node.left);
			int r = maxHeight(node.right);
			return l <= r ? r + 1 : l + 1;
		}
	}

	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

}
