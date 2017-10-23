package cgtjr.academics.math.algebra.zequations;

public abstract class ZEquations extends ZEquationsVrTp implements Equations
{
   public abstract double computeZValues(double x,double y);

   public double[][] rtrvZValues()
   {
      return getZ();
   }
   public float getShiftScaledX()
   {
      float aXShift = getXShift();
      float aXScale = getXScale();
      float aX = getX();
      return aXScale*(aX+aXShift);
   }
   public float getShiftScaledY()
   {
      float aYShift = getYShift();
      float aYScale = getYScale();
      float aY = getY();
      return aYScale*(aY+aYShift);
   }
   public static ZEquations rtrvByName(String myEquation)
   {
      ZEquations aZEquations = null;  
      if(myEquation.equals(LogEquations.getName()))
      {
         aZEquations  = new LogEquations();
      }else if(myEquation.equals(SinCircleEquations.getName()))
      {
         aZEquations  = new SinCircleEquations();
      }else if(myEquation.equals(ConeEquations.getName()))
      {
         aZEquations  = new ConeEquations();
      }else if(myEquation.equals(HyperParaEquations.getName()))
      {
         aZEquations  = new HyperParaEquations();
      }
      return aZEquations;
   }
   public static ZEquations rtrvByEquation(String myEquation)
   {
      ZEquations aZEquations = null;
      if(myEquation.equals(LogEquations.getEqtnStrng()))
      {
         aZEquations  = new LogEquations();
      }else if(myEquation.equals(SinCircleEquations.getEqtnStrng()))
      {
         aZEquations  = new SinCircleEquations();
      }else if(myEquation.equals(ConeEquations.getEqtnStrng()))
      {
         aZEquations  = new ConeEquations();
      }else if(myEquation.equals(HyperParaEquations.getEqtnStrng()))
      {
         aZEquations  = new HyperParaEquations();
      }else if(myEquation.equals(ClndrCncvEqutns.getEqtnStrng()))
      {
         aZEquations  = new ClndrCncvEqutns();
      }else if(myEquation.equals(PrmdEqutns.getEqtnStrng()))
      {
         aZEquations  = new PrmdEqutns();
      }
      return aZEquations;
   }
   public static String getEqtnStrng()
   {
      return "";
   }
}