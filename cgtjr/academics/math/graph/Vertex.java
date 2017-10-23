package cgtjr.academics.math.graph;

import java.util.Enumeration;
import java.util.Random;
import java.util.Vector;

public class Vertex {

    private Vector adjcntVertices;
    private Vertex parentNode;
    private String name;
    private int initInDegree;
    private int initOutDegree;
    private int inDegree;
    private int outDegree;
    private String valueVal;
    private double dblVal;
    private double value;
    private Object dataObject;
    private boolean visited;
    private long dateTime;
    private long longData;
    //private boolean isSelected;
    private boolean isRoot;
    private int depth;
    private int treeIndex;
    private int index1;
    private int index2;
    private int index3;
    private int counter;
    private int number;
    private int itrtnNmbr;
    private boolean visibility;
    private boolean stateStts;
    //private boolean isActiveNode = true;
    //private int topologicalState = -1;
    //private Enumeration theEnumeration;
    private static Random myRandom = new Random(System.currentTimeMillis());

    public Vertex() {
        adjcntVertices = new Vector();
        visibility = true;
    }

    public Vertex(String aName) {
        adjcntVertices = new Vector();
        name = aName;
        visibility = true;
    }

    public boolean getActiveState() {
        return stateStts;
    }

    public void setActiveState(boolean myStateStts) {
        stateStts = myStateStts;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long myDateTime) {
        dateTime = myDateTime;
    }

    public void setCounter(int myCounter) {
        counter = myCounter;
    }

    public void setNumber(int myNumber) {
        number = myNumber;
    }

    public int getCounter() {
        return counter;
    }

    public int getNumber() {
        return number;
    }

    public void setVisibility(boolean myVisibility) {
        visibility = myVisibility;
    }

    public boolean getVisibility() {
        return visibility;
    }

    public void setItrtnNmbr(int myItrtnNmbr) {
        itrtnNmbr = myItrtnNmbr;
    }

    public int getItrtnNmbr() {
        return itrtnNmbr;
    }

    public void setIndex1(int myIndex) {
        index1 = myIndex;
    }

    public void setIndex2(int myIndex) {
        index2 = myIndex;
    }

    public void setIndex3(int myIndex) {
        index3 = myIndex;
    }

    public int getIndex1() {
        return index1;
    }

    public int getIndex2() {
        return index2;
    }

    public int getIndex3() {
        return index3;
    }

    public void setTreeIndex(int myTreeIndex) {
        treeIndex = myTreeIndex;
    }

    public int getTreeIndex() {
        return treeIndex;
    }

    public void setDepth(int myDepth) {
        depth = myDepth;
    }

    public int getDepth() {
        return depth;
    }

    public void resetDegreeVisited() {
        Enumeration myEnumeration = rtrvEnumeration();
        Vertex myVertex1 = null;
        Vertex myVertex2 = null;
        while (myEnumeration.hasMoreElements()) {
            myVertex1 = (Vertex) myEnumeration.nextElement();
            myVertex1.setVisited(false);
            //System.out.println("Vertex.resetDegreeVisited() : reseting degree visited "+myVertex1.getName()+",indegree = "+myVertex1.inDegree+",initial="+myVertex1.initialInDegree);
            myVertex1.resetInDegree();

            Enumeration myEnumeration2 = myVertex1.rtrvEnumeration();

            while (myEnumeration2.hasMoreElements()) {
                myVertex2 = (Vertex) myEnumeration2.nextElement();
                if (myVertex2.getVisited() == true) {
                    myVertex2.resetDegreeVisited();
                }
            }
        }
    }

    public void resetInDegree() {
        inDegree = initInDegree;

    }

    public void setInitInDegree(int myInitialInDegree) {
        initInDegree = myInitialInDegree;
    }

    public void resetOutDegree() {
        outDegree = initOutDegree;
    }
    /*
     * public void setTopologicalState(int aState) { topologicalState = aState;
     * } public int getTopologicalState() { return topologicalState;
   }
     */

    public void setIsRoot(boolean whatIsRoot) {
        isRoot = whatIsRoot;
    }

    public boolean getIsRoot() {
        return isRoot;
    }
    /*
     * public void setIsActiveNode(boolean isItActiveNode) { isActiveNode =
     * isItActiveNode; } public boolean getIsActiveNode() { return isActiveNode;
   }
     */

    public void setParentNode(Vertex aVertex1) {
        parentNode = aVertex1;
    }

    public Vertex getParentNode() {
        return parentNode;
    }

    public void setName(String myName) {
        name = myName;
    }

    public String getName() {
        return name;
    }

    public void setValueVal(String myValueVal) {
        valueVal = myValueVal;
    }

    public String getValueVal() {
        return valueVal;
    }

    public void setDblVal(double myDblVal) {
        dblVal = myDblVal;
    }

    public double getDblVal() {
        return dblVal;
    }

    public void setValue(double myValue) {
        //System.out.println("Vertex: "+toString());
        value = myValue;
    }

    public double getValue() {
        return value;
    }

    public void setDataObject(Object myDataObject) {
        dataObject = myDataObject;
    }

    public Object getDataObject() {
        return dataObject;
    }

    public void connectVertices2(Vertex myVertex) {

        pointToVertex(myVertex);
        myVertex.pointToVertex(this);
    }

    public void connectVertices(Vertex myVertex) {
        Vector someAdjacentVertices = myVertex.getAdjcntVertices();
        if (!someAdjacentVertices.contains(this)) {
            pointToVertex(myVertex);
        }
    }

    public void pointToVertex(Vertex myVertex) {
        if (myVertex.getName() == null) {
            /*
             * myVertex.setName(""+myVertex.getClass().getName()+
             * "_"+myVertex.hashCode()+ "_"+getAdjacentVertices().size());
             */
        }
        //System.out.println("Vertex = : "+myVertex.getName());
        //System.out.println("Vertex : name = "+getName()+" & coord = "+this+" pointing to name = "+myVertex.getName()+", coord = "+myVertex);
        if (!adjcntVertices.contains(myVertex)) {
            adjcntVertices.addElement(myVertex);
            ++outDegree;
            ++myVertex.inDegree;
            ++myVertex.initInDegree;
        }
        myVertex.pointAtActn(this);
    }

    public void addVertex2(Vertex myVertex) {
        addVertex1(myVertex);
        myVertex.addVertex1(this);
    }

    public void addVertex1(Vertex myVertex) {
        if (myVertex.getName() == null) {
            /*
             * myVertex.setName(""+myVertex.getClass().getName()+
             * "_"+myVertex.hashCode()+ "_"+getAdjacentVertices().size());
             */
        }
        //System.out.println("Vertex = : "+myVertex.getName());
        //System.out.println("Vertex : name = "+getName()+" & coord = "+this+" pointing to name = "+myVertex.getName()+", coord = "+myVertex);
        if (!adjcntVertices.contains(myVertex)) {
            adjcntVertices.addElement(myVertex);
            ++outDegree;
            ++myVertex.inDegree;
            ++myVertex.initInDegree;
        }
        //myVertex.pointAtActn(this);
    }

    public void pointAtActn(Vertex aVertex) {
        //System.out.println("Vertex.pointAtActn()");
    }

    public void rmvAdjcntVertex(Vertex myVertex) {
        if (adjcntVertices.contains(myVertex)) {
            getAdjcntVertices().removeElement(myVertex);
            --myVertex.inDegree;
            --myVertex.initInDegree;
        }
    }

    public void rmvVertex(Vertex myVertex) {
        if (myVertex == null) {
            return;
        }
        if (adjcntVertices.contains(myVertex)) {
            --myVertex.inDegree;
            --myVertex.initInDegree;
            getAdjcntVertices().removeElement(myVertex);
            //System.out.println("Vertex: remove  = "+myVertex.getName() +", indegree = "+myVertex.getInDegree());
        }
    }

    public void rmvVertex(int myIndex) {
        Vertex aVertex = null;
        if (adjcntVertices.size() >= myIndex + 1) {
            aVertex = (Vertex) adjcntVertices.elementAt(myIndex);
            --aVertex.inDegree;
            --aVertex.initInDegree;
            adjcntVertices.remove(myIndex);
            //System.out.println("Vertex: remove  = "+myVertex.getName() +", indegree = "+myVertex.getInDegree());
        }
    }

    public void rmvAllVertices() {
        adjcntVertices.removeAllElements();
    }

    public void rmvAllVertices(String aName) {
        Enumeration myEnumeration = rtrvEnumeration();

        Vertex myVertex = null;
        while (myEnumeration.hasMoreElements()) {
            myVertex = (Vertex) myEnumeration.nextElement();
            if (myVertex.getName() != null && myVertex.getName().equals(aName)) {
                getAdjcntVertices().removeElement(myVertex);
            }
        }
    }

    public Vector getAdjcntVertices() {
        return adjcntVertices;
    }

    public void setAdjcntVertices(Vector myVector) {
        adjcntVertices = myVector;
    }

    public Enumeration rtrvEnumeration() {
        return adjcntVertices.elements();
    }

    public void enmrtVrtxIntrctn() {
        Vertex myVertex1 = null;
        Vertex myVertex2 = null;
        int mySize1 = nmbrOfVertices();
        for (int i = 0; i < mySize1; i++) {

            try {
                myVertex1 = (Vertex) adjcntVertices.elementAt(i);
            } catch (ArrayIndexOutOfBoundsException aiobe) {
                mySize1 = nmbrOfVertices();
            }
            myVertex1.vertexEnumerationOuterAction();
            for (int j = 0; j < mySize1; j++) {
                try {
                    myVertex2 = (Vertex) adjcntVertices.elementAt(j);
                } catch (ArrayIndexOutOfBoundsException aiobe) {
                    mySize1 = nmbrOfVertices();
                }
                myVertex2.vertexEnumerationInnerAction(myVertex1);
                vertexEnumerationInnerAction(myVertex1, myVertex2);
            }

            vertexEnumerationOuterAction(myVertex1);
        }

    }

    public void enumerateVertexInteraction2() {
        Enumeration myEnumeration = rtrvEnumeration();
        Vertex myVertex1 = null;
        Vertex myVertex2 = null;
        while (myEnumeration.hasMoreElements()) {
            myVertex1 = (Vertex) myEnumeration.nextElement();
            myVertex1.vertexEnumerationOuterAction();
            Enumeration myEnumeration2 = rtrvEnumeration();
            while (myEnumeration2.hasMoreElements()) {
                myVertex2 = (Vertex) myEnumeration2.nextElement();
                vertexEnumerationInnerAction(myVertex1, myVertex2);
                myVertex2.vertexEnumerationInnerAction(myVertex1);
                myVertex2.enumerateVertexInteraction2();
            }
        }
    }

    public void vertexEnumerationOuterAction(Vertex myVertex1) {
    }

    public void vertexEnumerationOuterAction() {
    }

    public void vertexEnumerationInnerAction(Vertex myVertex1) {
    }

    public void vertexEnumerationInnerAction(Vertex myVertex1, Vertex myVertex2) {
    }

    public int nmbrOfVertices() {
        return adjcntVertices.size();
    }

    public Vertex rtrvVertex(int anIndex) {
        return (Vertex) adjcntVertices.elementAt(anIndex);
    }

    public Vertex getVrtxByIndx(int anIndex) {
        Vertex aVertex = (Vertex) adjcntVertices.elementAt(anIndex);
        return aVertex;
    }

    public void enmrtVertices() {
        Vertex myVertex = null;
        int mySize = nmbrOfVertices();
        for (int i = 0; i < mySize; i++) {
            try {
                myVertex = (Vertex) adjcntVertices.elementAt(i);
            } catch (ArrayIndexOutOfBoundsException aiobe) {
                mySize = nmbrOfVertices();
            }
            enumerationAction(myVertex);
        }
    }

    public String enumerationAction(Vertex aVertex1) {
        return "";
    }

    public String enumerationAction() {
        return "";
    }

    public Object getVertexByIndex(int anIndex) {
        Object anObject = null;
        try {
            anObject = adjcntVertices.elementAt(anIndex);
        } catch (ArrayIndexOutOfBoundsException e) {
            anObject = null;
        }
        return anObject;
    }

    public Vertex getVertexByName(String aName) {
        Enumeration myEnumeration = rtrvEnumeration();

        Vertex myVertex = null;
        while (myEnumeration.hasMoreElements()) {
            myVertex = (Vertex) myEnumeration.nextElement();
            if (myVertex.getName() != null && myVertex.getName().equals(aName)) {
                return myVertex;
            }
        }
        return null;
    }

    public Vertex getVertexByName(String aName, String aParentName) {
        Enumeration myEnumeration = rtrvEnumeration();
        Vertex myVertex = null;
        while (myEnumeration.hasMoreElements()) {
            myVertex = (Vertex) myEnumeration.nextElement();
            if (myVertex.getName() != null && myVertex.getParentNode() != null
                    && myVertex.getParentNode().getName() != null
                    && myVertex.getParentNode().getName().equals(aParentName)
                    && myVertex.getName().equals(aName)) {
                return myVertex;
            }
        }

        return null;
    }

    public static int rtrvRandomNmbr(int number1, int number2) {
        int randomNumber = myRandom.nextInt();
        if (randomNumber < 0) {
            randomNumber = -randomNumber;
        }
        return number1 + (randomNumber % number2);
    }

    public void setVisited(boolean aVisit) {
        visited = aVisit;
    }

    public boolean getVisited() {
        return visited;
    }

    public void addUniqueVertex(Vertex myVertex) {
        boolean contains = adjcntVertices.contains(myVertex);
        if (contains == false) {
            pointToVertex(myVertex);
        }
    }

    public void addVertex(Vertex myVertex) {
        pointToVertex(myVertex);
    }

    public void insertVertex(Vertex myVertex, int aNumber) {
        adjcntVertices.insertElementAt(myVertex, aNumber);
    }

    public void addVertexAt(Vertex myVertex, int aNumber) {
        adjcntVertices.add(aNumber, myVertex);
    }

    public void setVertex(Vertex myVertex, int aNumber) {
        adjcntVertices.set(aNumber, myVertex);
    }

    public Vertex getNewVertex() {
        return new Vertex();
    }

    public void dcrsAdjcntInDegree() {
        Enumeration adjacentVertexEnum = getAdjcntVertices().elements();

        while (adjacentVertexEnum.hasMoreElements()) {
            Vertex myAdjVertex = (Vertex) adjacentVertexEnum.nextElement();
            --myAdjVertex.inDegree;
            //System.out.println("Vertex : "+name+" is decreasing inDegree for vertex : "
            //                   +myAdjVertex.getName()+"=(in degree="+myAdjVertex.inDegree+")");
        }
    }

    public void incrsAdjcntInDegree() {
        Enumeration adjacentVertexEnum = getAdjcntVertices().elements();

        while (adjacentVertexEnum.hasMoreElements()) {
            Vertex myAdjVertex = (Vertex) adjacentVertexEnum.nextElement();
            ++myAdjVertex.inDegree;
            System.out.println("Vertex : remove this ... !!!");
            ++myAdjVertex.initInDegree;
        }
    }

    public void visitAll(boolean hadAVisit) {
        Enumeration adjacentVertexEnum = getAdjcntVertices().elements();

        while (adjacentVertexEnum.hasMoreElements()) {
            Vertex myAdjVertex = (Vertex) adjacentVertexEnum.nextElement();
            myAdjVertex.setVisited(hadAVisit);
        }
    }

    public void addAllVertices(Vertex myVertex) {
        String output = "";
        Vertex aVertex = null;
        //System.out.println("Vertex.addAllVertices: visited = "+myPoint.getIsVisited());
        if (myVertex.getVisited() == false) {
            myVertex.setVisited(true);
            addVertex(myVertex);
        }
        Vector aVector = myVertex.getAdjcntVertices();
        //System.out.println("Vertex.addAllVertices(...) : Point = "+myPoint.getName());
        Enumeration myEnumeration = aVector.elements();
        while (myEnumeration.hasMoreElements()) {
            aVertex = (Vertex) myEnumeration.nextElement();
            if (aVertex.getVisited() == false) {
                addAllVertices(aVertex);
            }
        }
    }

    public Vertex getVertexByIndices(Vertex myVertex) {
        //System.out.println("Vertex : checking if name = "+myVertex.getName()+" coord = "+myVertex +" == "+this.getName()+" coord = "+this);      
        if (myVertex.getIndex1() == getIndex1() && myVertex.getIndex2() == getIndex2() && myVertex.getIndex3() == getIndex3()) {
            return this;
        }
        Vertex aVertex = null;
        Enumeration myEnumeration = rtrvEnumeration();
        if (myEnumeration.hasMoreElements()) {
            aVertex = (Vertex) myEnumeration.nextElement();
            if (myVertex.getIndex1() == aVertex.getIndex1() && myVertex.getIndex2() == aVertex.getIndex2() && myVertex.getIndex3() == aVertex.getIndex3()) {
                return aVertex;
            }
        }
        if (myEnumeration.hasMoreElements()) {
            aVertex = (Vertex) myEnumeration.nextElement();
            if (myVertex.getIndex1() == aVertex.getIndex1() && myVertex.getIndex2() == aVertex.getIndex2() && myVertex.getIndex3() == aVertex.getIndex3()) {
                return aVertex;
            }
        }
        if (myEnumeration.hasMoreElements()) {
            aVertex = (Vertex) myEnumeration.nextElement();
            if (myVertex.getIndex1() == aVertex.getIndex1() && myVertex.getIndex2() == aVertex.getIndex2() && myVertex.getIndex3() == aVertex.getIndex3()) {
                return aVertex;
            }
        }
        if (myEnumeration.hasMoreElements()) {
            aVertex = (Vertex) myEnumeration.nextElement();
            if (myVertex.getIndex1() == aVertex.getIndex1() && myVertex.getIndex2() == aVertex.getIndex2() && myVertex.getIndex3() == aVertex.getIndex3()) {
                return aVertex;
            }
        }
        if (myEnumeration.hasMoreElements()) {
            aVertex = (Vertex) myEnumeration.nextElement();
            if (myVertex.getIndex1() == aVertex.getIndex1() && myVertex.getIndex2() == aVertex.getIndex2() && myVertex.getIndex3() == aVertex.getIndex3()) {
                return aVertex;
            }
        }
        if (myEnumeration.hasMoreElements()) {
            aVertex = (Vertex) myEnumeration.nextElement();
            if (myVertex.getIndex1() == aVertex.getIndex1() && myVertex.getIndex2() == aVertex.getIndex2() && myVertex.getIndex3() == aVertex.getIndex3()) {
                return aVertex;
            }
        }
        return null;
    }
    /*
     * public Vertex getVertexByIndices(Vertex myVertex) {
     * //System.out.println("Vertex : checking if name = "+myVertex.getName()+"
     * coord = "+myVertex +" == "+this.getName()+" coord = "+this);
     * if(myVertex.getIndex1() == getIndex1() && myVertex.getIndex2() ==
     * getIndex2() && myVertex.getIndex3() == getIndex3()) { return this; }
     * Enumeration myEnumeration = retrieveEnumeration();
     *
     * Vertex aVertex = null;
     *
     * while(myEnumeration.hasMoreElements()) { aVertex = (Vertex)
     * myEnumeration.nextElement(); //System.out.println("Shape : checking if
     * "+myVertex +" == "+aVertex ); if(myVertex.getIndex1() ==
     * aVertex.getIndex1() && myVertex.getIndex2() == aVertex.getIndex2() &&
     * myVertex.getIndex3() == aVertex.getIndex3()) { return aVertex; } } return
     * null; }
     */
    /*
     * public Vertex findUnreachableVertex(Vertex aVertex,int numberOfHops) {
     * Vector myVector = this.getAdjacentVertices(); //setIsVisited(true);
     * Enumeration myEnumeration = myVector.elements();
     * while(myEnumeration.hasMoreElements()) { Vertex myVertex1 = (Vertex)
     * myEnumeration.nextElement(); if(
     * !myVertex1.getName().equals(aVertex.getName()) &&
     * myVertex1.getCoordinated()== false ) { //System.out.println("beginning
     * unreachable search on "+myVertex1.getName());
     * if(breadthFirstSearch(aVertex,myVertex1,numberOfHops,0) == false) {
     * //System.out.println("Found unreachable vertex : " + myVertex1
     * .getName()); myVertex1.setCoordinated(true); return myVertex1; } } }
     * return null;
   }
     */

    public int getInDegree() {
        return inDegree;
    }

    public int getOutDegree() {
        return outDegree;
    }

    public void topologicalAction() {
    }
}