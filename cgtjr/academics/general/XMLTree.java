package cgtjr.academics.general;


import java.io.File;
import java.io.IOException;
import org.w3c.dom.Document;
//import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// Basic GUI components
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JComponent;

// GUI components for right-hand side
import javax.swing.JSplitPane;
import javax.swing.JEditorPane;

// GUI support classes
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

// For creating borders
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;

// For creating a TreeModel
import javax.swing.tree.*;
import javax.swing.event.*;
//import java.util.*;

import java.io.IOException;
import org.w3c.dom.Document;
//import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XMLTree extends JPanel {
    // Global value so it can be ref'd by the tree-adapter
    static Document document;
    static final int windowHeight = 300;
    static final int leftWidth = 240;
    static final int rightWidth = 340;
    static final int windowWidth = leftWidth + rightWidth;

    // An array of names for DOM node-types
    // (Array indexes = nodeType() values.)
   /*
    static final int ELEMENT_TYPE = Node.ELEMENT_NODE;
    static final int ATTR_TYPE = Node.ATTRIBUTE_NODE;
    static final int TEXT_TYPE = Node.TEXT_NODE;
    static final int CDATA_TYPE = Node.CDATA_SECTION_NODE;
    static final int ENTITYREF_TYPE = Node.ENTITY_REFERENCE_NODE;
    static final int ENTITY_TYPE = Node.ENTITY_NODE;
    static final int PROCINSTR_TYPE = Node.PROCESSING_INSTRUCTION_NODE;
    static final int COMMENT_TYPE = Node.COMMENT_NODE;
    static final int DOCUMENT_TYPE = Node.DOCUMENT_NODE;
    static final int DOCTYPE_TYPE = Node.DOCUMENT_TYPE_NODE;
    static final int DOCFRAG_TYPE = Node.DOCUMENT_FRAGMENT_NODE;
    static final int NOTATION_TYPE = Node.NOTATION_NODE;
*/
    // The list of elements to display in the tree
    // Could set this with a command-line argument, but
    // not much point -- the list of tree elements still
    // has to be defined internally.
    // Extra credit: Read the list from a file
    // Super-extra credit: Process a DTD and build the list.



    public static JComponent rtrvXMLTree() {
        // Make a nice border
        EmptyBorder eb = new EmptyBorder(5, 5, 5, 5);
        BevelBorder bb = new BevelBorder(BevelBorder.LOWERED);
        CompoundBorder cb = new CompoundBorder(eb, bb);
        //this.setBorder(new CompoundBorder(cb, eb));

        // Set up the tree
        JTree tree = new JTree(new DomToTreeModelAdapter(document));

        // Iterate over the tree and make nodes visible
        // (Otherwise, the tree shows up fully collapsed)
        //TreePath nodePath = ???;
        //  tree.expandPath(nodePath); 
        JScrollPane treeView = new JScrollPane(tree);
        treeView.setPreferredSize(new Dimension(leftWidth, windowHeight));

        // Build right-side view
        // (must be final to be referenced in inner class)
        //final JEditorPane htmlPane = new JEditorPane("text/html", "");
        //htmlPane.setEditable(false);

        //JScrollPane htmlView = new JScrollPane(htmlPane);
        //htmlView.setPreferredSize(new Dimension(rightWidth, windowHeight));

        // Wire the two views together. Use a selection listener 
        // created with an anonymous inner-class adapter.
        tree.addTreeSelectionListener(new TreeSelectionListener() {
                public void valueChanged(TreeSelectionEvent e) {
                    TreePath p = e.getNewLeadSelectionPath();

                    if (p != null) {
                        AdapterNode adpNode =
                            (AdapterNode) p.getLastPathComponent();
                        //htmlPane.setText(adpNode.content());
                    }
                }
            });


            return treeView;
    } // constructor

    public static JComponent rtrvJPanel(String argv) {
        JComponent aJPanel = null;

        if ( argv == null || argv.equals("") ) {
            buildDom();
            aJPanel = makePanel();
        }else{
            crtDocument(argv);
            aJPanel = makePanel();
        }
        return aJPanel;
    }

    public static void crtDocument(String argv) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File(argv));

            removeElmnt(document,Node.TEXT_NODE,null);
            document.normalize();

        } catch (SAXParseException spe) {
            // Error generated by the parser
            System.out.println("\n** Parsing error" + ", line " +
                spe.getLineNumber() + ", uri " + spe.getSystemId());
            System.out.println("   " + spe.getMessage());

            // Use the contained exception, if any
            Exception x = spe;

            if (spe.getException() != null) {
                x = spe.getException();
            }

            x.printStackTrace();
        } catch (SAXException sxe) {
            // Error generated during parsing)
            Exception x = sxe;

            if (sxe.getException() != null) {
                x = sxe.getException();
            }
            x.printStackTrace();
        } catch (ParserConfigurationException pce) {
            // Parser with specified options can't be built
            pce.printStackTrace();
        } catch (IOException ioe) {
            // I/O error
            ioe.printStackTrace();
        }
    } // main
    public  void makeFrame() {
        // Set up a GUI framework
        JFrame frame = new JFrame("DOM Echo");
        frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });

        // Set up the tree, the views, and display it all
        final XMLTree echoPanel = new XMLTree();
        frame.getContentPane()
             .add("Center", echoPanel);
        frame.pack();

        Dimension screenSize = Toolkit.getDefaultToolkit()
                                      .getScreenSize();
        int w = windowWidth + 10;
        int h = windowHeight + 10;
        frame.setLocation((screenSize.width / 3) - (w / 2),
            (screenSize.height / 2) - (h / 2));
        frame.setSize(w, h);
        frame.setVisible(true);
    } // makeFrame

    public static JComponent makePanel() {

        final JComponent echoPanel =  XMLTree.rtrvXMLTree();
        return echoPanel;
    } // makeFrame

    public static void buildDom() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.newDocument(); // Create from whole cloth

            Element root = (Element) document.createElement("rootElement");
            document.appendChild(root);
            root.appendChild(document.createTextNode("Some"));
            root.appendChild(document.createTextNode(" "));
            root.appendChild(document.createTextNode("text"));

            document.getDocumentElement()
                    .normalize();
        } catch (ParserConfigurationException pce) {
            // Parser with specified options can't be built
            pce.printStackTrace();
        }
    } // buildDom
    public static void removeElmnt(Node node, short nodeType, String name) {

       System.out.println("Type = "+node.getNodeType()+", name = "+node.getNodeName()+", has children = "+node.hasChildNodes());
       if (node.hasChildNodes()){
          NodeList list = node.getChildNodes();
          for (int i=0; i<list.getLength(); i++) {
             removeElmnt(list.item(i), nodeType, name);
          }
        }
       else if (node.getNodeName().trim().equalsIgnoreCase("Text") ||
                  node.getNodeName().trim().equalsIgnoreCase("#Text")) {
           node.getParentNode().removeChild(node);
        }
    }
    public static Document getDocument()
    {
       return document;
    }
}
