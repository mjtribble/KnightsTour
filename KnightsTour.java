/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knightstour;

import java.io.IOException;
import java.util.Scanner;



/**
 *
 * @author melodytribble
 */
public class KnightsTour {

   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws IOException  {

        System.out.println("Enter board size (8 for 8x8 board): ");
        int size = getInt();
        
        System.out.println("Enter the beginning square (1-"+ size*size+"): ");
        int position = getInt();
        
        Board myBoard = new Board(size, position);
        myBoard.runTour();
        
        // TODO code application logic here
    }
     /**
     *This reads in the message and converts characters to uppercase
     *@return the message
     */
    private static String getInputString() throws IOException 
    {

            Scanner sc = new Scanner(System.in);
            String s= sc.nextLine();
            return s;
    }
    /**
    *This converts the user's input to an Integer value
    *@returns the integer the user input
    */
    private static int getInt() throws IOException
    {
        String s = getInputString();
        int i = Integer.parseInt(s); 

        return i;
    }
    
    
   
    
}
