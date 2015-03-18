package Biblioteca;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MyGridLayout {

	JButton btn = new JButton("a");
    public MyGridLayout() {
        JPanel bPanel = new JPanel();
        bPanel.setLayout(new GridLayout(10, 10, 5, 5));
        
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
            	
            	
            	
            	
                JPanel b = new JPanel();
                b.add(btn);
                System.out.println("(" + row + ", " + col + ")");
                b.putClientProperty("column", row);
                b.putClientProperty("row", col);
                b.addMouseListener(new MouseAdapter() {
          
          			@Override
                    public void mouseClicked(MouseEvent e) {
                        JPanel btn = (JPanel) e.getSource();
                        System.out.println("clicked column " + btn.getClientProperty("column")
                                + ", row " + btn.getClientProperty("row"));
                    }
                });
                b.setBorder(new LineBorder(Color.blue, 1));
                bPanel.add(b);
            }
        }
        JFrame frame = new JFrame("PutClientProperty Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(bPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                 new MyGridLayout();
            }
        });
    }
}