package gui;

import javax.swing.JLabel;

/**
 * Status area displayed at the bottom of the application window.
 *
 * <p>
 * This class extends Swing JLabel and provides a status bar for displaying brief status information, progress
 * indicators, or other application state messages to the user.
 * </p>
 *
 * @author I. Bogicevic
 * @author Max Hiller
 * @version 0.3
 * @since 0.2
 */
public class StatusArea extends JLabel {
    /**
     * Constructs a new StatusArea.
     *
     * <p>
     * This constructor creates an empty status bar label. Status text can be updated using the setText() method.
     * </p>
     */
    public StatusArea() {
        super();
    }
}
