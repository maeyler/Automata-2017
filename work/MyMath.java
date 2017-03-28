class MyMath {
    public static Object value(String fn, double x) {
      try{
        return Math.class.getMethod(fn, Double.TYPE).invoke(null, x);
      } catch (Exception e) {
        return null;
      }
    }
    public static void sqrt(double x) {
        String f = "sqrt(%s) = %s = %s %n";
        System.out.printf(f, x, Math.sqrt(x), value("sqrt", x));
    }
    public static void exp(double x) {
        String f = "exp(%s) = %s = %s%n";
        System.out.printf(f, x, Math.exp(x), value("exp", x));
    }
    public static void log(double x) {
        String f = "log(%s) = %s = %s%n";
        System.out.printf(f, x, Math.log(x), value("log", x));
    }
    public static void cos(double x) {
        String f = "cos(%s) = %s = %s%n";
        System.out.printf(f, x, Math.cos(x), value("cos", x));
    }
    public static void allFunctions() {  //ADVANCED TOPIC
        for (java.lang.reflect.Method m : Math.class.getMethods()) {
            Class[] a = m.getParameterTypes();
            if (a.length == 1 && a[0] == Double.TYPE) 
                System.out.printf("%s  ", m.getName());
        }
        System.out.println();
    }
    public static void main(String[] args) {
        sqrt(900); cos(0); exp(1); log(1/Math.E); cos(Math.PI);
    }
}
