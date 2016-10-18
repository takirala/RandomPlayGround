
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
    
