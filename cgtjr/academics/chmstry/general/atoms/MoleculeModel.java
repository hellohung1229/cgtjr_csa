package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.math.geometry.linepnts.Line;
import cgtjr.academics.math.geometry.crdntepnts.MeshShp;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.chmstry.general.atoms.Atom;
import cgtjr.academics.math.graph.*;
import cgtjr.academics.math.geometry.*;
import cgtjr.academics.chmstry.physical.*;
import cgtjr.academics.general.MtrlVar;

import cgtjr.academics.math.lnralgbra.Trnsfrm;
import java.util.*;

public class MoleculeModel extends Molecule
{
   private String atomField;
   private String atomNumber;
   private String atomType;
   private String residueName;
   private String chain;
   private String residueChain;
   private HashMap branchDesignator;
   private LinkedHashMap branchIndicator;
 
   private Line theConnections;
   private String thePreviousAtom;
   private String thePreviousBackBoneAtom;
   private PDBAtom thePreviousPDBAtom;
   private PDBAtom thePrevResiduePDBAtom;
   private PDBAtom thePDBPreviousCNSAtom;

   private static PDBAtom theLastCarbonPeptide;

   private String xCoordinate;
   private String yCoordinate;
   private String zCoordinate;

   private Polarization moleculePolarization;

   public MoleculeModel()
   {
      setCheckBoundary(false);
      theConnections = new Line();
      moleculePolarization = new Polarization();
   }
   public void setAtomField(String anAtomField)
   {
      atomField = anAtomField;
   }
   public void setAtomNumber(String anAtomNumber)
   {
      atomNumber = anAtomNumber;
   }
   public void setPDBNumber(String anAtomField)
   {
      atomField = anAtomField;
   }
   public void setXCoordinate(String myXCoordinate)
   {
      xCoordinate = myXCoordinate;
   }
   public void setYCoordinate(String myYCoordinate)
   {
      yCoordinate = myYCoordinate;
   }
   public void setZCoordinate(String myXCoordinate)
   {
      zCoordinate = myXCoordinate;
   }
   public Enumeration processConnections(boolean isVersion2)
   {
      if(isVersion2==true)
      {
         return processConnectionsV2();
      }else{
         return processConnectionsV1();
      }
   }
   public Enumeration processConnectionsV2()
   {
      int anIndex = 0;
      PDBAtom aPDBAtom = null;

      Enumeration anEnumeration1 = rtrvEnumeration();
      //System.out.println("PDBMolecule.processConnections() size = "+getAdjacentVertices().size());
      Enumeration anEnumeration2 = null;
      PDBAtom anotherPDBAtom = null;
      while(anEnumeration1.hasMoreElements())
      {
         aPDBAtom = (PDBAtom)anEnumeration1.nextElement();
         anEnumeration2 = aPDBAtom.rtrvEnumeration();
         while(anEnumeration2.hasMoreElements())
         {
            anotherPDBAtom = (PDBAtom)anEnumeration2.nextElement();

            theConnections.addVertex(new Line(aPDBAtom.getX1(),aPDBAtom.getY1(),aPDBAtom.getZ1(),
                                     anotherPDBAtom.getX1(),anotherPDBAtom.getY1(),anotherPDBAtom.getZ1()));  
         }
      }
      return theConnections.rtrvEnumeration();
   }
   public Enumeration processConnectionsV1()
   {
      int anIndex = 0;
      PDBAtom aPDBAtom = null;

      String currentAtomName = null;
      branchDesignator = new HashMap();
      branchIndicator = new LinkedHashMap();

      PDBAtom aPDBPreviousAtom = null;
      PDBAtom thePrevResiduePDBAtom = null;

      Enumeration anEnumeration1 = rtrvEnumeration();
      //System.out.println("PDBMolecule.processConnections() size = "+getAdjacentVertices().size());
      
      while(anEnumeration1.hasMoreElements())
      {         
         aPDBAtom = (PDBAtom)anEnumeration1.nextElement();  

         updateBranchIndicator(aPDBPreviousAtom);
         updateBranchDesignator(aPDBAtom);
         if(anIndex == 0)
         {
            connectPeptide(aPDBAtom);
         }else{
            connect(aPDBAtom);
         }
         if(aPDBAtom.getName().charAt(1) == 'N' || (aPDBAtom.getName().charAt(1) == 'N' && Character.isLetter(aPDBAtom.getName().charAt(2)) )) 
         {
            aPDBPreviousAtom = aPDBAtom;
            thePDBPreviousCNSAtom = aPDBAtom;            
         }else if((aPDBAtom.getName().charAt(1) == 'C' && Character.isLetter(aPDBAtom.getName().charAt(2)))) 
         {
            aPDBPreviousAtom = aPDBAtom;
            thePDBPreviousCNSAtom = aPDBAtom;
         }else if((aPDBAtom.getName().charAt(1) == 'S' && Character.isLetter(aPDBAtom.getName().charAt(2)))) 
         {
            aPDBPreviousAtom = aPDBAtom;
            thePDBPreviousCNSAtom = aPDBAtom;
         }
         if( aPDBAtom.getName().substring(0,4).trim().equals("C") )
         {
            theLastCarbonPeptide = aPDBAtom;
         }
         if(aPDBAtom.getName().charAt(1) != 'H')
         {
            thePreviousPDBAtom = aPDBAtom;
         }
         if(aPDBAtom.getName().charAt(1) != 'H') 
         {
            thePrevResiduePDBAtom = aPDBAtom;
         }
         String aStr = aPDBAtom.getName();
         ++anIndex;
      }
      return theConnections.rtrvEnumeration();
   }
   public static void resetPeptideInfo()
   {
      theLastCarbonPeptide = null;
   }
   public void updateBranchIndicator(PDBAtom myAtom)
   {
      if(myAtom == null)
      {
         return;
      }
      String anAtom = myAtom.getName();
      char aChar = anAtom.charAt(3);
      if(!(anAtom.charAt(1) == 'N' || anAtom.charAt(1) == 'C' || 
          (anAtom.charAt(1) != 'C' && Character.isLetter(anAtom.charAt(1))  )))
      {
         return;
      }
      //System.out.println("PDBMolecule.updateBranchIndicator(): atom = "+myAtom.getName());
      if(aChar == ' ' || aChar == '0')
      {
         branchDesignator.put("0",myAtom);
      }else if(aChar == '1')
      {
         branchDesignator.put("1",myAtom);         
      }else if(aChar == '2')
      {
         branchDesignator.put("2",myAtom);         
      }else if(aChar == '3')
      {
         branchDesignator.put("3",myAtom);         
      }else if(aChar == '4')
      {
         branchDesignator.put("4",myAtom);         
      }else if(aChar == '5')
      {
         branchDesignator.put("5",myAtom);         
      }else if(aChar == '6')
      {
         branchDesignator.put("6",myAtom);         
      }else if(aChar == '7')
      {
         branchDesignator.put("7",myAtom);         
      }else if(aChar == '8')
      {
         branchDesignator.put("8",myAtom);         
      }else if(aChar == '9')
      {
         branchDesignator.put("9",myAtom);         
      }
   }
   public void updateBranchDesignator(PDBAtom myAtom)
   {
      if(myAtom == null)
      {
         return;
      }
      String anAtom = myAtom.getName();
      char aChar = anAtom.charAt(2);
      if(!(anAtom.charAt(1) == 'N' || anAtom.charAt(1) == 'C' || 
          (anAtom.charAt(1) != 'C' && Character.isLetter(anAtom.charAt(1))  )))
      {
         return;
      }
      if(Character.isLetter(aChar))
      {
         System.out.println("PDBMolecule.updateBranchDesignator() letter = "+aChar+" atom "+myAtom.getName());
         String aLetter = new String(""+aChar);

         branchIndicator.put(aLetter,myAtom);
      }
   }
   public void connect(PDBAtom myPDBAtom)
   {
      boolean connectStatus = false;

      /*
      if(connectStatus == false)
      {
         connectStatus = connectHAlone(myPDBAtom);
      }*/
      /*
      if(myPDBAtom == null)
      {
         return;
      }
      if(connectStatus == false)
      {
         connectStatus = connectOXT(myPDBAtom);
      }
      if(connectStatus == false)
      {
         connectStatus = connectHToO(myPDBAtom);
      }
      if(connectStatus == false)
      {
         connectStatus = connectTwoAtoms(myPDBAtom);
      }      
      if(connectStatus == false)
      {
         connectStatus = connectToPreviousBranch(myPDBAtom);
      }
      if(connectStatus == false)
      {
         connectStatus = connectHydrogen(myPDBAtom);
      }
      if(connectStatus == false)
      {
         connectStatus = connectHWithoutIndex(myPDBAtom);
      }
      if(connectStatus == false)
      {
         connectStatus = connectToPrevious(myPDBAtom);
      } 
      if(connectStatus == false)
      {
         connectStatus = connectHToNC(myPDBAtom);
      }*/
   }
   public boolean connectToPreviousCAtom(String anAtomName1,String anAtomName2)
   {
      Atom anAtom1 = (Atom)getVertexByName(anAtomName1);
      Atom anAtom2 = (Atom)getVertexByName(anAtomName2);

      if(anAtom1 != null && anAtom2 != null)
      {
         System.out.println("PDBMolecule.connectToPrevious():connecting atom 1 : "+anAtom1.getName()+" atom  2 : "+anAtom2.getName());
         System.out.println("PDBMolecule.connectToPrevious():connecting atom 1 : "+anAtom1+" atom  2 : "+anAtom2);
         theConnections.addVertex(new Line(anAtom1.getX1(),anAtom1.getY1(),anAtom1.getZ1(),
                                     anAtom2.getX1(),anAtom2.getY1(),anAtom2.getZ1()));
         Electronegativity.electroNegativityConnect(anAtom1,anAtom2);
         return true;
      }
      return false;
   }
   public boolean connectToPreviousBranch(PDBAtom myPDBAtom)
   {
      String aString = myPDBAtom.getName();
      String aSubString = aString.substring(2,4);

      char aChar = aString.charAt(3);

      String branchValue = null;
      String indicatorValue = null;

      PDBAtom aPDBAtom = null;

      if(myPDBAtom == null)
      {
         return false;
      } 
      /*
      if(aChar == ' ')
      {
         return false;
      }*/
      System.out.println("PDBMolecule.connectToPreviousBranch() : attempting connections atom  : "+myPDBAtom.getName());      
      if(!((myPDBAtom.getName().charAt(1) == 'N') || 
            (myPDBAtom.getName().charAt(1) == 'S' && Character.isLetter(myPDBAtom.getName().charAt(2))) ||
            (myPDBAtom.getName().charAt(1) == 'N' && Character.isLetter(myPDBAtom.getName().charAt(2))) ||
            (myPDBAtom.getName().charAt(1) == 'O' && Character.isLetter(myPDBAtom.getName().charAt(2))) ||
            (myPDBAtom.getName().charAt(1) == 'C' && Character.isLetter(myPDBAtom.getName().charAt(2)))
         ) )
      {
         return false;
      }
      if(aChar == ' ')
      {
         //aChar = '0';
         aPDBAtom = thePDBPreviousCNSAtom;
      }
      if(Character.isDigit(aChar))
      {
         branchValue = new String(""+aChar);
         aPDBAtom = (PDBAtom) branchDesignator.get(branchValue);
      }
      if(aPDBAtom == null && Character.isDigit(aChar))
      {
         //branchValue = new String("0");
         //Integer branchInteger = new Integer(aChar);
         //branchValue = ""+(branchInteger.intValue()-1);
         //indicatorValue = aString.
         //indicatorValue = branchIndicator.get(indicatorValue);
         //aPDBAtom = (PDBAtom) branchDesignator.get(indicatorValue);
         aPDBAtom = getNextToLastIndicator();
      }
      if(myPDBAtom != null && aPDBAtom != null )
      {
         System.out.println("PDBMolecule.connectToPreviousBranch() : connecting atom 1 : "+myPDBAtom.getName()+" atom  2 : "+aPDBAtom.getName());
         theConnections.addVertex(new Line(myPDBAtom.getX1(),myPDBAtom.getY1(),myPDBAtom.getZ1(),
                                     aPDBAtom.getX1(),aPDBAtom.getY1(),aPDBAtom.getZ1()));                                        
         Electronegativity.electroNegativityConnect(myPDBAtom,aPDBAtom);
         return true;
      }    
      return false;
   }
   public PDBAtom getNextToLastIndicator()
   {
      Set aSet = branchIndicator.keySet();
      Iterator anIterator = aSet.iterator();
      PDBAtom aPDBAtom1 = null;
      PDBAtom aPDBAtom2 = null;
      String aKey = null;

      while(anIterator.hasNext())
      {
         aKey = (String)anIterator.next();
         
         aPDBAtom2 = aPDBAtom1;
         aPDBAtom1 = (PDBAtom)branchIndicator.get(aKey);
      }
      return aPDBAtom2;
   }
   public boolean connectTwoAtoms(PDBAtom myPDBAtom)
   {
      if(myPDBAtom == null || myPDBAtom.getName().charAt(1) == 'H' || myPDBAtom.getName().charAt(2) == 'H')
      {
         return false;
      }
      String aString = myPDBAtom.getName();
      String aSubString = aString.substring(2,4);
      Integer branchInteger = null;
      String aPDBAtomName = null;

      PDBAtom aPDBAtom = null;

      System.out.println("PDBMolecule.connectTwoAtoms() : attempting to connect= "+aString);
      Enumeration anEnumeration = rtrvEnumeration();
      while(anEnumeration.hasMoreElements())
      {
         aPDBAtom = (PDBAtom)anEnumeration.nextElement();  
         aPDBAtomName = aPDBAtom.getName();
         
         if(aPDBAtomName.trim().equals(aSubString.trim()))
         {
            Electronegativity.electroNegativityConnect(myPDBAtom,aPDBAtom);
            //System.out.println("PDBMolecule.connectTwoAtoms() aPDBAtomName = "+aPDBAtomName+" substring : "+aSubString);

            System.out.println("PDBMolecule.connectTwoAtoms() atom 1 : "+myPDBAtom.getName()+" atom  2 : "+aPDBAtom.getName());
            //System.out.println("PDBMolecule.connectTwoAtoms() atom 1 : "+myPDBAtom +" atom  2 : "+aPDBAtom);
            theConnections.addVertex(new Line(myPDBAtom.getX1(),myPDBAtom.getY1(),myPDBAtom.getZ1(),
                                     aPDBAtom.getX1(),aPDBAtom.getY1(),aPDBAtom.getZ1()));       
            return true;
         }
      }
      return false;
   }   
   public boolean connectHToO(PDBAtom myPDBAtom)
   {
      if(myPDBAtom == null || ! (myPDBAtom.getName().substring(1,4).equals("HOC")||
                                 myPDBAtom.getName().substring(1,3).equals("HO")) )
      {
         return false;
      }
      String aString = myPDBAtom.getName();
      String aSubString = aString.substring(2,4);
      Integer branchInteger = null;
      String aPDBAtomName = null;

      PDBAtom aPDBAtom = null;

      System.out.println("PDBMolecule.connectHToO() : attempting to connect= "+aString);
      Enumeration anEnumeration = rtrvEnumeration();
      while(anEnumeration.hasMoreElements())
      {
         aPDBAtom = (PDBAtom)anEnumeration.nextElement();  
         aPDBAtomName = aPDBAtom.getName();
         
         if(aPDBAtomName.trim().equals(aSubString.trim()))
         {
            Electronegativity.electroNegativityConnect(myPDBAtom,aPDBAtom);
            //System.out.println("PDBMolecule.connectTwoAtoms() aPDBAtomName = "+aPDBAtomName+" substring : "+aSubString);

            System.out.println("PDBMolecule.connectTwoAtoms() atom 1 : "+myPDBAtom.getName()+" atom  2 : "+aPDBAtom.getName());
            //System.out.println("PDBMolecule.connectTwoAtoms() atom 1 : "+myPDBAtom +" atom  2 : "+aPDBAtom);
            theConnections.addVertex(new Line(myPDBAtom.getX1(),myPDBAtom.getY1(),myPDBAtom.getZ1(),
                                     aPDBAtom.getX1(),aPDBAtom.getY1(),aPDBAtom.getZ1()));       
            return true;
         }else if(aPDBAtomName.trim().equals("OXT"))
         {
            Electronegativity.electroNegativityConnect(myPDBAtom,aPDBAtom);
            //System.out.println("PDBMolecule.connectTwoAtoms() aPDBAtomName = "+aPDBAtomName+" substring : "+aSubString);

            System.out.println("PDBMolecule.connectTwoAtoms() atom 1 : "+myPDBAtom.getName()+" atom  2 : "+aPDBAtom.getName());
            System.out.println("PDBMolecule.connectTwoAtoms() atom 1 : "+myPDBAtom +" atom  2 : "+aPDBAtom);
            theConnections.addVertex(new Line(myPDBAtom.getX1(),myPDBAtom.getY1(),myPDBAtom.getZ1(),
                                     aPDBAtom.getX1(),aPDBAtom.getY1(),aPDBAtom.getZ1()));       
            return true;
         }
      }
    
      return false;
   }
   public boolean connectHydrogen(PDBAtom myPDBAtom)
   {
      String aString = myPDBAtom.getName();
      String aSubString = aString.substring(2,4);
      Integer branchInteger = null;
      String aPDBAtomName = null;

      PDBAtom aPDBAtom = null;

      if(myPDBAtom == null || aString == null || aString.charAt(1) != 'H')
      {
         return false;
      }
      System.out.println("PDBMolecule.connectHydrogen() : attempting connection name = "+aString+" substring = "+aSubString);
      Enumeration anEnumeration = rtrvEnumeration();
      while(anEnumeration.hasMoreElements())
      {
         aPDBAtom = (PDBAtom)anEnumeration.nextElement();  
         aPDBAtomName = aPDBAtom.getName();
         System.out.println("PDBMolecule.connectHydrogen() aPDBAtomName = "+aPDBAtomName+" substring : "+aSubString);
         
         
         if(aPDBAtomName.substring(2,4).equals(aSubString) && !aPDBAtomName.equals(aString))
         {
            Electronegativity.electroNegativityConnect(myPDBAtom,aPDBAtom);
            System.out.println("PDBMolecule.connectHydrogen() atom 1 : "+myPDBAtom.getName()+" atom  2 : "+aPDBAtom.getName());
            System.out.println("PDBMolecule.connectHydrogen() atom 1 : "+myPDBAtom +" atom  2 : "+aPDBAtom);
            
            theConnections.addVertex(new Line(myPDBAtom.getX1(),myPDBAtom.getY1(),myPDBAtom.getZ1(),
                                     aPDBAtom.getX1(),aPDBAtom.getY1(),aPDBAtom.getZ1()));       
            return true;
         }
      }
      return false;
   }

   public boolean connectHWithoutIndex(PDBAtom myPDBAtom)
   {
      String aString = myPDBAtom.getName();
      String aSubString = aString.substring(2,3);
      Integer branchInteger = null;
      String aPDBAtomName = null;

      PDBAtom aPDBAtom = null;

      if(aString == null || !( aString.charAt(1) == 'H'  ) )
      {
         return false;
      }
      System.out.println("PDBMolecule.connectHWithoutIndex() : attempting to connect : "+myPDBAtom.getName());
      Enumeration anEnumeration = rtrvEnumeration();
      while(anEnumeration.hasMoreElements())
      {
         aPDBAtom = (PDBAtom)anEnumeration.nextElement();  
         aPDBAtomName = aPDBAtom.getName();
         //System.out.println("PDBMolecule.connectHWithoutIndex() : charat 2 : "+myPDBAtom.getName().charAt(2)+" char at 2 : "+aPDBAtom.getName().charAt(2));

         if(aPDBAtomName != null && aPDBAtomName.charAt(2) == myPDBAtom.getName().charAt(2) && aPDBAtomName.charAt(3) == ' ')
         {
            Electronegativity.electroNegativityConnect(myPDBAtom,aPDBAtom);
            System.out.println("PDBMolecule.connectHWithoutIndex() : connecting atom 1 : "+myPDBAtom.getName()+" atom  2 : "+aPDBAtom.getName());

            theConnections.addVertex(new Line(myPDBAtom.getX1(),myPDBAtom.getY1(),myPDBAtom.getZ1(),
                                     aPDBAtom.getX1(),aPDBAtom.getY1(),aPDBAtom.getZ1()));    
            return true;   
         }
      }  
      return false;
   }
   public boolean connectHToNC(PDBAtom myPDBAtom)
   {
      String aString = myPDBAtom.getName();
      String aSubString = aString.substring(2,3);
      Integer branchInteger = null;
      String aPDBAtomName = null;

      PDBAtom aPDBAtom = null;

      if(aString == null || !( aString.charAt(1) == 'H'  ) )
      {
         return false;
      }
      System.out.println("PDBMolecule.connectHWithoutIndex() : attempting to connect : "+myPDBAtom.getName());
      Enumeration anEnumeration = rtrvEnumeration();
      while(anEnumeration.hasMoreElements())
      {
         aPDBAtom = (PDBAtom)anEnumeration.nextElement();  
         aPDBAtomName = aPDBAtom.getName();
         //System.out.println("PDBMolecule.connectHWithoutIndex() : charat 2 : "+myPDBAtom.getName().charAt(2)+" char at 2 : "+aPDBAtom.getName().charAt(2));

         if(aPDBAtomName != null && aPDBAtomName.trim().equals("N") && myPDBAtom.getName().trim().equals("HNC"))
         {
            Electronegativity.electroNegativityConnect(myPDBAtom,aPDBAtom);
            System.out.println("PDBMolecule.connectHWithoutIndex() : connecting atom 1 : "+myPDBAtom.getName()+" atom  2 : "+aPDBAtom.getName());

            theConnections.addVertex(new Line(myPDBAtom.getX1(),myPDBAtom.getY1(),myPDBAtom.getZ1(),
                                     aPDBAtom.getX1(),aPDBAtom.getY1(),aPDBAtom.getZ1()));   
            return true;    
         }
      }  
      return false;
   }   
   public boolean connectHAlone(PDBAtom myPDBAtom)
   {
      String aString = myPDBAtom.getName();
      String aSubString = aString.substring(2,3);
      //char aChar = aString.charAt(3);
      Integer branchInteger = null;
      String aPDBAtomName = null;

      PDBAtom aPDBAtom = null;

      if(aString == null || !( aString.charAt(1) == 'H'  ) )
      {
         return false;
      }
      System.out.println("PDBMolecule.connectHAlone() : attempting to connect : "+myPDBAtom.getName());
      if(aString.trim().equals("H") )
      {
            //Electronegativity.electroNegativityConnect(myPDBAtom,aPDBAtom);
            System.out.println("PDBMolecule.connectHWithoutIndex() : not connecting atom  : "+myPDBAtom.getName());

            //theConnections.addVertex(new Line(myPDBAtom.getX1(),myPDBAtom.getY1(),myPDBAtom.getZ1(),
            //                         aPDBAtom.getX1(),aPDBAtom.getY1(),aPDBAtom.getZ1()));       
         return true;
      }
       
      return false;
   }
   public boolean connectOXT(PDBAtom myPDBAtom)
   {
      String aString = myPDBAtom.getName();
      String aSubString = aString.substring(2,3);
      //char aChar = aString.charAt(3);
      Integer branchInteger = null;
      String aPDBAtomName = null;

      PDBAtom aPDBAtom = null;

      if(aString == null  )
      {
         return false;
      }
      System.out.println("PDBMolecule.connectHWithoutIndex() : attempting to connect : "+myPDBAtom.getName());
      Enumeration anEnumeration = rtrvEnumeration();
      while(anEnumeration.hasMoreElements())
      {
         aPDBAtom = (PDBAtom)anEnumeration.nextElement();  
         aPDBAtomName = aPDBAtom.getName();
         //System.out.println("PDBMolecule.connectHWithoutIndex() : charat 2 : "+myPDBAtom.getName().charAt(2)+" char at 2 : "+aPDBAtom.getName().charAt(2));

         if(aPDBAtomName != null && aPDBAtomName.trim().equals("C") && myPDBAtom.getName().trim().equals("OXT"))
         {
            Electronegativity.electroNegativityConnect(myPDBAtom,aPDBAtom);
            System.out.println("PDBMolecule.connectHWithoutIndex() : connecting atom 1 : "+myPDBAtom.getName()+" atom  2 : "+aPDBAtom.getName());

            theConnections.addVertex(new Line(myPDBAtom.getX1(),myPDBAtom.getY1(),myPDBAtom.getZ1(),
                                     aPDBAtom.getX1(),aPDBAtom.getY1(),aPDBAtom.getZ1())); 
            return true;      
         }
      }  
      return false;
   }   
   public boolean connectPeptide(PDBAtom aPDBAtom)
   {
      if(!(aPDBAtom != null && theLastCarbonPeptide != null && (aPDBAtom.getName().substring(1,2).equals("N") ) ))
      {
         return false;
      }
      System.out.println("connectPeptide() ... attempting to connect atom : "+ aPDBAtom.getName() +"and "+theLastCarbonPeptide.getName());
      if(aPDBAtom != null && theLastCarbonPeptide != null )
      {
         System.out.println("connecting atom 1 : "+aPDBAtom.getName()+" atom  2 : "+theLastCarbonPeptide.getName());
         theConnections.addVertex(new Line(aPDBAtom.getX1(),aPDBAtom.getY1(),aPDBAtom.getZ1(),
                                     theLastCarbonPeptide.getX1(),theLastCarbonPeptide.getY1(),theLastCarbonPeptide.getZ1()));
         Electronegativity.electroNegativityConnect(aPDBAtom,theLastCarbonPeptide);
         DipoleMoment.calculateDipoleMoment(aPDBAtom,theLastCarbonPeptide);
         return true;
      }
      return false;
   }
   public boolean connectToPrevious(PDBAtom aPDBAtom)
   {
    
      if(!(aPDBAtom != null && thePreviousPDBAtom != null && (aPDBAtom.getName().substring(1,2).equals("C") || 
         aPDBAtom.getName().substring(1,2).equals("O") ) ))
      {
         return false;
      }
      System.out.println("connectToPrevious() ... connecting for atom : "+ aPDBAtom.getName() +"and "+thePreviousPDBAtom.getName());
      if(aPDBAtom != null && thePreviousPDBAtom != null )
      {
         System.out.println("PDBMolecule.connectToPrevious():connecting atom 1 : "+aPDBAtom.getName()+" atom  2 : "+thePreviousPDBAtom.getName());

         theConnections.addVertex(new Line(aPDBAtom.getX1(),aPDBAtom.getY1(),aPDBAtom.getZ1(),
                                     thePreviousPDBAtom.getX1(),thePreviousPDBAtom.getY1(),thePreviousPDBAtom.getZ1()));
                                     
         Electronegativity.electroNegativityConnect(aPDBAtom,thePreviousPDBAtom);

         DipoleMoment.calculateDipoleMoment(aPDBAtom,thePreviousPDBAtom);
         return true;
      }
      return false;
   }
   public static boolean startWithDigit(String myValue)
   {
      if(myValue.charAt(0) >= 49 && myValue.charAt(0) <=57)
      {
         return true;
      }
      return false;
   }
   public String getDigits(String myValue)
   {
      String aDigitStr = "";
      int anIndex = 0;
      int aLength = myValue.length();
      while(myValue.charAt(anIndex) >=49 && myValue.charAt(anIndex) <= 57 )
      {
         aDigitStr += ""+ myValue.charAt(anIndex);
         anIndex++;
      }
      return aDigitStr;
   }
   public static String retrieveAtomSymbol(String myValue)
   {
      String aLetterStr = "";
      int anIndex = 0;
      int aLength = myValue.length();
       
      if(startWithDigit(myValue))
      {
         while(myValue.charAt(anIndex) >=49 && myValue.charAt(anIndex) <= 57 )
         {
            anIndex++;
         }
         aLetterStr = ""+myValue.charAt(anIndex);
      }else{
         aLetterStr = ""+myValue.charAt(0)+myValue.charAt(1);         
         aLetterStr = aLetterStr.trim();
      }
      return aLetterStr;
   }   
   public boolean isAlpha(String myValue)
   {
      if(myValue.toLowerCase().endsWith("a"))
      {
         return true;
      }
      return false;      
   }
   public boolean isDigitAndAlpha(String aValue)
   {
      if(startWithDigit(aValue) && isAlpha(aValue))
      {
         return true;
      }
      return false;
   }
   public boolean connectToCA(String aString)
   {
      int atomPosition = 0;
      if(!isDigitAndAlpha(aString))
      {
         return false;
      }
      Atom anAtom = (Atom)getVertexByName(aString);
      Atom aCAAtom = (Atom)getVertexByName("CA");
      System.out.println("PDBMolecule.connectToCA():connecting atom 1 : "+anAtom.getName()+" atom  2 : "+aCAAtom.getName());
      System.out.println("PDBMolecule.connectToCA():connecting atom 1 : "+anAtom+" atom  2 : "+aCAAtom);
      theConnections.addVertex(new Line(anAtom.getX1(),anAtom.getY1(),anAtom.getZ1(),
                                     aCAAtom.getX1(),aCAAtom.getY1(),aCAAtom.getZ1()));
      Electronegativity.electroNegativityConnect(aCAAtom,anAtom);
      return true;
   }
   public boolean connectToAtoms(String aString)
   {
      System.out.println("PDBMolecule.connectToAtoms():connectToAtoms() ... attempting for : aString = "+aString);
      int atomPosition = 0;
      int strLength = aString.length();
      char myChar2 = aString.charAt(strLength-1);
      char myChar1 = aString.charAt(0);
      String cAtom = "C";

      String aSubString1 = "";
      String aSubString2 = "";

      if( aString != null && Character.isDigit(aString.charAt(0)) && Character.isDigit(aString.charAt(3))  )
      {
         aSubString2 = aString.substring(2,4);
      }else if( aString != null && Character.isDigit(aString.charAt(0)) && Character.isLetter(aString.charAt(2))  )
      {
         aSubString2 = ""+aString.charAt(2);
      }else if(aString != null && aString.charAt(0) == 'H')
      {
         aSubString2 = ""+aString.charAt(1);
      }
      Atom aCNSAtom = retrieveCNSAtom(aSubString2);
      Atom anAtom = (Atom)getVertexByName(aString);

      if(aCNSAtom != null && anAtom != null)
      {
         theConnections.addVertex(new Line(anAtom.getX1(),anAtom.getY1(),anAtom.getZ1(),
                                     aCNSAtom.getX1(),aCNSAtom.getY1(),aCNSAtom.getZ1()));
         Electronegativity.electroNegativityConnect(aCNSAtom,anAtom);
         return true;
      }else{
         return false;
      }
   }
   public Atom retrieveAminoAtom(String mySubString,MoleculeModel aPDBMolecule)
   {
 
      PDBAtom aPDBAtom = null;
      Enumeration anEnumeration = aPDBMolecule.rtrvEnumeration();
 
      while(anEnumeration.hasMoreElements())
      {
         aPDBAtom = (PDBAtom) anEnumeration.nextElement();
      }
      if(mySubString == null || mySubString.trim().equals(""))
      {
         return null;
      }
      Atom anAtom = null;
      String CNSAtoms[] = {"C","N","S","O"};
      String anAtomStr = "";
      for(int anIndex = 0;anIndex<CNSAtoms.length;anIndex++)
      {
         anAtomStr = CNSAtoms[anIndex]+mySubString;
         anAtom = (Atom)getVertexByName(anAtomStr);
         //System.out.println("finding atom = "+anAtomStr);
         if( anAtom != null)
         {
            return anAtom;
         }        
      }
      return null;
   }
   public Atom retrieveCNSAtom(String mySubString)
   {
 
      if(mySubString == null || mySubString.trim().equals(""))
      {
         return null;
      }
      Atom anAtom = null;
      String CNSAtoms[] = {"C","N","S","O"};
      String anAtomStr = "";
      for(int anIndex = 0;anIndex<CNSAtoms.length;anIndex++)
      {
         anAtomStr = CNSAtoms[anIndex]+mySubString;
         anAtom = (Atom)getVertexByName(anAtomStr);
         //System.out.println("finding atom = "+anAtomStr);
         if( anAtom != null)
         {
            return anAtom;
         }        
      }
      return null;
   }
   public boolean connectToFirstAtom(String aStrVal)//example 1H etc.
   {
      PDBAtom aPDBAtom = null;
      String previousAtom = null;
      String currentAtom = null;
      System.out.println("PDBMolecule.connectToFirstAtom(): ... attempting for atom : "+ aStrVal);
      if(!(startWithDigit(aStrVal) && aStrVal.length()==2))
      {
         return false;
      }
      Enumeration anEnumeration = rtrvEnumeration();
      if(anEnumeration.hasMoreElements())
      {
         Atom anAtom = (Atom)getVertexByName(aStrVal);
         aPDBAtom = (PDBAtom)anEnumeration.nextElement();  
         currentAtom = aPDBAtom.getName();
         Electronegativity.electroNegativityConnect(anAtom,aPDBAtom);
         System.out.println("connecting atom 1 : "+anAtom.getName()+" atom  2 : "+aPDBAtom.getName());
         System.out.println("connecting atom 1 : "+anAtom +" atom  2 : "+aPDBAtom);
         theConnections.addVertex(new Line(anAtom.getX1(),anAtom.getY1(),anAtom.getZ1(),
                                     aPDBAtom.getX1(),aPDBAtom.getY1(),aPDBAtom.getZ1()));
      }
      return true;
   }
   public boolean isTwoOrMoreAtoms(String myValue)
   {
      if(myValue.length() >= 2)
      {
         return true;
      }
      return false;
   }
   public void topologicalAction(Vertex anAtom)
   {
      //System.out.println("PDBMolecule.topologicalAction(...) atom name = "+anAtom.getName());
/*
      int aTopologicalState = getTopologicalState();

      System.out.println("PDBMolecule.topologicalAction() : topologicalState = "+aTopologicalState );
      if(aTopologicalState == 0)
      {
         //DipoleMoment.calculateDipoleMoment((PDBAtom)anAtom);
      }else if(aTopologicalState == 1){
         //moleculePolarization.calculatePolarization((Atom)anAtom);
      }
 */
   }
   public static MeshShp rtrvShape(DmnsnVar myShpDmnsnVar)
   {
      return rtrvShape(myShpDmnsnVar,null);
   }
   public static MeshShp rtrvShape(DmnsnVar myShpDmnsnVar,MtrlVar myMtrlVar)
   {
      DmnsnVar shpDmnsnVar = myShpDmnsnVar;
      MoleculeModel aPDBMolecule = PDBMoleculeFile.getPDBMolecule();
      Enumeration anEnumeration1 = aPDBMolecule.rtrvEnumeration();
      MoleculeModel aPDBMolecule2 = (MoleculeModel)anEnumeration1.nextElement();
      Enumeration anEnumeration2 = aPDBMolecule2.rtrvEnumeration();

      double aDiameter = 4*aPDBMolecule2.getRadius();

      double xDiff = aPDBMolecule2.cmptWidth()+aDiameter;
      double yDiff = aPDBMolecule2.cmptHeight()+aDiameter;
      double zDiff = aPDBMolecule2.cmptLength()+aDiameter;
      System.out.println("MoleculeModel: width = "+aPDBMolecule2.cmptWidth()+", height = "+aPDBMolecule2.cmptHeight()+", length = "+aPDBMolecule2.cmptLength());
      //HelixVar aHelixVar = new HelixVar(xDiff,yDiff,zDiff,1.0,1.0,1.0,0,0,0);
      DmnsnVar aDmnsnVar = aPDBMolecule2.rtrvDmnsnVar(shpDmnsnVar.getCrdntTpVal());
      //TODO: the function should return a dmnsnVar with all information updated
      
      shpDmnsnVar.setMinDmnsn1Val(aDmnsnVar.getMinDmnsn1Val());
      shpDmnsnVar.setMinDmnsn2Val(aDmnsnVar.getMinDmnsn2Val());
      shpDmnsnVar.setMinDmnsn3Val(aDmnsnVar.getMinDmnsn3Val());
      shpDmnsnVar.setMaxDmnsn1Val(aDmnsnVar.getMaxDmnsn1Val());
      shpDmnsnVar.setMaxDmnsn2Val(aDmnsnVar.getMaxDmnsn2Val());
      shpDmnsnVar.setMaxDmnsn3Val(aDmnsnVar.getMaxDmnsn3Val());
         System.out.println("ShpBndry: min x = "+shpDmnsnVar.getMinDmnsn1Val()+",min y = "+shpDmnsnVar.getMinDmnsn2Val()+", min z = "+shpDmnsnVar.getMinDmnsn3Val());
         System.out.println("ShpBndry: max x = "+shpDmnsnVar.getMaxDmnsn1Val()+",max y = "+shpDmnsnVar.getMaxDmnsn2Val()+", max z = "+shpDmnsnVar.getMaxDmnsn3Val());         

      //aDmnsnVar.setMaxDmnsn1Val(aDmnsnVar.getMaxDmnsn1Val());
      //shpDmnsnVar.setMaxDmnsn2Val(aDmnsnVar.getMaxDmnsn2Val());
      //shpDmnsnVar.setMaxDmnsn3Val(aDmnsnVar.getMaxDmnsn3Val());
      double aCenter1[] = aPDBMolecule2.cmptCenter();
      double aCenter2[] = aDmnsnVar.cmptCenter();

      float xDistance = (float)(aCenter1[0]-aCenter2[0]);
      float yDistance = (float)(aCenter1[1]-aCenter2[1]);
      float zDistance = (float)(aCenter1[2]-aCenter2[2]);

      Trnsfrm aTrnsfrm = new Trnsfrm();
      aTrnsfrm.translate(xDistance, yDistance, zDistance);
      System.out.println("PDBMolecule: xdistance = "+xDistance+",yDistance="+yDistance+",zDistance="+zDistance);
      MeshShp crdntShp = new MeshShp(shpDmnsnVar,myMtrlVar);

      crdntShp.setTransform(aTrnsfrm);
      return crdntShp;
   }
   /*
   public Point createDataPoint()
   {
      Point aPoint1 = null;    
      Point aPoint2 = null;   
      PDBMolecule aPDBMolecule1 = PDBMoleculeFile.getPDBMolecule();
      PDBMolecule aPDBMolecule2 = (PDBMolecule)aPDBMolecule1.getVertexByIndex(0);
      Molecule aMolecule = new Molecule();
      Vector aVector = aPDBMolecule2.getAdjacentVertices();
      int aSize = aVector.size();
      System.out.println("PDBMolecule : createDataPoint()");

      for(int i=0;i<aSize;i++)
      {
         aPoint1 = (Point)aVector.elementAt(i);
         aPoint2 = new Point();
         aPoint2.setX1(aPoint1.getX1()); 
         aPoint2.setY1(aPoint1.getY1());
         aPoint2.setZ1(aPoint1.getZ1());
         aMolecule.addVertex(aPoint2);
      }
      return aMolecule;
   }*/

   /*
   public void displayVolumeMesh(Canvas3DUniverse myCanvas3DUniverse)
   {
      ShapeDisplay aShapeDisplay = new ShapeDisplay();
      int aSize = numberOfVertices();
      System.out.println("PDBMolecule : aSize = "+aSize);
      for(int i=0;i<aSize;i++)
      {
         Point aPoint = (Point)getVertexByIndex(i);
         displayVolumeMesh(aPoint,aShapeDisplay,myCanvas3DUniverse); 
      } 
   }
   public void displayVolumeMesh(Point myPoint,ShapeDisplay myShapeDisplay,Canvas3DUniverse myCanvas3DUniverse)
   {
      System.out.println("PDBMolecule ... point = "+myPoint);
      MeshShape aMeshShape = new MeshShape();
      aMeshShape.setBoundaryShape(new Boundary(new SphereShape(3,2*Math.PI,2*Math.PI)));      
      //aMeshShape.setBoundaryShape(new Boundary(new BoxShape(7,4,4,-3,-2f,-2f)));      
      //Boundary aBoundary = new Boundary(new BoxShape(3,1,1,2.0f,2f,2f));
      //aBoundary.setInvtBndryDcsn(true);
      //aMeshShape.insertBoundaries(aBoundary);
      Point aPoint1 = aMeshShape.createInitCoordinates(myPoint);
      Point aPoint2 = aMeshShape.createCoordinateMesh();

      //TransformGroup aTransformGroup = theCanvas3DUniverse.retrieveTransformGroup();
      //BranchGroup aBranchGroup = aShapeDisplay.createMaxMesh(aPoint2,aTransformGroup);
      //aShapeDisplay.createMaxMesh(aPoint2,aTransformGroup);
      myShapeDisplay.maxMeshAnimate(aPoint2,myCanvas3DUniverse.retrieveTransformGroup());

      //theCanvas3DUniverse.updateScene(aBranchGroup);
   } */  
}