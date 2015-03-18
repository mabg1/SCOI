package Biblioteca;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Ejemplo_GUI_A_Estudiar extends JInternalFrame {
	JTextField number;
	JButton square;
	JTextField result;

	public Ejemplo_GUI_A_Estudiar() {
		JComponent cp;

		number = new JTextField();
		square = new JButton("Square");
		result = new JTextField();

		number.setColumns(10);
		square.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					int n = Integer.decode(number.getText()).intValue();
					result.setText(n * n + "");
				} catch (NumberFormatException nfe) {
					result.setText("Format error");
				};
			};
		});
		result.setColumns(10);
		result.setEditable(false);

		cp = (JComponent) getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add("North", number);
		cp.add("Center", square);
		cp.add("South", result);
		pack();
		setVisible(true);
	}

	public static void main(String args[]) {
		final JFrame jf = new JFrame("Swing example");
		JDesktopPane jdp = new JDesktopPane();
		Ejemplo_GUI_A_Estudiar ge = new Ejemplo_GUI_A_Estudiar();
		JMenuBar jmb = new JMenuBar();
		JMenu jm = new JMenu("L&F");

		JMenuItem jmi1 = new JMenuItem("Metal");
		jm.add(jmi1);
		jmi1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aae) {
				try {
					UIManager.setLookAndFeel(
						"javax.swing.plaf.metal.MetalLookAndFeel");
					SwingUtilities.updateComponentTreeUI(jf);
				} catch (Throwable t) {
					System.out.println("Error: " + t);
				}
			}
		});

		JMenuItem jmi2 = new JMenuItem("Windows");
		jm.add(jmi2);
		jmi2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aae) {
				try {
					UIManager.setLookAndFeel(
						"com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					SwingUtilities.updateComponentTreeUI(jf);
				} catch (Throwable t) {
					System.out.println("Error: " + t);
				}
			}
		});

		JMenuItem jmi3 = new JMenuItem("Motif");
		jm.add(jmi3);
		jmi3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aae) {
				try {
					UIManager.setLookAndFeel(
						"com.sun.java.swing.plaf.motif.MotifLookAndFeel");
					SwingUtilities.updateComponentTreeUI(jf);
				} catch (Throwable t) {
					System.out.println("Error: " + t);
				}
			}
		});

		JMenuItem jmi4 = new JMenuItem("Mac");
		jm.add(jmi4);
		jmi4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aae) {
				try {
					UIManager.setLookAndFeel(
						"javax.swing.plaf.mac.MacLookAndFeel");
					SwingUtilities.updateComponentTreeUI(jf);
				} catch (Throwable t) {
					System.out.println("Error: " + t);
				}
			}
		});

		jmb.add(jm);
		jf.setJMenuBar(jmb);

		jdp.setPreferredSize(new Dimension(320, 200));
		jdp.add(ge);
		jf.getContentPane().add(jdp);
		jf.pack();
		jf.setVisible(true);
	}
}