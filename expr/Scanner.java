package expr;
 
/** Lexical phase: scan input and return tokens.
 *
 * @author M A Eyler
 */
 
public class Scanner {
   final String source; //String being parsed
   int prev;      //previous char position
   int next;      //next char position
   Token tok;     //current token 
   float nval;    //numeric value for number
   String sval;   //String value for ident
   final static String sample = "if (25.25*x.yz25 = 25.) z25=25.0";
   
   /** Constructs an instance, using s as the input */   
   public Scanner(String s) {
       source = s; prev = 0; next = 0; 
   }

   void getNumber() {
      while (next < source.length()) {
         char c = source.charAt(next);
         if (c=='.' || Character.isDigit(c)) next++;
         else break;
      }
      tok = Token.NUMBER; 
      String s = source.substring(prev, next);
      nval = Float.parseFloat(s);
   }
   void getIdent() {
      while (next < source.length()) {
         char c = source.charAt(next);
         if (Character.isLetterOrDigit(c)) next++;
         else break;
      }
      tok = Token.IDENT; 
      sval = source.substring(prev, next);
   }
   /** Returns next token after reading a sufficient number of chars */   
   public Token nextToken() {
      nval = 0; sval = "";
      char c;
      do {
         if (next >= source.length()) return (tok = Token.EOF);
         c = source.charAt(next++);  //read next char
      } while (Character.isWhitespace(c));
      prev = next-1;
      if (Character.isLetter(c)) getIdent();
      else if (Character.isDigit(c)) getNumber();
      else tok = Token.valueOf(c);  //tok = c;
      return tok;
   }
   /** String representation of the current token */   
   public String toString() { 
      String s = tok.toString(); 
      if (tok == Token.NUMBER) return Constant.numToStr(nval);
      if (tok == Token.IDENT)  return sval;
      return s;
   }
   /** Prints all (remaining) tokens to System.out */   
   public void listTokens() {
      while (tok != Token.EOF) {
         nextToken();
         System.out.println(toString());
      }
   }

   /** Test code for this class */   
   public static void main(String[] args) {
      System.out.println(sample);
      new Scanner(sample).listTokens();
   }
}
