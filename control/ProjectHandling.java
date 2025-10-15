package control;

import java.io.File;

import javax.swing.JFileChooser;

import gui.PackageCalculator;

/**
 * Handles project and file management operations.
 *
 * <p>
 * This class provides static methods for opening projects, creating new files, and managing the workspace in the
 * Package Calculator application.
 * </p>
 *
 * @author I. Bogicevic
 * @author Max Hiller
 * @version 0.3
 * @since 0.2
 */
public class ProjectHandling {

    /**
     * Opens a project from the specified directory path.
     *
     * <p>
     * This method updates the application window title, loads the directory tree in the explorer area, and remembers
     * the project root path.
     * </p>
     *
     * @param rootPath
     *            the absolute path to the project directory
     */
    public static void openProject(String rootPath) {
        // Update window title to show current project path
        PackageCalculator.getInstance().setTitle(PackageCalculator.APPNAME + " – " + rootPath);
        // Load directory tree in explorer area
        PackageCalculator.getInstance().explorerArea.loadNewTree(rootPath);
        // Remember the currently open project path
        PackageCalculator.getInstance().rootPath = rootPath;
    }

    /**
     * Opens a project by showing a directory chooser dialog.
     *
     * <p>
     * This method displays a directory selection dialog to the user. Note: The actual project opening functionality is
     * currently commented out.
     * </p>
     */
    public static void openProject() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        File projectDirectory = null;

        // Show directory chooser dialog
        int result = fileChooser.showOpenDialog(PackageCalculator.getInstance());
        if (result == JFileChooser.APPROVE_OPTION) {
            projectDirectory = fileChooser.getSelectedFile();
        }

        if (projectDirectory != null) {
            projectDirectory.getAbsolutePath();
        }
        // TODO: Enable project opening functionality
        // openProject(projectDirectory.getAbsolutePath());
    }

    /**
     * Creates a new file in the current project.
     *
     * <p>
     * Note: This method is currently not implemented.
     * </p>
     */
    public static void newFile() {
        // TODO: Implement new file creation functionality
    }
}
