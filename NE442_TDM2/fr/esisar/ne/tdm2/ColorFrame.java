//package fr.esisar.ne.tdm2;

import java.awt.Color;

import javax.swing.JFrame;

public class ColorFrame
{
    public static void main(String[] args) throws Exception
    {
        ColorFrame c = new ColorFrame();
        c.execute();
    }

    private void execute() throws Exception
    {
    	//Ouverture d'une nouvelle fenetre
        JFrame frame = new JFrame("Chenillard");
        frame.setSize(300,300);

        //Affichage de la fenetre en vert
        frame.getContentPane().setBackground(Color.GREEN);
        frame.setVisible(true);
        Thread.sleep(2000); //attendre un moment


        //Affichage de la fenetre en rouge
        frame.getContentPane().setBackground(Color.RED);
        frame.setVisible(true);
        Thread.sleep(2000); //attendre un moment

        //Affichage de la fenetre en vert
        frame.getContentPane().setBackground(Color.GREEN);
        frame.setVisible(true);
        Thread.sleep(2000);

        //Fermeture de la fenetre
        frame.dispose();

    }
}
