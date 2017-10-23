/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng;

/**
 *
 * @author clayton g thomas jr
 */
public class MedianKrnl {
    
   private int krnlMask[][];
   private int defaultWidth = 3;
   private int defaultHeight = 3;
   private int maskSum;

   public MedianKrnl()
   {
      intlzKrnl3x3();
   }
   public void intlzKrnl3x3()
   {
      krnlMask = new int[defaultWidth][defaultHeight];          
 
      krnlMask[0][0] = 1;                 
      krnlMask[0][1] = 1;                 
      krnlMask[0][2] = 1;                 
      krnlMask[1][0] = 1;                 
      krnlMask[1][1] = 1;
      krnlMask[1][2] = 1;                 
      krnlMask[2][0] = 1;                 
      krnlMask[2][1] = 1;
      krnlMask[2][2] = 1; 
      
      maskSum = 9;
   }
   public void intlzKrnl5x5()
   {
      krnlMask = new int[defaultWidth][defaultHeight];          
 
      krnlMask[0][0] = 1;                 
      krnlMask[0][1] = 1;                 
      krnlMask[0][2] = 1;    
      krnlMask[0][3] = 1;          
      krnlMask[0][4] = 1;          
      krnlMask[1][0] = 1;                 
      krnlMask[1][1] = 1;
      krnlMask[1][2] = 1;          
      krnlMask[1][3] = 1;          
      krnlMask[1][4] = 1;                
      krnlMask[2][0] = 1;                 
      krnlMask[2][1] = 1;
      krnlMask[2][2] = 1;                 
      krnlMask[2][3] = 1;          
      krnlMask[2][4] = 1;
      krnlMask[3][0] = 1;                 
      krnlMask[3][1] = 1;
      krnlMask[3][2] = 1;                 
      krnlMask[3][3] = 1;          
      krnlMask[3][4] = 1;      
      krnlMask[4][0] = 1;                 
      krnlMask[4][1] = 1;
      krnlMask[4][2] = 1;                 
      krnlMask[4][3] = 1;          
      krnlMask[4][4] = 1;      
      
      maskSum = 25;
   }   
   public int[][] getKrnlMask()
   {
      return krnlMask;
   }    
   public int getMaskSum()
   {
       return maskSum;
   }
}