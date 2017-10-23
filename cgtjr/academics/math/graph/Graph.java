package cgtjr.academics.math.graph;

import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.crdntepnts.PntTool;
import java.util.*;

public class Graph extends Vertex
{
   Vertex graphVertices;
   NdTrvrslActn nodeTrvrslActn;
   private int defaultColor = 0x00bdbdbd;   
   private int color = defaultColor;
   //private float colorC1Flt;
   private int textColor;
   private String imageName;
   
   public Graph()
   {
      graphVertices = new Vertex();
   }
   public Graph(String aName)
   {
      super(aName);
      graphVertices = new Vertex();
   }
   public Graph(int numberOfVertices)
   { 
      graphVertices = new Vertex();
   }
   public void resetColor()
   {
       //color = defaultColor;
       color = 0x00bdbdbd;
   }
   public void setColor(int aColor)
   {
      color = aColor;
   }
   public int getColor()
   {
      return color;
   }
   public void setTextColor(int aTextColor)
   {
      textColor = aTextColor;
   }
   public int getTextColor()
   {
      return textColor;
   }
   public String getImageName()
   {
      return imageName;
   }
   public void setImageName(String anImageName)
   {
      imageName = anImageName;
   }
   public void setNodeTrvrslActn(NdTrvrslActn myNodeTrvrslActn)
   {
      nodeTrvrslActn = myNodeTrvrslActn;
   }
   public int getTreeDepth()
   {
      return 0;
   }
   public void addToGraph(Vertex aVertex)
   {
      graphVertices.pointToVertex(aVertex);
   }
   public void vertexEnumerationOuterAction(Vertex myVertex1)
   {
   }
   public void vertexEnumerationInnerAction(Vertex myVertex1)
   {
   }
   public void vertexEnumerationInnerAction(Vertex myVertex1,Vertex myVertex2)
   {
   }
   public void topologicalAction(Vertex aVertex)
   {
   }   
   public String enumerationAction()
   {
      return "";
   }
   public void connectVertices(Vertex myVertex1,Vertex myVertex2)
   {
      myVertex1.pointToVertex(myVertex2);
      myVertex2.pointToVertex(myVertex1);
   }  
   public void topologicalWalk()
   {
      int numberOfNodes = getAdjcntVertices().size(); 
      //System.out.println("Graph.topologicalWalk() : size = "+numberOfNodes );
      Vertex myVertex = findUnvisitedVertex();
      while(myVertex != null)
      {
         //System.out.println("Graph.topologicalWalk() ... myVertex = "+myVertex.getName()+", indegree = "+myVertex.getInDegree());
         topologicalAction(myVertex);
         myVertex.topologicalAction();
         myVertex.dcrsAdjcntInDegree();
         myVertex = findUnvisitedVertex();         
      }
   }
   /** This code could be implemented later !!!!
   public boolean topologicalStep(in aValue)
   {
      int numberOfNodes = getAdjacentVertices().size(); 
      //System.out.println("Graph.topologicalWalk() : size = "+numberOfNodes );
      Vertex myVertex = findUnvisitedVertex(aValue);
      while(myVertex != null)
      {
         System.out.println("Graph.topologicalWalk() ... test 1");
         topologicalAction(myVertex);
         myVertex.topologicalAction();
         myVertex.decreaseAdjacentInDegree();
         myVertex = findUnvisitedVertex(aValue);     
             
      }
   }   
   public void topologicalRun(int aValue)
   {
      if(unvisitedNodesExist())
      {
         topologicalStep(aValue);
      }
   }
   public boolean unvisitedNodesExist()
   {
      boolean found = false;

      Enumeration myEnumeration = getAdjacentVertices().elements();

      while(myEnumeration.hasMoreElements())
      {
         Vertex myVertex = (Vertex) myEnumeration.nextElement();
         if(myVertex.getIsVisited() == false)
         {
            return true;
         }
      }       
      return false;
   }**/
   
   public Vertex findUnvisitedVertex(int myInDegree)
   {
      boolean found = false;

      Enumeration myEnumeration  = getAdjcntVertices().elements();

      while(myEnumeration.hasMoreElements())
      {
         Vertex myVertex = (Vertex) myEnumeration.nextElement();
         if(myVertex.getInDegree() == myInDegree && myVertex.getVisited() == false)
         {
            System.out.println("Found vertex : " + myVertex.getName()+", indegree = "+myVertex.getInDegree());
            myVertex.setVisited(true);
            found = true;
            return myVertex;
         }
      } 
      return null;
   }
   /*
   public Vertex findUnvisitedVertex(int myInDegree)
   {
      boolean found = false;

      Enumeration myEnumeration  = getAdjacentVertices().elements();

      while(myEnumeration.hasMoreElements())
      {
         Vertex myVertex = (Vertex) myEnumeration.nextElement();
         if(myVertex.getInDegree() == myInDegree && myVertex.getIsVisited() == false)
         {
            System.out.println("Found "+myInDegree+" in-degree vertex : " + myVertex.getName());
            myVertex.setIsVisited(true);
            found = true;
            return myVertex;
         }
      }  
      if(myEnumeration.hasMoreElements())
      {
         return findUnvisitedVertex (myIndegree+1);
      }
      return findUnvisitedVertex(myIn;
   }*/
   public String breadthFirstSearch()
   {
      return breadthFirstSearch(this);
   }
   public void breadthDepthSearchAction(Vertex myVertex1,Vertex myVertex2)
   {
      
   }
   public Vertex breadthFirstSearch(Vertex aVertex,String aVertexName)
   {
      String output = "";
      /*
      if(aVertex.getIsVisited() == true)
      {
         return "";
      }else{
         aVertex.setIsVisited(true);
      }*/
      //output += aVertex.enumerationAction();

      Vector myVector = aVertex.getAdjcntVertices();

      System.out.println("Performing breadth first search for node : "+aVertex.getName());
      System.out.println("Adjacency list size = "+myVector.size());

      Enumeration myEnumeration = myVector.elements();
      while(myEnumeration.hasMoreElements())
      {
         Vertex myVertex = (Vertex) myEnumeration.nextElement();
         if(myVertex.getName().equals(aVertexName))
         {
            return myVertex;
         }
      }
      myEnumeration = myVector.elements();
      while(myEnumeration.hasMoreElements())
      {
         Vertex myVertex = (Vertex) myEnumeration.nextElement();
         if(myVertex.getName().equals(aVertexName))
         {
            return breadthFirstSearch(myVertex,aVertexName);
         }
      }
      return null;
   }

   public String breadthFirstSearch(Vertex aVertex)
   {
      String output = "";
      System.out.println("Graph.breadthFirstSearch : visited = "+aVertex.getVisited());
      /*
      if(aVertex.getIsVisited() == true)
      {
         return "";
      }else{
         aVertex.setIsVisited(true);
      }*/
      if(aVertex.getVisited()==false)
      {
         output += aVertex.enumerationAction();
         aVertex.setVisited(true);        
      }

      Vector myVector = aVertex.getAdjcntVertices();

      System.out.println("Graph.breadthFirstSearch(...) : Performing breadth first search for node : "+aVertex.getName());
      System.out.println("Graph.breadthFirstSearch(...) : Adjacency list size = "+myVector.size());

      Enumeration myEnumeration = myVector.elements();
      while(myEnumeration.hasMoreElements())
      {
         Vertex myVertex = (Vertex) myEnumeration.nextElement();
         if(myVertex.getVisited()==false)
         {
            output += myVertex.enumerationAction();
            myVertex.setVisited(true);
            breadthDepthSearchAction(aVertex,myVertex);
         }
      }
      myEnumeration = myVector.elements();
      while(myEnumeration.hasMoreElements())
      {
         Vertex myVertex = (Vertex) myEnumeration.nextElement();
         if(myVertex.getVisited()==false)
         {
            output += breadthFirstSearch(myVertex);
         }
      }
      return output;
   }
   public boolean breadthFirstSearch(Vertex aVertex1,Vertex aVertex2,int aDepth,int aCount)
   {
      boolean foundVertex = false;

      if(aCount >= aDepth){
         return false;
      }
      //System.out.println("search node: "+aVertex1.getName()+" visit = "+aVertex1.getIsVisited() );
      //System.out.println("search for: "+aVertex2.getName()+" visit = "+aVertex2.getIsVisited() );
      if(aVertex1.getVisited() == true )
      {
         return false;
      }
      Vector myVector = aVertex1.getAdjcntVertices();
      Enumeration myEnumeration = myVector.elements();
      while(myEnumeration.hasMoreElements())
      {
         Vertex myVertex = (Vertex) myEnumeration.nextElement();    
         //System.out.println("parent : "+aVertex1.getName()+" and child :"+myVertex.getName() +"child visit : "+myVertex.getIsVisited()+" compared with :"+aVertex2.getName()+" at aCount = "+aCount);
         if(myVertex.getName().equals(aVertex2.getName() ) )
         {
            foundVertex = true;
            return foundVertex;
         }
      }
      aCount++;
      myEnumeration = myVector.elements();
      while(myEnumeration.hasMoreElements())
      {
         Vertex myVertex = (Vertex) myEnumeration.nextElement();    
         //System.out.println("searching on child "+myVertex.getName());        
         foundVertex = (foundVertex || breadthFirstSearch(myVertex,aVertex2,aDepth,aCount) );
         if(foundVertex == true)
         {
            System.out.println("found it!!!");
            return foundVertex;
         }
      }
      return foundVertex;
   }
   /*
   public boolean brdth1stSrchByDstnce(Vertex aVertex1,Vertex aVertex2,int aDepth,int aCount)
   {
      boolean foundVertex = false;
      double distance = 10;
      double dltaDstnce = 2;

      
      if(aCount >= aDepth){
         return false;
      }
      //System.out.println("search node: "+aVertex1.getName()+" visit = "+aVertex1.getIsVisited() );
      //System.out.println("search for: "+aVertex2.getName()+" visit = "+aVertex2.getIsVisited() );
      if(aVertex1.getVisited() == true )
      {
         return false;
      }
      Vector myVector = aVertex1.getAdjcntVertices();
      Enumeration myEnumeration = myVector.elements();
      while(myEnumeration.hasMoreElements())
      {
         Vertex myVertex = (Vertex) myEnumeration.nextElement();    
         //System.out.println("parent : "+aVertex1.getName()+" and child :"+myVertex.getName() +"child visit : "+myVertex.getIsVisited()+" compared with :"+aVertex2.getName()+" at aCount = "+aCount);
         if(PntTool.getDistance((Pnt)aVertex1,(Pnt)aVertex2) <= distance+dltaDstnce && 
            PntTool.getDistance((Pnt)aVertex1,(Pnt)aVertex2) >= distance-dltaDstnce  )
         {
            foundVertex = true;
            return foundVertex;
         }
      }
      aCount++;
      myEnumeration = myVector.elements();
      while(myEnumeration.hasMoreElements())
      {
         Vertex myVertex = (Vertex) myEnumeration.nextElement();    
         //System.out.println("searching on child "+myVertex.getName());        
         foundVertex = (foundVertex || breadthFirstSearch(myVertex,aVertex2,aDepth,aCount) );
         if(foundVertex == true)
         {
            System.out.println("found it!!!");
            return foundVertex;
         }
      }
      return foundVertex;
   } */  
   public void depthBreadthSearch(Vertex aVertex)
   {
      String output = "";
      System.out.println("Graph.breadthFirstSearch : visited = "+aVertex.getVisited());
      if(aVertex.getVisited()==false)
      {
         depthBreadthAction(aVertex);
         aVertex.setVisited(true);        
      }
      Vector myVector = aVertex.getAdjcntVertices();
      //System.out.println("Graph.breadthFirstSearch(...) : Performing breadth depth search for node : "+aVertex.getName());
      //System.out.println("Graph.breadthFirstSearch(...) : Adjacency list size = "+myVector.size());
      Enumeration myEnumeration = myVector.elements();
      while(myEnumeration.hasMoreElements())
      {
         Vertex myVertex = (Vertex) myEnumeration.nextElement();
         if(myVertex.getVisited()==false)
         {
            breadthFirstSearch(myVertex);
         }
      }
   }
   public void depthBreadthAction(Vertex myVertex)
   {

   }
   public Vertex findUnvisitedVertex()
   {
      return findUnvisitedVertex(1);
   }
   public Vertex findParent(Vertex aVertex)
   {
      return findParent(this,aVertex,Integer.MAX_VALUE);
   }
   public Vertex findParent(Vertex aVertex,int numberOfHops)
   {
      return findParent(this,aVertex,numberOfHops);
   }
   public Vertex findParent(Vertex aVertex1,Vertex aVertex2,int aNumberOfHops)
   {
      
      if(aNumberOfHops <=0)
      {
         return null;
      }

      if(aVertex1.getVisited() == true)
      {
         return null;
      }else{
         //aVertex1.setVisited(true);
      }


      Vector myVector = aVertex1.getAdjcntVertices();

      //System.out.println("Graph : Parent - "+aVertex1.getName()+" visited = "+aVertex1.getIsVisited()+", looking for child "+aVertex2.getName() );
      //System.out.println("Adjacency list size = "+myVector.size());

      Enumeration myEnumeration = myVector.elements();
      while(myEnumeration.hasMoreElements())
      {
         Vertex myVertex1 = (Vertex) myEnumeration.nextElement();
         //System.out.println("Parent name : "+aVertex1.getName()+" childrens "+myVertex1.getName() );         
         //myVertex.takeAction();
         if(myVertex1.getName().equals(aVertex2.getName()) )
         {
            //System.out.println("Found parent : "+aVertex1.getName()+"coord data : "+aVertex1.getDataObject()+", with child named :"+myVertex1.getName());
            return aVertex1;
         }
      }
      aNumberOfHops--;
      myEnumeration = myVector.elements();
      Vertex myVertex3 = null;
      while(myEnumeration.hasMoreElements() )
      {
         Vertex myVertex2 = (Vertex) myEnumeration.nextElement();
         if(myVertex2.getVisited() == false && !myVertex2.getName().equals(this.getName()))
         {
            myVertex3 = findParent(myVertex2,aVertex2,aNumberOfHops);
         }
         if(myVertex3 != null)
         {
            return myVertex3; 
         }        
      }
      return null;
   }
   public int strtNodeTrvrsl()
   {
      nodeTrvrslActn.action1();
      int anInt = traverseNode();
      nodeTrvrslActn.action5();
      return anInt;
   }
   public int traverseNode()
   {
      return traverseNode(0);
   }
   public int traverseNode(int myStartDepth)
   {
      return traverseNode(this,0,myStartDepth,Integer.MAX_VALUE);
   }
   public int traverseNode(int myDepthCounter,int myStartDepth)
   {
      return traverseNode(this,myDepthCounter,myStartDepth,Integer.MAX_VALUE);
   }
   public int traverseNode(Vertex myVertex1,int myDepthCounter,int myStartDepth)
   {
      return traverseNode(myVertex1,myDepthCounter,myStartDepth,Integer.MAX_VALUE);
   }
   public int traverseNode(Vertex myVertex1,int myDepthCounter,int myStartDepth,int myStopDepth)
   {
      int nodeCounter = 0;
      int totalCount = 0;
      Enumeration anEnumeration = null;
      Vertex aVertex = null;

      //System.out.println("Graph: myDepthCounter = "+myDepthCounter+", myStartDepth = "+myStartDepth+", myStopDepth = "+myStopDepth);
      myVertex1.setVisited(true);

      if(myDepthCounter >= myStartDepth && myDepthCounter <= myStopDepth)
      {
         nodeTrvrslActn.action2(myVertex1,0);
      }
      if(myDepthCounter < myStopDepth)
      {
         anEnumeration = myVertex1.rtrvEnumeration();
         myDepthCounter++;
         while(anEnumeration.hasMoreElements())
         {
            aVertex = (Vertex) anEnumeration.nextElement();
            /*
            System.out.println("Graph : recursive call for Vertex = "+aVertex+" size = "+aVertex.numberOfVertices()+", visited state = "+aVertex.getIsVisited()+", depth = "+myDepthCounter);
            System.out.println("Graph : myVertex1.getName() = "+myVertex1.getName()+", aVertex.getName() = "+aVertex.getName());

            System.out.println("Graph : myVertex1.getIsVisited() = "+myVertex1.getIsVisited()+", aVertex.getIsVisited() = "+aVertex.getIsVisited());
            System.out.println("Graph : myVertex1.getDepth() = "+myVertex1.getDepth()+", aVertex.getDepth()"+aVertex.getDepth());

            */
            //System.out.println("Graph : myVertex1.getItrtnNmbr() = "+myVertex1.getItrtnNmbr()+", aVertex.getItrtnNmbr() = "+aVertex.getItrtnNmbr());
            //if(myVertex1.getItrtnNmbr() >= aVertex.getItrtnNmbr())
            //{
               nodeTrvrslActn.action3(myVertex1,aVertex,0);
            //}
            //aVertex.getDepth() >= myVertex1.getDepth() ... may need changing ... may start at lower depth?
            //if(aVertex.getIsVisited() == false && aVertex.getDepth() >= myVertex1.getDepth() && aVertex.getItrtnNmbr() <= myVertex1.getItrtnNmbr())
            if(aVertex.getVisited() == false && aVertex.getDepth() >= myVertex1.getDepth() && aVertex.getItrtnNmbr() <= myVertex1.getItrtnNmbr())
            {
               //System.out.println("Graph.traverse() : aVertex = "+aVertex+", DepthCounter = "+myDepthCounter);
               nodeTrvrslActn.action4(myVertex1,aVertex,myDepthCounter);
               traverseNode(aVertex,myDepthCounter,myStartDepth,myStopDepth);
            }
         }
      }
      myVertex1.setItrtnNmbr(myVertex1.getItrtnNmbr()+1);
      myVertex1.setVisited(false);
      return 1;
   }
}