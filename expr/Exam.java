package expr;
   
public class Exam extends Parser {
   public Exam(String s) { super(s); }
   public void check() {  //for Q3
      tok = lex.nextToken();
      S(); match(Token.EOF);
      System.out.println(); 
   }
   void S() {  //for Q3
      if (tok == Token.LEFT) {
         match(Token.LEFT); 
         S(); 
         match(Token.RIGHT);
      } else { //A();
         match(Token.NUMBER); 
         while (tok == Token.COMMA) {
             match(Token.COMMA);
             match(Token.NUMBER); 
         }
      }
   }
   public static void test(String s) { //for Q3
      try {
         System.out.print(s);
         new Exam(s).check();
         System.out.println("Accepted");
      } catch (Exception x) {
         System.out.println();
         System.out.println(x.getMessage());
         System.out.println("Rejected");
      }
   }

   public static Expression //for Q2
      f = new Function("sqrt", new Constant(36)),
      b = new Binary(f, Token.MINUS, new Constant(6)),
      e = new Function("cos", b);
   public static void main(String[] args) {
      System.out.println("\nQuestion 2");
      System.out.printf(" e.fValue()    = %s %n", e.fValue());
      System.out.printf(" b.toPostfix() = %s %n", b.toPostfix());
      System.out.printf(" e.toString()  = %s %n", e.toString());
      System.out.println("\nQuestion 3");
      test("(3,5,8)"); test("(33,)55"); test("(((333)))"); 
   }
}
