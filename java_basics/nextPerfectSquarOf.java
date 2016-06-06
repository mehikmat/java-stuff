int n = 2;
while ( n < Integer.MAX_VALUE) {
  if (n == 0){
     return 1;
  }
  if (n < 0) {
      return 0;
  }
  ++n;
  int b = (int) Math.sqrt(n);
  if (b*b == n){
      return n;
  }
}
