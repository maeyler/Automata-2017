package expr;

/** Constants used in various classes.
 * <P>
 * Various token values including four arithmetic operators.
 * @author M A Eyler
 */
 
public enum Token {
    
    LEFT("("), RIGHT(")"), EQUAL("="), PERIOD("."), 
    PLUS("+"), MINUS("-"), STAR("*"), SLASH("/"),
    IDENT("ident"), NUMBER("number"), EOF("eof"); 

    final String str;
    Token(String s) { str = s; }
    public String toString() { return str; }
    
    public static Token valueOf(char c) {
      for (Token t: values())
          if (t.str.length()==1 && t.str.charAt(0)==c) return t;
      return Token.EOF;
    }
    public static void main(String[] args) {
       Expression p = new Constant(3);
       Expression q = new Binary(new Constant(13), Token.MINUS, p);
       Expression e = new Binary(q, Token.STAR, new Constant(1.5f));
       System.out.println(e);
       System.out.println(e.fValue());
    }
}

/* NOT USED -- replaced by enum above
public interface Token {
    int EOF = -1;
    int NUMBER = -2;
    int IDENT = -3;
    char LEFT = '(';
    char RIGHT = ')';
    char PLUS = '+';
    char MINUS = '-';
    char STAR = '*';
    char SLASH = '/';
}
*/
