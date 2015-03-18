package Biblioteca;

import java.applet.AudioClip;
import javax.swing.JApplet;
import java.io.File;
import java.net.URL;

public class Cat_Sonido{
 @SuppressWarnings({ "deprecation", "static-access" })
public static void main(String a[] ){
  try {
   //archivo de audio
   File f=new File("C:\\pato_juan.wav");
   //lo convertimos a url
   URL u=f.toURL();
   //Bueno de la AudioClip no se puede instancias por eso esto
   AudioClip sonido=JApplet.newAudioClip(u);
   //para que suene
   sonido.play();
   //como el programa se ejecuta muy rapido el audio no se alcanza a escuchar
   Thread.currentThread().sleep(8000);
   //si fuese una ventana no fuese necesario esa linea
  }catch (Exception ex) {
   System.out.println (ex);
  }
 }
}