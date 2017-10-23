package cgtjr.academics.math.algebra.zequations;


import java.awt.*;

public class EqtnChoice
{
   public EqtnChoice()
   {
   }
   public static Choice rtrvChoices()
   {
      Choice eqtnList = new Choice();
      eqtnList.add(SinCircleEquations.getEqtnStrng());
      //eqtnList.add(LogEquations.getEqtnStrng());
      eqtnList.add(HyperParaEquations.getEqtnStrng());
      eqtnList.add(ConeEquations.getEqtnStrng());
      eqtnList.add(ClndrCncvEqutns.getEqtnStrng());
      eqtnList.add(PrmdEqutns.getEqtnStrng());
      //eqtnList.add(CbDvdSqrdEqtns.getEqtnStrng());
      //eqtnList.add(OneSinEqutns.getEqtnStrng());
      //eqtnList.add(TwoSinEqutns.getEqtnStrng());
      //eqtnList.add(CbDvdSqrdEqtns.getEqtnStrng());
      //eqtnList.add(HemiCnvxEqutns.getEqtnStrng());
      //eqtnList.add(HemiCncvEqutns.getEqtnStrng());
      //eqtnList.add(PrbldCncvEqtns.getEqtnStrng());
      //eqtnList.add(PrbldCnvxEqtns.getEqtnStrng());
      return eqtnList;
   }
}