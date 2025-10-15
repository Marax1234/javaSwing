package gui;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

/**
 * Messages area for displaying status messages and notifications.
 *
 * <p>
 * This class extends Swing JScrollPane and provides a panel for displaying application status messages, notifications,
 * and other informational text to the user. It contains a JList with a DefaultListModel for managing messages.
 * </p>
 *
 * @author I. Bogicevic
 * @author Max Hiller
 * @version 0.3
 * @since 0.2
 */
public class MessagesArea extends JScrollPane {

    /** The list model for managing message items. */
    private DefaultListModel<String> listModel;

    /** The list component for displaying messages. */
    private JList<String> messageList;

    /**
     * Constructs a new MessagesArea.
     *
     * <p>
     * This constructor creates a scrollable list view for displaying messages. Messages can be added using the
     * addMessage() method or by accessing the list model directly via getListModel().
     * </p>
     */
    public MessagesArea() {
        super();
        listModel = new DefaultListModel<>();
        messageList = new JList<>(listModel);
        setViewportView(messageList);
    }

    /**
     * Returns the list model for managing messages.
     *
     * @return the DefaultListModel containing messages
     */
    public DefaultListModel<String> getListModel() {
        return listModel;
    }

    /**
     * Adds a message to the messages area.
     *
     * @param message
     *            the message to add
     */
    public void addMessage(String message) {
        listModel.addElement(message);
    }
}
