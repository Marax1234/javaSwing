package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Main application class for the Package Calculator.
 *
 * <p>
 * This class extends Swing JFrame and serves as the entry point for the Package Calculator application. It implements
 * the singleton pattern to provide global access to the application instance and manages the main window layout with
 * its various GUI components.
 * </p>
 *
 * <p>
 * The application window is organized using nested JSplitPanes with the following layout:
 * </p>
 * <ul>
 * <li>Left: Explorer area (20%)</li>
 * <li>Center-Top: Calculator/Editor area (64%)</li>
 * <li>Center-Right: Inspector area (16%)</li>
 * <li>Center-Bottom: Messages area (10%)</li>
 * <li>Top: Toolbar</li>
 * <li>Bottom: Status bar</li>
 * </ul>
 *
 * @author I. Bogicevic
 * @author Max Hiller
 * @version 0.3
 * @since 0.2
 */
public class PackageCalculator extends JFrame {

    /** The application name displayed in the window title. */
    public static final String APPNAME = "PackageCalculator";

    /** Singleton instance of the application. */
    private static PackageCalculator instance;

    /**
     * Returns the singleton instance of the PackageCalculator application.
     *
     * @return the singleton instance
     */
    public static PackageCalculator getInstance() {
        return instance;
    }

    /** Toolbar area containing application buttons. */
    public ToolbarArea toolbarArea = new ToolbarArea();

    /** Explorer area showing the file system tree. */
    public ExplorerArea explorerArea = new ExplorerArea();

    /** Calculator area for package dimension input and cost calculation. */
    public CalculatorArea editorArea = new CalculatorArea();;

    /** Inspector area for displaying detail information. */
    public InspectorArea inspectorArea = new InspectorArea();

    /** Messages area for displaying status messages. */
    public MessagesArea messagesArea = new MessagesArea();

    /** Status bar at the bottom of the window. */
    public StatusArea statusArea = new StatusArea();

    /** Root path to the currently open project directory. */
    public String rootPath;

    /**
     * Initializes the GUI components and layout.
     *
     * <p>
     * This method creates the nested layout structure with JSplitPanes and sets up the main window.
     * </p>
     */
    private void initializeGUI() {
        // Set window title
        setTitle(APPNAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create horizontal split pane for editor and inspector (right side)
        JSplitPane lr2SplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, editorArea, inspectorArea);
        lr2SplitPane.setResizeWeight(0.8);
        lr2SplitPane.setDividerLocation(0.8);

        // Create vertical split pane for main content and messages (top-down)
        JSplitPane tdSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, lr2SplitPane, messagesArea);
        tdSplitPane.setResizeWeight(0.9);
        tdSplitPane.setDividerLocation(0.9);

        // Create horizontal split pane for explorer and content (left-right)
        JSplitPane lrSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, explorerArea, tdSplitPane);
        lrSplitPane.setResizeWeight(0.2);
        lrSplitPane.setDividerLocation(0.2);

        // Set up main frame layout
        setLayout(new BorderLayout());
        add(toolbarArea, BorderLayout.NORTH);
        add(lrSplitPane, BorderLayout.CENTER);
        add(statusArea, BorderLayout.SOUTH);

        // Set window size to screen size
        Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(new Dimension((int) screenBounds.getWidth(), (int) screenBounds.getHeight()));
        setLocationRelativeTo(null);

        // Load default workspace if needed
        // TODO: Configure default workspace path
        // ProjectHandling.openProject("/Users/...");
    }

    /**
     * Constructs a new PackageCalculator instance.
     *
     * <p>
     * This constructor initializes the singleton instance and sets up the GUI.
     * </p>
     */
    public PackageCalculator() {
        // Initialize singleton instance
        PackageCalculator.instance = this;

        // Initialize the GUI
        initializeGUI();
    }

    /**
     * Main entry point for the application.
     *
     * <p>
     * This method sets the Look and Feel to the system default and creates the main window on the Event Dispatch
     * Thread.
     * </p>
     *
     * @param args
     *            command line arguments
     */
    public static void main(String[] args) {
        // Set system Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create and show the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            PackageCalculator app = new PackageCalculator();
            app.setVisible(true);
        });
    }
}
