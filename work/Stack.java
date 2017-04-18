import java.util.Arrays;

class Stack {

    final static int M = 10;
    int n; char[] data; 

    public Stack() { 
        clear();
    }
    public void clear() {
        n = 0; data = new char[M];  
    }
    public boolean isEmpty() {
        return (n == 0);
    }
    public void push(char c) {
        if (n == data.length)  //larger array is needed
            data = Arrays.copyOf(data, n+M);
        data[n++] = c;  //increment n after data access
    }
    public void push(String s) { 
        for (int i=s.length()-1; i>=0; i--) 
            push(s.charAt(i));  //push chars in reverse
    }
    public char pop() {
        return data[--n];  //decrement n before data access
    }
    public String toString() {
        String s = "";
        if (n > M) s += data[n-1]+"..."+data[0];
        else for (int i=n-1; i>=0; i--) 
            s += data[i];  //add chars in reverse
        return "["+s+"] "+n;
    }
    
    public static void main(String[] args) {
        Stack s = new Stack();
        s.push("Z"); 
        System.out.println(s);
        s.push("bir"); s.push("iki"); s.push("uc");
        System.out.println(s);
        s.pop(); s.pop(); s.push("son");
        System.out.println(s);
        s.push("X");
        System.out.println(s);
    }
}
