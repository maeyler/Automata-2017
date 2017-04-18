abstract class PDA extends Automaton {

    abstract String delta(char p, char c);
    boolean accept(char init, String w) {
        int m = w.length(); 
        Stack s = new Stack(); 
        s.push(init); int i=0;  
        while (!s.isEmpty()) {
            char c = (i<m ? w.charAt(i) : 0);
            //System.out.printf("%s %c %s %n", i, c, s);
            char p = s.pop();
            if (p == c) { //stack matches input
                i++; System.out.print(" ");
            } else { //find a valid transition
                String d = delta(p, c);
                if (d == null) return false;
                s.push(d);
            }
        }
        //System.out.printf("%s   %s %n", i, s);
        return (i == m);
    }
}
