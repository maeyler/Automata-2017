package expr;

import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import javax.swing.*;

/**
 * Simple GUI for expressions.
 * <pre>
 * 30.08.2002  project started
 * 23.12.2002  package
 * 17.07.2003  swing
 * 07.04.2013  simplify
 * 15.03.2015  scaled font & size
 * </pre>
 * @author M A Eyler
 */
 
public class Sampler extends JPanel {
   
   final static String version = "V2.2 Mar 2016";
   final static int nROWS = 16;
   final static Color BKGD = Color.cyan;
   final static Color verColor = Color.darkGray;
   static final int RESOLUTION = Toolkit.getDefaultToolkit().getScreenResolution();
   static final float RES_RATIO = RESOLUTION/96f;  //default resolution is 96
   final static Font 
      verFont = scaledFont("SansSerif", 0, 9),
      normal = scaledFont("SansSerif", 1, 12),
      ttype = scaledFont("MonoSpaced", 0, 12);
   final static String sample = "3*8-(5-3)*7-(2*5-3)/(5/(2+3))";
   final static int GAP = scaled(10);
   final JTextField exp = new JTextField(sample);
   final JTextField rpn = new JTextField(40);
   final JTextArea txt = new JTextArea("");
   final JLabel val = new JLabel("= value    ");

   /** constructor: called from main() */
   public Sampler() { 
      setLayout(new BorderLayout(GAP, GAP));
      setBackground(BKGD);
      int g = 2*GAP;
      Color b = Color.black;
      setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(b), 
            BorderFactory.createEmptyBorder(g, g, g, g)
      ));
      
      JPanel p = new JPanel();
      p.setOpaque(false);
      p.setLayout(new BorderLayout(GAP, GAP));
      JLabel lab1 = new JLabel("Expression:");
      lab1.setFont(normal);
      p.add(lab1, "West");
      exp.setFont(ttype);
      exp.addActionListener(new Ear());
      p.add(exp);
      val.setFont(normal);
      val.setForeground(Color.black);
      p.add(val, "East");
      add(p, "North");
      
      JPanel q = new JPanel();
      q.setOpaque(false);
      q.setLayout(new BorderLayout(GAP, GAP));
      JLabel lab2 = new JLabel("Postfix:");
      lab2.setFont(normal);
      q.add(lab2, "West");
      rpn.setFont(ttype);
      rpn.setEditable(false);
      q.add(rpn);
      add(q, "South");
      
      JScrollPane s = new JScrollPane(txt);
      txt.setEditable(false);
      txt.setRows(nROWS);
      JLabel lab3 = new JLabel("Tree:");
      lab3.setFont(normal);
      add(lab3, "West");
      add(s, "Center");
   }
   /** Displays version info */   
   public void paint(java.awt.Graphics g) {
      super.paint(g);
      g.setColor(verColor);
      g.setFont(verFont);
      g.drawString(version, 2, scaled(10));
   }

    static float scaled(float k) { return k*RES_RATIO; }
    static int scaled(int k) { return Math.round(k*RES_RATIO); }
    static Font scaledFont(String name, int style, float size) {
        Font f =  new Font(name, style, 1); //unit font
        return f.deriveFont(scaled(size));
    }
   /** Runs as an application */   
   public static void main(String[] args) {
      JFrame f = new JFrame("Expression Sampler");
      f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      f.setLocation(100, 100);
      f.setContentPane(new Sampler()); 
      f.pack(); f.setVisible(true); 
   }
///==============================================================
   class Ear implements ActionListener {
       public void actionPerformed(ActionEvent evt) {
          Parser P = new Parser(exp.getText());
          try {
             Expression e = P.parse();
             String v = Constant.numToStr(e.fValue());
             val.setText("= "+v);
             txt.setText(e.toTree());
             rpn.setText(e.toPostfix());
             System.out.println(e+" = "+v); 
          } catch (Exception x) {
             exp.select(P.lex.prev, P.lex.next);
             val.setText("error");
             txt.setText(x.getMessage());
             rpn.setText("");
             System.out.println(x);
          }
       }
   }
}
