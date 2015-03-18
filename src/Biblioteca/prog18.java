package Biblioteca;

import java.lang.*; import java.awt.*; import java.awt.event.*;

@SuppressWarnings("unused")
public class prog18 {

//declaracion, creacion e inicializacion de componentes, objetos y variables

static Frame ventana= new Frame();

// se ocupa un objeto u objetos al cual pegarle el popupmenu

static Panel p1 = new Panel();

static Label l1= new Label("clik derecho here");

static TextField t1 = new TextField(10);

// se ocupa un popupmenu

static PopupMenu cambios = new PopupMenu();

// se ocupan items para cada menu o columna

static MenuItem pd = new MenuItem("pes-dlr");

static MenuItem dp = new MenuItem("dlr-pesos");

static MenuItem exit=new MenuItem("Quit");

public static void main(String[] args)

{ // area de definicion de propiedades de el objeto

ventana.setTitle("mi programa");

// agregando items al popupmenu

cambios.add(pd);cambios.add(dp);cambios.add(exit);

// agregando el poupmenu a label y su escuchador de raton

l1.add(cambios);

l1.addMouseListener( new MouseAdapter()

{ public void mouseReleased( MouseEvent e ){

if ( e.isPopupTrigger() ) cambios.show(l1,100,0 ); } });

//agregando componentes a panel

p1.add(l1);p1.add(t1);

// agregando panel a frame

ventana.add(p1); ventana.pack(); ventana.setVisible(true);

ventana.addWindowListener( new WindowAdapter()

{ public void windowClosing(WindowEvent e){

ventana.dispose(); System.exit(0);}});

// agregando el unico escuchador que permite menuitem a cada item

exit.addActionListener( new ActionListener()

{ public void actionPerformed( ActionEvent e )

{ System.exit(0); } } );

pd.addActionListener( new ActionListener()

{ public void actionPerformed( ActionEvent e )

{ t1.setText("pesos a dolares"); } } );

dp.addActionListener( new ActionListener()

{ public void actionPerformed( ActionEvent e )

{ t1.setText("dolares a pesos"); } } );

}; // termina main

} // termina clase