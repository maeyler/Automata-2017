class PDA1 extends PDA {

    public String delta(char p, char c) {
        if (p=='S' && c=='a') return "aSa";
        if (p=='S' && c=='+') return "+aEa";
        if (p=='E' && c=='a') return "aEa";
        if (p=='E' && c=='=') return "=";
        return null;  //default is null -- no transition
    } 
    public boolean accept(String w) {
        return accept('S', w);
    } 

    public static void main(String[] args) {
        Automaton a = new PDA1();
        a.test("+a=a");
        a.test("a+a=aa");
        a.test("a+aa=aaa");
        a.test("a+aa=aaaaa");
        a.test("a+aa=aa");
        a.test("a+aa=baa");
        a.test("aa+=aa");
    }
}
