package medium;

import java.util.ArrayList;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class LeastCommonAncestor {

	public static void main(String[] args) {
		LeastCommonAncestor lca = new LeastCommonAncestor();
		TreeNode leaf1 = new TreeNode(1);
		TreeNode leaf2 = new TreeNode(2);
		TreeNode node = new TreeNode(3);

		node.left = leaf1;
		node.right = leaf2;
		
		TreeNode node1 = new TreeNode(9);
		//TreeNode node2 = new TreeNode(2);
		TreeNode root = new TreeNode(6);

		root.left = node;
		node.right = node1;
		
		ArrayList<TreeNode>  asd = lca.getAncs(root, leaf1);
		
		System.out.println(asd.size());

	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

		ArrayList<TreeNode> forp = new ArrayList<TreeNode>();
		ArrayList<TreeNode> forq = new ArrayList<TreeNode>();

		forp = getAncs(root, p);
		forq = getAncs(root, q);

		int len1 = forp.size();
		int len2 = forq.size();

		for (int i = len1 - 1; i >= 0; i--) {
			TreeNode a = forp.get(i);
			for (int j = len2 - 1; j >= 0; j--) {
				if (forq.get(j).val == a.val) {
					return a;
				}
			}
		}
		return root;
	}

	private ArrayList<TreeNode> getAncs(TreeNode r, TreeNode point) {
		TreeNode root = r;
		TreeNode p = point;
		ArrayList<TreeNode> forp = new ArrayList<TreeNode>();
		if (root.val == p.val) {
			forp.add(p);
			return forp;
		}

		while (root.val != p.val) {
			forp.add(p);
			if (p.val < root.val) {
				root = root.left;
			} else {
				root = root.right;
			}
		}
		return forp;
	}

}
