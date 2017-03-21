package expr;
 
/** Syntax phase: read tokens and check syntax. Do not generate anything.
 * Recursive-descent parsing algorithm for arithmetic expressions
 *
 * @author M A Eyler
 */
   
public class Checker {

   Scanner lex;
   Token tok; 
   final static String sample = "((3*8)-(5-3)*7)-(2*4)";

   public Checker(String s) { 
      lex = new Scanner(s); 
      System.out.println(s);
   }
   void match(Token k)  {
      System.out.print(lex);
      if (tok == k) tok = lex.nextToken();
      else expected(k.name());
   }
   void expected(String s) {
      error("Expected: "+s+", Found: "+tok.name());
   }
   void error(String msg) {
      throw new RuntimeException(msg);
   }
   /** 
    * May fail in case of a syntax error. 
    * Should be called once per instance.
    */   
   public void check() { 
      tok = lex.nextToken();
      expr(); match(Token.EOF);
      System.out.println(); 
   }
   void expr() {
      //System.out.printf(" expr: %s %n", lex);
      term(); Token t = tok;
      while (t == Token.PLUS || t == Token.MINUS)  {
         match(t); term(); t = tok;
      }
   }
   void term() {
      //System.out.printf(" term: %s %n", lex);
      factor(); Token t = tok;
      while (t == Token.STAR || t == Token.SLASH)  {
         match(t); factor(); t = tok;
      }
   }
   void factor() {
      //System.out.printf(" fact: %s %n", lex);
      if (tok == Token.NUMBER)  {
         match(Token.NUMBER); 
      }
      else if (tok == Token.LEFT)  {
         match(Token.LEFT);
         expr();
         match(Token.RIGHT); 
      }
      else expected("Factor"); 
   }
   
   /** Test code -- syntax check for the sample String
    */   
   public static void main(String[] args) {
      new Checker(sample).check();
      System.out.println("Accepted");
   }
}
