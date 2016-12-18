from sys import stdin
import re

def main2():
	numOfRecords = int(raw_input())

	records = {}

	while numOfRecords>0:
		split = raw_input().split(":")
		ssn = split[0]
		name = split[1]
		existing = ('', '', '')

		if ssn is records:
			existing = records[ssn]
		
		first = ''
		middle = ''
		last = ''

		if ',' in name: # Case 2 and 4
			last = name.split(",")[0]
			if ' ' in name:
				middle = name.split(",")[1].split()[1] # Case 4
			first = name.split(",")[1].split()[0] # Case 2
		
		elif not ' ' in name :
			first = name # Case 1

		elif '.' in name: # Case 6 and 7
			split = name.split('.')
			if(len(split) == 3) :
				#case 6
				first = split[0]
				middle = split[1]
				last = split[2]
			else : #case 7
				first = name.split('.')[0].split()[0]
				middle = name.split('.')[0].split()[1]
				last = name.split('.')[1]

		else :
			#Case 3 and 5
			split = name.split()
			if(len(split) == 3) :
				#case 5
				first = split[0]
				middle = split[1]
				last = split[2]
			else :
				first = split[0]
				last = split[1]

		records[ssn] = ( max(first, existing[0]) ,max(middle, existing[1]) ,max(last, existing[2]))	
		numOfRecords = numOfRecords-1;

	print records

def main():
	#pAndM = raw_input("Enter P and M")
	stringS = "ABCDDCDABCDABCDDDCDA" #raw_input()
	pattern = "AB*D"
	result = ""
	for c in pattern:
		if c == '*' :
			result += "[A-D]{1}"
		else :
			result += "(" + c + ")"
	pattern = re.match(result, stringS)
	print len(pattern.groups())


def main3():

	raw_input() #ignore s and p length
	S = raw_input()
	P = raw_input()
	'''
	regex = ''
	for c in P:
	    if c == '*':
	        regex += '[A-D]{1}'
	    else :
	        regex += '(' + c + ')'
	pattern = re.match(regex, S)
	if pattern == None:
	    print 0
	else :
	    print len(pattern.groups())
	'''

	patternLen = int(len(P))
	count = 0;

	for i in range(0, len(S)) :
		end = i + patternLen
		temp = S[i:end]
	   	for j in range(0, patternLen):
	   		if (P[j] != '*' and temp[j] != P[j]):
	   			break
		count = count + 1
	print count;

if __name__ == '__main__':
	main()