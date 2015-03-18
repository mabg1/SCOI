/**
* seaglasslookandfeel-0.1.5.jar
*/
package Biblioteca;

import java.awt.GridLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author beastieux
 */
@SuppressWarnings("serial")
public class Ejemplo_Temas_GUI extends JFrame {

    @SuppressWarnings("rawtypes")
	JComboBox cmb1=new JComboBox();

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Ejemplo_Temas_GUI()
    {
        JPanel pnlEjemplo=new JPanel();

        JTextField txt1=new JTextField();
        
        JCheckBox chk1=new JCheckBox("Opcion 1");


        cmb1.setModel(new DefaultComboBoxModel
                     (new String[] { "Estilo MetalLookAndFeel",
                                     "Estilo MotifLookAndFeel",
                                     "Estilo GTKLookAndFeel",
                                     "Estilo NimbusLookAndFeel",
                                     "Estilo WindowsLookAndFeel",
                                     "Estilo SeaGlassLookAndFeel" }));

        cmb1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb1ItemStateChanged(evt);
            }
        });


        JButton btn1=new JButton("Button 1");


        pnlEjemplo.add(txt1);
        pnlEjemplo.add(cmb1);
        pnlEjemplo.add(chk1);
        pnlEjemplo.add(btn1);

        pnlEjemplo.setLayout(new GridLayout(5,1));

        this.add(pnlEjemplo);
        this.setSize(500, 150);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }


    private void cmb1ItemStateChanged(java.awt.event.ItemEvent evt) {

        switch(cmb1.getSelectedIndex())
        {
            case 0:
                    setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel"); 
                    break;
            case 1:
                    setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel"); 
                    break;
            case 2:
                    setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel"); 
                    break;
            case 3:
                    setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
                    break;
            case 4:
                    setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
                    break;
            case 5:
                    setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel"); 
                    break;
            default:
                    setLookAndFeel(null);
        }
    }

    private void setLookAndFeel(String laf)
    {

            if (laf==null) {
                    laf=UIManager.getSystemLookAndFeelClassName();
            }
            else
            {
                try {
                        UIManager.setLookAndFeel(laf);
                } catch (InstantiationException ex) {

                } catch (ClassNotFoundException ex) {

                } catch (UnsupportedLookAndFeelException ex) {

                } catch (IllegalAccessException ex) {

                }
            }
            SwingUtilities.updateComponentTreeUI(this);
    }

    public static void main(String args[]) {
        Ejemplo_Temas_GUI obj = new Ejemplo_Temas_GUI();
        obj.setVisible(true);
    }
}