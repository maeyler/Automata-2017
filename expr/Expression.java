package expr;
 
/** Basic methods in a simple expression.
 * Constants and four binary operations are implemented. 
 * Parentheses may be used witout any limitation.
 * 
 * @author M A Eyler
 */
 
public interface Expression {
   /** Returns float value of this Expression */    
   float fValue();
   /** String representation of this Expression */   
   String toString();
   /** Converts this Expression to postfix (RPN) */   
   String toPostfix();
   /** Converts this Expression to a tree */   
   String toTree();
}
