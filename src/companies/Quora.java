//package companies;

import java.util.Scanner;

class Quora {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();

		int elems[] = new int[N];
		for (int i = 0; i < N ; i++ ) {
			elems[i] = sc.nextInt();
		}

		for(int i = 0; i <= N-K; i++) {
			int nonInc = getSequences(elems, i, i+K, true);
			int nonDec = getSequences(elems, i, i+K, false);
			System.out.println(nonDec-nonInc);
		}
	}

	private static int getSequences(int[] elems, int from, int to, boolean order) {
		
		int count = 0;

		for(int i = from; i < to; i++) {
			for (int j = i+1; j < to;  j++) {
				if(check(elems, i, j, order)) {
					count++;
				}
			}
		}
		return count;
	}

	// true is non decreasing. false is non increasing.
	private static boolean check(int[] elems, int from, int to, boolean order) {
		int prev = elems[from];
		for(int i = from+1; i <= to; i++) {
			if(order && elems[i] - prev > 0) return false;
			if(!order && prev - elems[i] > 0) return false;
			prev = elems[i];
		}
		return true;
	}
}