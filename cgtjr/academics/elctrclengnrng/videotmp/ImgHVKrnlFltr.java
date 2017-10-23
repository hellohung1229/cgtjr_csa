package cgtjr.academics.elctrclengnrng.videotmp;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;



public abstract class ImgHVKrnlFltr extends ImageFilter
{
   private GenericHVKrnl imageKernel;

   ImgHVKrnlFltr()
   {
   }
   /*
   ImgHVKrnlFltr(int myImageWidth,int myImageHeight)
   {
      super(myImageWidth,myImageHeight);
   }*/
   public void setKernel(GenericHVKrnl myGenericHVKrnl)
   {
      imageKernel = myGenericHVKrnl;
   }  
   public GenericHVKrnl getKernel()
   {
      return imageKernel;
   }  
}