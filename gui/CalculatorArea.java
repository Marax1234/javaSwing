package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.Calculator;
import data.Packet;

/**
 * Calculator area for package dimension input and shipping cost calculation.
 *
 * <p>
 * This class extends Swing JPanel and provides a form for entering package dimensions (length, width, height) and
 * weight, with a button to calculate and display the shipping costs.
 * </p>
 *
 * @author I. Bogicevic
 * @author Max Hiller
 * @version 0.3
 * @since 0.2
 */
public class CalculatorArea extends JPanel {

    /** Text field for entering the package length in millimeters. */
    JTextField lengthTextField = new JTextField(10);

    /** Text field for entering the package width in millimeters. */
    JTextField widthTextField = new JTextField(10);

    /** Text field for entering the package height in millimeters. */
    JTextField heightTextField = new JTextField(10);

    /** Text field for entering the package weight in grams. */
    JTextField weightTextField = new JTextField(10);

    /** Label displaying the calculated shipping cost. */
    JLabel shippingCostLabel = new JLabel("?");

    /** Button to trigger the shipping cost calculation. */
    JButton calcButton = new JButton("Calculate");

    /**
     * Calculates the shipping costs based on user input.
     *
     * <p>
     * This method reads the values from the input text fields, creates a Packet object, calculates the shipping costs
     * using the Calculator, and displays the result in the shipping cost label.
     * </p>
     *
     * @return the calculated shipping cost in euros
     */
    private double calcShippingCosts() {
        // Initialize calculator
        Calculator calc = new Calculator();

        // Get user input values from text fields
        int length = Integer.parseInt(lengthTextField.getText());
        int width = Integer.parseInt(widthTextField.getText());
        int height = Integer.parseInt(heightTextField.getText());
        int weight = Integer.parseInt(weightTextField.getText());

        // Perform shipping cost calculation
        Packet packet = new Packet(length, width, height, weight);
        Double costs = calc.calcShippingCosts(packet);

        // Display the result to the user
        shippingCostLabel.setText(costs.toString());

        return costs;
    }

    /**
     * Constructs a new CalculatorArea and initializes the form layout.
     *
     * <p>
     * This constructor creates a grid bag layout with input fields for package dimensions and weight, unit labels, a
     * calculate button, and a label to display the shipping cost result.
     * </p>
     */
    public CalculatorArea() {
        // Set layout manager
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Add description labels for each input field
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Length: "), gbc);

        gbc.gridy = 1;
        add(new JLabel("Width:  "), gbc);

        gbc.gridy = 2;
        add(new JLabel("Height: "), gbc);

        gbc.gridy = 3;
        add(new JLabel("Weight: "), gbc);

        // Add input text fields
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(lengthTextField, gbc);

        gbc.gridy = 1;
        add(widthTextField, gbc);

        gbc.gridy = 2;
        add(heightTextField, gbc);

        gbc.gridy = 3;
        add(weightTextField, gbc);

        // Add unit labels (mm for dimensions, g for weight)
        gbc.gridx = 2;
        gbc.gridy = 0;
        add(new JLabel("mm"), gbc);

        gbc.gridy = 1;
        add(new JLabel("mm"), gbc);

        gbc.gridy = 2;
        add(new JLabel("mm"), gbc);

        gbc.gridy = 3;
        add(new JLabel("g"), gbc);

        // Add shipping cost calculation controls
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Shipping Costs: "), gbc);

        gbc.gridx = 1;
        add(shippingCostLabel, gbc);

        gbc.gridx = 2;
        add(calcButton, gbc);

        // Configure action listener for calculate button
        calcButton.addActionListener(e -> calcShippingCosts());
    }
}
