class DFA extends Automaton {
   //deterministic finite automaton for (1+0)*10
   
   static enum State {A, B, C} //enumerated class
   public static Class getStates() { return State.class; } 

   public State delta(State q, char c) {
      if (q==State.A && c=='0') return State.A;
      if (q==State.B && c=='0') return State.C; 
      if (q==State.C && c=='0') return State.A; 
      if (c=='1') return State.B;
      return null;  //default is null -- no transition
   } 
   public boolean accept(String w) {
      State q = State.A;  //initial State
      System.out.printf("   %s", q);
      for (int j=0; j<w.length(); j++) {
         char c = w.charAt(j);
         State t = delta(q, c);
         //System.out.println(j+": ("+q+", "+c+") -> "+t);
         System.out.printf(" ----> %s", t);
         if (t == null) return false;
         q = t;
      }
      return (q==State.C);  //acceptable?
   } 

   public static final Automaton dfa = new DFA();
   public static void main(String[] args) {
      test(dfa, args); 
   }
}
