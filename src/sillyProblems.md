
Perfect Square

    long r = x;
    while (r*r > x)
      r = (r + x/r) / 2;
    return r*r == x;
    
Rotate Image

    clockwise rotate
    first reverse up to down, then swap the symmetry 
    1 2 3     7 8 9     7 4 1
    4 5 6  => 4 5 6  => 8 5 2
    7 8 9     1 2 3     9 6 3

Last Set bit in x :

    x & ~(x-1)

Bits counting algorithm (Brian Kernighan)

*This algorithm goes through as many iterations as there are set bits. So if we have a 32-bit word with only the high bit set, then it will only go once through the loop. In the worst case, it will pass once per bit. An integer n has log(n) bits, hence the worst case is O(log(n)). Here's your code annotated at the important bits:*

    int count_set_bits(int n){
            int count = 0; // count accumulates the total bits set 
            while(n != 0){
                n &= (n-1); // clear the least significant bit set
                count++;
            }
      }

Phone Interview src : https://trananh.github.io/blog/work/2014/02/24/preparing-for-a-google-phone-interview.html

Java Collections Performance http://infotechgems.blogspot.com/2011/11/java-collections-performance-time.html

Graph Algos

https://www.hackerearth.com/practice/notes/graph-theory-part-i/

https://www.hackerearth.com/practice/notes/graph-theory-part-ii/

![Dijikstra](https://i.imgur.com/XDGniiN.gif)

Dijikstra won't work for negative edges. 
Time complexity O(Elog(V))

![BellmanFord](http://users.informatik.uni-halle.de/~jopsi/dinf504/bellman_ford.gif)

![Prims](http://i.stack.imgur.com/KofyW.gif)
![Krushkals](http://i.stack.imgur.com/6RCFr.gif)
