package expr;
 
/** Constants and four binary operations are implemented. 
 */
 
public class Constant implements Expression  {
   float num;
   /** Constructs a constant Expression. */   
   public Constant(float n) { num = n; }
   public float fValue() { return num; }
   public String toPostfix() { return ' '+numToStr(num); }
   public String toString() { return numToStr(num); }
   /** Converts argument to String, removing ".0" 
    * at the end of an integer constant.
    */   
   public static String numToStr(float n) {
      String s = ""+n;
      if (s.endsWith(".0"))  //integers need no decimal point
         s = s.substring(0, s.length()-2);
      return s;
   }
   public String toTree() { return "implement this"; }
}
