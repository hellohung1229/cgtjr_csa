/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.videotmp;

import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Nit
 */
public class Main {
   public static void main( String args[] ) {

    // Create a File Chooser:
    JFileChooser fileChooser = new JFileChooser();

    // Show Open File dialog:
    int result = fileChooser.showOpenDialog( null );

    if ( result == JFileChooser.APPROVE_OPTION ) // User chose a file
    {
        URL mediaURL = null;

        try {
            // Get the file as URL:
            mediaURL = fileChooser.getSelectedFile().toURI().toURL();
        } // end try

        catch ( MalformedURLException malformedURLException ) {
            System.err.println( "Could not create URL for the file" );
        } // end catch

        if ( mediaURL != null ) // Only display if there is a valid URL
        {
            System.out.println("Main: test");
            JFrame mediaTest = new JFrame( "Movie Player" );
            mediaTest.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
            MediaPanel mediaPanel = new MediaPanel( mediaURL );
            //mediaTest.add( mediaPanel );
            mediaTest.add( new JPanel() );            
            mediaTest.setSize( 300, 300 );
            mediaTest.setVisible( true );
        } // end inner if

    } // end outer if

   } // end main    
}
