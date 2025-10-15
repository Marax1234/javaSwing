package gui;

import java.awt.Component;
import java.io.File;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;

/**
 * Explorer area displaying a file system tree view.
 *
 * <p>
 * This class extends Swing JTabbedPane and provides a file system browser that displays directories and .txt files in a
 * tree structure. It includes context menus for file operations and filters to show only relevant file types.
 * </p>
 *
 * @author I. Bogicevic
 * @author Max Hiller
 * @version 0.3
 * @since 0.2
 */
public class ExplorerArea extends JTabbedPane {

    /** The tree view displaying the file system structure. */
    private JTree treeView;

    /** The tree model managing the file system data. */
    private DefaultTreeModel treeModel;

    /**
     * Wrapper class for representing a file in the tree cell.
     *
     * <p>
     * This inner class wraps a File object and provides custom string representation and context menu functionality for
     * tree view items.
     * </p>
     */
    class FileItem {
        /** The file represented by this tree item. */
        public File file;

        /**
         * Private default constructor.
         */
        @SuppressWarnings("unused")
        private FileItem() {
        }

        /**
         * Constructs a FileItem from a File object.
         *
         * @param file
         *            the file to wrap
         */
        public FileItem(File file) {
            this.file = file;
        }

        /**
         * Constructs a FileItem from a file path string.
         *
         * @param str
         *            the file path string
         */
        public FileItem(String str) {
            this.file = new File(str);
        }

        /**
         * Returns the string representation of this file item.
         *
         * <p>
         * For the root folder, displays "..." + separator + name. For other files, displays only the file name.
         * </p>
         *
         * @return the display name for this file item
         */
        @Override
        public String toString() {
            // Hide root folder name (already shown in window title)
            if (file.getAbsolutePath().equals(PackageCalculator.getInstance().rootPath)) {
                return "..." + File.separator + file.getName();
            }
            // Return only the filename for other items
            return file.getName();
        }
    }

    /**
     * Creates a tree node for the given file item.
     *
     * <p>
     * This method creates a tree node containing the FileItem. Child nodes are loaded lazily when the node is expanded.
     * </p>
     *
     * @param fileItem
     *            the file item to create a tree node for
     * @return the tree node representing the file
     */
    private DefaultMutableTreeNode createNode(FileItem fileItem) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(fileItem);
        // Add dummy node if directory to enable expansion
        if (fileItem.file.isDirectory()) {
            node.add(new DefaultMutableTreeNode("Loading..."));
        }
        return node;
    }

    /**
     * Builds the children for a tree node.
     *
     * <p>
     * Only directories and .txt files are included in the tree.
     * </p>
     *
     * @param node
     *            the tree node to build children for
     */
    private void buildChildren(DefaultMutableTreeNode node) {
        Object userObject = node.getUserObject();
        if (!(userObject instanceof FileItem)) {
            return;
        }

        FileItem fileItem = (FileItem) userObject;
        if (!fileItem.file.isDirectory()) {
            return;
        }

        // Remove dummy/loading node
        node.removeAllChildren();

        // Add actual children
        File[] files = fileItem.file.listFiles();
        if (files != null) {
            for (File childFile : files) {
                // Add only folders and txt-files
                if (childFile.isDirectory() || childFile.getName().toLowerCase().endsWith(".txt")) {
                    node.add(createNode(new FileItem(childFile)));
                }
            }
        }

        treeModel.nodeStructureChanged(node);
    }

    /**
     * Custom tree cell renderer with context menus.
     *
     * <p>
     * This class provides custom rendering for tree cells and creates context-sensitive menus based on whether the item
     * is a file or directory.
     * </p>
     */
    private class FileTreeCellRenderer extends DefaultTreeCellRenderer {
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
                boolean leaf, int row, boolean hasFocus) {

            super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

            if (value instanceof DefaultMutableTreeNode) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
                Object userObject = node.getUserObject();

                if (userObject instanceof FileItem) {
                    FileItem fileItem = (FileItem) userObject;
                    setText(fileItem.toString());

                    // Create context menu
                    JPopupMenu contextMenu = new JPopupMenu();

                    if (fileItem.file.isDirectory()) {
                        // Context menu for folders
                        JMenuItem newFileMenu = new JMenuItem("New File");
                        JMenuItem newSubfolderMenu = new JMenuItem("New Subfolder");
                        contextMenu.add(newFileMenu);
                        contextMenu.add(newSubfolderMenu);
                    } else {
                        // Context menu for files
                        JMenuItem openMenu = new JMenuItem("Open");
                        JMenuItem closeMenu = new JMenuItem("Close");
                        contextMenu.add(openMenu);
                        contextMenu.add(closeMenu);
                    }

                    // Add common menu items
                    JMenuItem copyMenu = new JMenuItem("Copy");
                    JMenuItem pasteMenu = new JMenuItem("Paste");
                    JMenuItem deleteMenu = new JMenuItem("Delete");
                    contextMenu.add(new JSeparator());
                    contextMenu.add(copyMenu);
                    contextMenu.add(pasteMenu);
                    contextMenu.add(deleteMenu);

                    setComponentPopupMenu(contextMenu);
                }
            }

            return this;
        }
    }

    /**
     * Builds a file system browser tree view for the given root path.
     *
     * <p>
     * Note: Code structure adapted from original JavaFX version.
     * </p>
     *
     * @param rootPath
     *            the root directory path to browse
     * @return the tree view displaying the file system
     */
    private JTree buildFileSystemBrowser(String rootPath) {
        DefaultMutableTreeNode root = createNode(new FileItem(new File(rootPath)));
        treeModel = new DefaultTreeModel(root);
        JTree tree = new JTree(treeModel);

        // Set custom cell renderer
        tree.setCellRenderer(new FileTreeCellRenderer());

        // Add tree expansion listener for lazy loading
        tree.addTreeWillExpandListener(new TreeWillExpandListener() {
            @Override
            public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
                Object lastPathComponent = event.getPath().getLastPathComponent();
                if (lastPathComponent instanceof DefaultMutableTreeNode) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) lastPathComponent;
                    // Check if children need to be loaded
                    if (node.getChildCount() == 1) {
                        Object childObject = ((DefaultMutableTreeNode) node.getChildAt(0)).getUserObject();
                        if (childObject instanceof String && "Loading...".equals(childObject)) {
                            buildChildren(node);
                        }
                    }
                }
            }

            @Override
            public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
                // No action needed on collapse
            }
        });

        return tree;
    }

    /**
     * Loads a new file system tree for the given project path.
     *
     * <p>
     * This method builds a new tree view of the file system starting at the specified project path and displays it in
     * the explorer tab.
     * </p>
     *
     * @param projectPath
     *            the root path of the project to explore
     */
    public void loadNewTree(String projectPath) {
        // Build the file system tree
        treeView = buildFileSystemBrowser(projectPath);

        // Expand the root node
        treeView.expandRow(0);

        // Add tree to the explorer tab in a scroll pane
        removeAll();
        JScrollPane scrollPane = new JScrollPane(treeView);
        addTab("Explorer", scrollPane);
    }

    /**
     * Constructs a new ExplorerArea.
     *
     * <p>
     * This constructor initializes the tabbed pane with a fixed, non-closable tab labeled "Explorer".
     * </p>
     */
    public ExplorerArea() {
        // Initialize with empty tab
        addTab("Explorer", new JScrollPane());
    }
}
