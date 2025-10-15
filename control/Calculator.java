package control;

import data.Packet;

/**
 * Calculator for computing shipping costs based on package dimensions and weight.
 *
 * <p>
 * This class provides methods to calculate shipping costs according to predefined pricing rules based on package size
 * and weight.
 * </p>
 *
 * @author I. Bogicevic
 * @author Max Hiller
 * @version 0.3
 * @since 0.2
 */
public class Calculator {

    /**
     * Calculates the shipping cost for a given package.
     *
     * <p>
     * The shipping cost is determined by the package dimensions and weight according to the following pricing table:
     * </p>
     * <ul>
     * <li>Up to 300×300×150 mm: €3.89</li>
     * <li>Up to 600×300×150 mm: €4.39</li>
     * <li>Up to 1200×600×600 mm and ≤5000g: €5.99</li>
     * <li>Larger packages ≤10000g: €7.99</li>
     * <li>Larger packages >10000g: €14.99</li>
     * </ul>
     *
     * @param pack
     *            the package for which to calculate shipping costs
     * @return the shipping cost in euros
     */
    public double calcShippingCosts(Packet pack) {
        double shippingCosts;
        // Check for small package: up to 300×300×150 mm
        if ((pack.height <= 300) && (pack.width <= 300) && (pack.height <= 150)) {
            shippingCosts = 3.89;
        }
        // Check for medium package: up to 600×300×150 mm
        if ((pack.height <= 600) && (pack.width <= 300) && (pack.height <= 150)) {
            shippingCosts = 4.39;
        }
        // Check for large package: up to 1200×600×600 mm and weight ≤ 5000g
        if ((pack.height <= 1200) && (pack.width <= 600) && (pack.height <= 600) && pack.weight <= 5000) {
            shippingCosts = 5.99;
        } else if (pack.weight <= 10000) {
            // Extra large package with weight ≤ 10000g
            shippingCosts = 7.99;
        } else {
            // Extra large package with weight > 10000g
            shippingCosts = 14.99;
        }
        return shippingCosts;
    }
}
