import java.util.Set;
import java.util.EnumSet;

class NFA extends Automaton {
   //non-deterministic finite automaton for (1+0)*10
   
   static enum State {a, b, c} //enumerated class
   public static Class getStates() { return State.class; } 

   public Set<State> delta(State q, char c) {
     if (q==State.a && c=='0') return EnumSet.of(State.a);
     if (q==State.a && c=='1') return EnumSet.of(State.a, State.b);
     if (q==State.b && c=='0') return EnumSet.of(State.c); 
     return null;  //default is null -- no transition
   } 
   public boolean accept(String w) {
      Set<State> S = EnumSet.of(State.a);
      //System.out.printf("%6s", S); //initial State
      for (int j=0; j<w.length(); j++) {
         char c = w.charAt(j);
         Set<State> T = EnumSet.noneOf(State.class); //empty set
         for (State q: S) {
             Set<State> d = delta(q, c);
             if (d!=null) T.addAll(d);
         }
         System.out.println(j+": ("+S+", "+c+") -> "+T);
         //System.out.printf("->%6s", T);
         if (T.isEmpty()) return false;
         S = T;
      }
      return (S.contains(State.c));  //acceptable?
   } 

   public static final Automaton nfa = new NFA();
   public static void main(String[] args) {
      test(nfa, args); 
   }
}
