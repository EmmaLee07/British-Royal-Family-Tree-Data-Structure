import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class RoyalFamily {
    public static void main(String[] args) {
        // Define a variable to store the root node
        TNode<String> root = null;

        // TODO: SETUP TREE DATA
        // 1. Use Scanner to read the data.txt file
        // 2. The first line in data.txt is the root node
        // 3. For each line in data.txt in the format A > B
        // - *find* the A node
        // - add B as a child of A
        try {
            // Initialize Scanner to read from data.txt
            Scanner scanner = new Scanner(new File("data.txt"));

            // Read the root node from the first line
            root = new TNode<>(scanner.nextLine().trim());

            // Process the remaining lines to populate the tree
            while (scanner.hasNextLine()) {
                // Read a line in the format A > B
                String line = scanner.nextLine();
                
                // Split the line into parent and child nodes
                String[] nodes = line.split(" > ");
                
                // Find the parent node in the tree
                TNode<String> parent = find(root, nodes[0]);
                
                // Create a new child node and add it to the parent
                TNode<String> child = new TNode<>(nodes[1]);
                parent.addChild(child);
            }

            // Close the scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            // Handle file not found exception
            e.printStackTrace();
        }

        // TODO: test printPath method
        TNode<String> child = find(root, "Princess Beatrice of York");
        String path = getPath(child);
        System.out.println(path);
    }

    /**
     * @return node if its data matches name, or return a child node with data that matches name
     */
    public static TNode<String> find(TNode<String> node, String name) {
        // Base case: node is null or its data matches the target name
        if (node == null || node.getData().equals(name)) {
            return node;
        }

        // Check each child of the current node
        for (TNode<String> child : node.getChildren()) {
            // Recursively search for the target name in the child subtree
            TNode<String> result = find(child, name);
            
            // If found, return the result
            if (result != null) {
                return result;
            }
        }

        // Target name not found in the current subtree
        return null;
    }

    /**
     * @return a String containing the path from the root node to the specified node, separated by ->
     */
    public static String getPath(TNode<String> node) {
        // Base case: node is null, return an empty string
        if (node == null) {
            return "";
        }

        // Get the parent of the current node
        TNode<String> parent = node.getParent();

        // Base case: node is the root, return its data
        if (parent == null) {
            return node.getData();
        }

        // Recursively build the path from the root to the current node
        return getPath(parent) + " -> " + node.getData();
    }
}
