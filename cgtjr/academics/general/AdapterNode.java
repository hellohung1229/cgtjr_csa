/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.general;

/**
 *
 * @author clayton g thomas jr
 */
import org.w3c.dom.Node;
import javax.swing.tree.*;
import javax.swing.event.*;

public class AdapterNode {
    boolean compress = false;

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

    // This class wraps a DOM node and returns the text we want to
    // display in the tree. It also returns children, index values,
    // and child counts.

    org.w3c.dom.Node domNode;

        // Construct an Adapter node from a DOM node
        public AdapterNode(org.w3c.dom.Node node) {
            domNode = node;
        }
        static String[] treeElementNames =
        {
            "slideshow", "slide", "title", // For slideshow #1
            "slide-title", // For slideshow #10
            "item",
        };
        static final String[] typeName =
        {
            "none", "Element", "Attr", "Text", "CDATA", "EntityRef", "Entity",
            "ProcInstr", "Comment", "Document", "DocType", "DocFragment",
            "Notation",
        };

        // Return a string that identifies this node in the tree
        public String toString() {
            String s = typeName[domNode.getNodeType()];
            String nodeName = domNode.getNodeName();

            if (!nodeName.startsWith("#")) {
                s += (": " + nodeName);
            }

            if (compress) {
                String t = content()
                               .trim();
                int x = t.indexOf("\n");

                if (x >= 0) {
                    t = t.substring(0, x);
                }

                s += (" " + t);

                return s;
            }

            if (domNode.getNodeValue() != null) {
                if (s.startsWith("ProcInstr")) {
                    s += ", ";
                } else {
                    s += ": ";
                }

                // Trim the value to get rid of NL's at the front
                String t = domNode.getNodeValue()
                                  .trim();
                int x = t.indexOf("\n");

                if (x >= 0) {
                    t = t.substring(0, x);
                }

                s += t;
            }

            return s;
        }

        public String content() {
            String s = "";
            org.w3c.dom.NodeList nodeList = domNode.getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {
                org.w3c.dom.Node node = nodeList.item(i);
                int type = node.getNodeType();
                AdapterNode adpNode = new AdapterNode(node); //inefficient, but works

                if (type == ELEMENT_TYPE) {
                    // Skip subelements that are displayed in the tree.
                    if (treeElement(node.getNodeName())) {
                        continue;
                    }

                    // EXTRA-CREDIT HOMEWORK:
                    //   Special case the SLIDE element to use the TITLE text
                    //   and ignore TITLE element when constructing the tree.
                    s += ("<" + node.getNodeName() + ">");
                    s += adpNode.content();
                    s += ("</" + node.getNodeName() + ">");
                } else if (type == TEXT_TYPE) {
                   if(node.getNodeValue()==null || node.getNodeValue().trim().equals(""))
                   {
                      //s+= node.getNodeValue();
                   }
                    s += node.getNodeValue();
                } else if (type == ENTITYREF_TYPE) {
                    // The content is in the TEXT node under it
                    s += adpNode.content();
                } else if (type == CDATA_TYPE) {
                    // The "value" has the text, same as a text node.
                    //   while EntityRef has it in a text node underneath.
                    //   (because EntityRef can contain multiple subelements)
                    // Convert angle brackets and ampersands for display
                    StringBuffer sb = new StringBuffer(node.getNodeValue());

                    for (int j = 0; j < sb.length(); j++) {
                        if (sb.charAt(j) == '<') {
                            sb.setCharAt(j, '&');
                            sb.insert(j + 1, "lt;");
                            j += 3;
                        } else if (sb.charAt(j) == '&') {
                            sb.setCharAt(j, '&');
                            sb.insert(j + 1, "amp;");
                            j += 4;
                        }
                    }

                    s += ("<pre>" + sb + "\n</pre>");
                }

                // Ignoring these:
                //   ATTR_TYPE      -- not in the DOM tree
                //   ENTITY_TYPE    -- does not appear in the DOM
                //   PROCINSTR_TYPE -- not "data"
                //   COMMENT_TYPE   -- not "data"
                //   DOCUMENT_TYPE  -- Root node only. No data to display.
                //   DOCTYPE_TYPE   -- Appears under the root only
                //   DOCFRAG_TYPE   -- equiv. to "document" for fragments
                //   NOTATION_TYPE  -- nothing but binary data in here
            }

            return s;
        }

        /*
         * Return children, index, and count values
         */
        public int index(AdapterNode child) {
            //System.err.println("Looking for index of " + child);
            int count = childCount();

            for (int i = 0; i < count; i++) {
                AdapterNode n = this.child(i);

                if (child.domNode == n.domNode) {
                    return i;
                }
            }

            return -1; // Should never get here.
        }

        public AdapterNode child(int searchIndex) {
            //Note: JTree index is zero-based.
            org.w3c.dom.Node node = domNode.getChildNodes()
                                           .item(searchIndex);

            if (compress) {
                // Return Nth displayable node
                int elementNodeIndex = 0;

                for (int i = 0; i < domNode.getChildNodes()
                                               .getLength(); i++) {
                    node = domNode.getChildNodes()
                                  .item(i);

                    if ((node.getNodeType() == ELEMENT_TYPE) &&
                            treeElement(node.getNodeName()) &&
                            (elementNodeIndex++ == searchIndex)) {
                        break;
                    }
                }
            }

            return new AdapterNode(node);
        }

        public int childCount() {
            if (!compress) {
                // Indent this
                return domNode.getChildNodes()
                              .getLength();
            }

            int count = 0;

            for (int i = 0; i < domNode.getChildNodes()
                                           .getLength(); i++) {
                org.w3c.dom.Node node = domNode.getChildNodes()
                                               .item(i);

                if ((node.getNodeType() == ELEMENT_TYPE) &&
                        treeElement(node.getNodeName())) {
                    // Note:
                    //   Have to check for proper type.
                    //   The DOCTYPE element also has the right name
                    ++count;
                }
            }

            return count;
        }
    boolean treeElement(String elementName) {
        for (int i = 0; i < treeElementNames.length; i++) {
            if (elementName.equals(treeElementNames[i])) {
                return true;
            }
        }

        return false;
    }
    }


