/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knightstour;

import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;
/**
 *
 * @author melodytribble
 */
public class Board {
    
    int size, position, prevPos, cycle, currentPos, nItems, fullStack;
    char[][] myBoard;
    Knight firstKnight, poppedK, peekK;
    Stack myStack;
    private static final int[][] moves8 = { {+1,-2}, {+2,-1}, {+2,+1}, {+1,+2},
                                            {-1,+2}, {-2,+1}, {-2,-1}, {-1,-2} };
    
   

    public Board(int s, int p)throws IOException 
    {
        size = s;
        position = p;
        fullStack = size*size;
        myBoard= new char[size][size];
        firstKnight = new Knight(position);
        myStack = new Stack();
        prevPos = -1;
        printBoard();
        
        int x = (position-1)%size; // translate from position#
        int y = (position-1)/size; // to (x,y) format
     
        myBoard[y][x] = 'O'; //occupied
        myStack.push(firstKnight);
        nItems = 1;
        printBoard();
    }
    
    public void runTour(int p)
    {
        while(!isStackFull())
        {
            int temp = getNextPos();
            if(temp>-1)
            {
                position = temp;
                System.out.println("position is changed to "+ position+"\n");
                Knight k = new Knight(position);
                System.out.println("*******************************************\nnew knight at position " + k.getPosition()+"\n");
                myStack.push(k);
                nItems++;
                myBoard[getY(position)][getX(position)]='O';//occupied
                printBoard();
                
            }else
            { 
            System.out.println("No possible move =( remove last Knight\n");
                peekK = (Knight)myStack.peek();  
                System.out.println("Knight at the top of the stack is "+ peekK.getPosition() );
                poppedK = new Knight(0);
                poppedK = (Knight)myStack.pop();
                position = poppedK.getPosition();
                System.out.println("Knight removed at position "+ poppedK.getPosition() );

                nItems--;
                
                if (prevPos>0){
                    myBoard[getY(prevPos)][getX(prevPos)]= 0;
                }
                
                myBoard[getY(position)][getX(position)]='X';//checked
                prevPos = position;
                System.out.println("Previous Position is now "+prevPos+"\n");
                peekK = (Knight)myStack.peek();
                System.out.println("Knight at the top of the stack is "+ peekK.getPosition() );
                position = peekK.getPosition();
                System.out.println("Position is now changed to "+ position+"\n");

                
                printBoard();
            }
        }
       
    }
    
     /**
     * This picks the next possible move
     * @return next moves position or -1 for failure to find next move
     */    
    public int getNextPos()
    { 
         cycle = 0; // reset move index
         while(cycle < 8) 
         {
            System.out.println("x cycle: "+cycle+"\n");
            int dx = moves8[cycle][0]; // get move in
            int dy = moves8[cycle][1]; // (x,y) format
            int x = getX(position);// translate from position#
            int y = getY(position);// to (x,y) format

            x = x + dx; // add move to
            y = y + dy; // position
            System.out.println("next possible position is: ("+x+", "+y+")\n");

            cycle++; // used this move


            if(x>=0 && x<size && y>=0 && y<size) // on the board?
            { // yes
            System.out.println("Move is on the board\n");
                int nextPos = x + y*size + 1; // (x,y) to j
                   System.out.println("next possible move is "+ nextPos+"\n");
                    if(isOccupied(nextPos)==false) // unoccupied cell?
                    { // no
                        System.out.println("next legal move is "+ nextPos+"\n");
                        return nextPos; // found a move
                    }
            } // end if(x>=0...)
            System.out.println("Move not on board");
         } // end while // no possible move
            System.out.println("No possible move\n");

         return -1; // failure
     } // end getNextPos()  
    
    private boolean isOccupied(int pos)
    {
        int x = getX(pos);
        int y = getY(pos);
        
        if(myBoard[y][x] == 'O'|| myBoard[y][x]=='X')
        {
            return true;
        }else 
        {
            return false;
        }
    }

    private int getX(int pos)
    {
        int x = (pos-1)%size; 
        System.out.println("x = "+ x+"\n");
        return x;
    }
    
    private int getY(int pos)
    {
        int y = (pos-1)/size; 
        System.out.println("y = "+ y+"\n");

        return y;
    }
    
    public int getSize()
    {
        return size;
    }
    
    public int getFirstPos()
    {
        return position;
    }
    
    public boolean isStackFull()
    {
        if(nItems == fullStack)
        {
            System.out.println("Number if Items in the stack = "+ nItems+"\n");
            return true;
        }else
        {
            System.out.println("Number if Items in the stack = "+ nItems+"\n");
            return false;
        }
            
    }
    public void fillBoard()
    {
        int p = 1;
        for(int i = 0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {
                myBoard[i][j] = 0;
            }
        }
    }
    
    public void printBoard()
    {   
        String output = "Current Board: \n";
        for(int x = 0; x<size; x++)
        {
            for(int y=0; y<size; y++)
            {
                output += myBoard[x][y]+"  ";
            }
            output += "\n";
        }
        System.out.println(output);
    }
    
}
