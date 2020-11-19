// Group Class - Holds Group's ID and Status

import javax.swing.tree.DefaultMutableTreeNode;

// Visitor Design Pattern
public class Group implements TreeInterface {

    private String uniqueID;
    private boolean generatedGroup;
    private static DefaultMutableTreeNode root;

    public Group() {
        root = new DefaultMutableTreeNode("Root");    
    }

    public Group(String uniqueID) {
        this(uniqueID, true);    
    }

    public Group(String uniqueID, boolean generated) {
        this.generatedGroup = generated;
        this.uniqueID = uniqueID;
    }

    public DefaultMutableTreeNode getRoot() {
        return root;
    }

    public String getID() {
        return uniqueID;
    }

    public boolean isGeneratedGroup() {
        return generatedGroup;
    }
    
    public String toString() {
        return uniqueID;
    }
}
