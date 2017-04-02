package expr;
 
public class Function implements Expression  {
   String name; Expression exp;
   java.lang.reflect.Method met;
   public Function(String fn, Expression x) { 
      name = fn; exp = x;
      try{
        met = Math.class.getMethod(fn, Double.TYPE);
      } catch (NoSuchMethodException e) {
        met = null;
      }
   }
   public float fValue() {
      try{
        Double d = (Double)met.invoke(null, exp.fValue()); 
        return d.floatValue();
      //IllegalAccessException  InvocationTargetException
      } catch (Exception e) {
        return Float.NaN;
      }
   }
   public String toPostfix() { return exp.toPostfix()+" "+name; }
   public String toString() { return name+"("+exp+")"; }
   public String toTree() { return "implement this"; }

   static   Expression s = new Function("sqrt", new Constant(400));
   static   Expression c = new Function("cos", new Constant(0));
   static   Expression e = new Binary(s, Token.MINUS, c);
   public static void main(String[] args) {
      System.out.println(e.toPostfix());       // 400 sqrt 0 cos -
      System.out.println(e+" = "+e.fValue());  // sqrt(400)-cos(0) = 19.0
   }
}
