package cgtjr.academics.math.graph;


public interface NdTrvrslActn
{
   public void action1();
   public void action2(Vertex myVertex,int myActnNmbr);
   public void action3(Vertex myVertex1,Vertex myVertex2,int myActnNmbr);
   public void action4(Vertex myVertex1,Vertex myVertex2,int myActnNmbr);//Vertex is unvisited nodes
   public void action5();
}