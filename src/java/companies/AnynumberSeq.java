package java.companies;

import java.util.ArrayList;

public class AnynumberSeq {

	static final String specialChar = "#";

	public static void main(String[] args) {
		printAll("", 123123L);
	}

	private static void printAll(String seq, long val) {
		// List of operations + , - , * , /

		// generate all permutations splits of the string. exhaustive list.

		int noOfMaxSplits = seq.length() - 1;
		for (int splitSize = 0; splitSize < noOfMaxSplits; splitSize++) {
			printAllForSplitSize(seq, splitSize, val);
		}
	}

	private static void evaluateAndPrint(String expr, int count) {

	}

	private static ArrayList<String> printAllForSplitSize(String seq, int splitSize, long val) {
		ArrayList<String> list = new ArrayList<String>();
		// no consecutive splits obviously.
		switch (splitSize) {
		case 1:
			for (int i = 1; i < seq.length() - 1; i++) {
				list.add(seq.substring(0, i) + specialChar + seq.substring(i + 1, seq.length() - 1));
			}
			break;
		case 2:
			for (int i = 1; i < seq.length() - 1; i++) {
				for (int j = i + 1; j < seq.length() - 1; j++) {
					list.add(seq.substring(0, i) + specialChar + seq.substring(i + 1, j) + specialChar
							+ seq.substring(j + 1, seq.length() - 1));
				}
			}
			break;
		case 3:
			for (int i = 1; i < seq.length() - 1; i++) {
				for (int j = i + 1; j < seq.length() - 1; j++) {
					for (int k = j + 1; k < seq.length() - 1; k++) {
						list.add(seq.substring(0, i) + specialChar + seq.substring(i + 1, j) + specialChar
								+ seq.substring(j + 1, k) + specialChar + seq.substring(k + 1, seq.length() - 1));
					}
				}
			}
			break;
		case 4:
			for (int i = 1; i < seq.length() - 1; i++) {
				for (int j = i + 1; j < seq.length() - 1; j++) {
					for (int k = j + 1; k < seq.length() - 1; k++) {
						for (int l = k + 1; l < seq.length() - 1; l++) {
							list.add(seq.substring(0, i) + specialChar + seq.substring(i + 1, j) + specialChar
									+ seq.substring(j + 1, k) + specialChar + seq.substring(k + 1, l) + specialChar
									+ seq.substring(l + 1, seq.length() - 1));
						}
					}
				}
			}
			break;
		case 5:
			for (int i = 1; i < seq.length() - 1; i++) {
				for (int j = i + 1; j < seq.length() - 1; j++) {
					for (int k = j + 1; k < seq.length() - 1; k++) {
						for (int l = k + 1; l < seq.length() - 1; l++) {
							list.add(seq.substring(0, i) + specialChar + seq.substring(i + 1, j) + specialChar
									+ seq.substring(j + 1, k) + specialChar + seq.substring(k + 1, l) + specialChar
									+ seq.substring(l + 1, seq.length() - 1));
						}
					}
				}
			}
			break;
		default:

		}
		return list;
	}
}
