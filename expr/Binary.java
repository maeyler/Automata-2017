package expr;
 
/** Constants and four binary operations are implemented. 
 */

public class Binary implements Expression  {
   Expression left;  Token oper;  Expression right;
   /** Constructs a binary operation from two Expressions and an operator. 
    */   
   public Binary(Expression le, Token op, Expression re) {
      left = le; oper = op; right = re; 
   }
   public float fValue() { 
      if (oper == Token.PLUS)  return left.fValue()+right.fValue();
      if (oper == Token.MINUS) return left.fValue()-right.fValue();
      if (oper == Token.STAR)  return left.fValue()*right.fValue();
      if (oper == Token.SLASH) return left.fValue()/right.fValue();
      return Float.NaN;
   }
   public String toPostfix() { 
      return left.toPostfix()+right.toPostfix()+' '+oper; 
   }
   int precedence() {
      if (oper == Token.PLUS || oper == Token.MINUS) return 10;
      if (oper == Token.STAR || oper == Token.SLASH) return 20;
      throw new IllegalArgumentException("operation "+oper);
   }
   public String toString() {
      return toString(left, false)+ oper +toString(right, true);
   }
   String toString(Expression e, boolean atRight) {
      String s = e.toString();
      if (!(e instanceof Binary)) return s;
      int prec = this.precedence();
      int p = ((Binary)e).precedence();
      if (prec<p ||(prec==p && !atRight)) return s;
      return Token.LEFT +s+ Token.RIGHT;
   }
   public String toTree() { return "implement this"; }
}
