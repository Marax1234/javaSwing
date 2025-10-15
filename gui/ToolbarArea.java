package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import control.ProjectHandling;

/**
 * Toolbar area containing application control buttons.
 *
 * <p>
 * This class extends Swing JToolBar and provides buttons for common operations such as opening projects, creating
 * files, and accessing application information.
 * </p>
 *
 * @author I. Bogicevic
 * @author Max Hiller
 * @version 0.3
 * @since 0.2
 */
public class ToolbarArea extends JToolBar {

    /**
     * Shows an information dialog with application details.
     *
     * <p>
     * This method displays a modal dialog window containing the application name, version, and copyright information.
     * </p>
     */
    private void showInfoDialog() {
        JDialog dialog = new JDialog(PackageCalculator.getInstance(), "Info", true);
        JPanel panel = new JPanel();
        JLabel infoLabel = new JLabel("<html>Package Calculator v0.3<br>(c) 2020 I. Bogicevic, Max Hiller</html>");
        panel.add(infoLabel);
        dialog.getContentPane().add(panel);
        dialog.setSize(400, 150);
        dialog.setLocationRelativeTo(PackageCalculator.getInstance());
        dialog.setVisible(true);
    }

    /**
     * Constructs a new ToolbarArea and initializes all buttons.
     *
     * <p>
     * This constructor creates and configures all toolbar buttons including Open Project, New File, Save File, Save
     * File As, Settings, About, and Info. It also sets up the corresponding action listeners for interactive buttons.
     * </p>
     */
    public ToolbarArea() {
        // Initialize all toolbar buttons
        JButton openProjectButton = new JButton("Open Project");
        JButton newFileButton = new JButton("New File");
        JButton saveFileButton = new JButton("Save File");
        JButton saveFileAsButton = new JButton("Save File as");
        JButton settingsButton = new JButton("Settings");
        JButton aboutButton = new JButton("About");
        JButton infoButton = new JButton("Info");

        // Configure action listeners for interactive buttons
        openProjectButton.addActionListener(e -> ProjectHandling.openProject());
        newFileButton.addActionListener(e -> ProjectHandling.newFile());
        infoButton.addActionListener(e -> showInfoDialog());

        // Add all buttons to the toolbar
        add(openProjectButton);
        add(newFileButton);
        add(saveFileButton);
        add(saveFileAsButton);
        addSeparator();
        add(settingsButton);
        add(aboutButton);
        add(infoButton);
    }
}
