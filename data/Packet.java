package data;

/**
 * Represents a package with its physical dimensions and weight.
 *
 * <p>
 * This class models a shipping package with length, width, height (in millimeters) and weight (in grams). These
 * properties are used to calculate shipping costs.
 * </p>
 *
 * @author I. Bogicevic
 * @author Max Hiller
 * @version 0.3
 * @since 0.2
 */
public class Packet {

    /** Length of the package in millimeters. */
    public int length;

    /** Width of the package in millimeters. */
    public int width;

    /** Height of the package in millimeters. */
    public int height;

    /** Weight of the package in grams. */
    public int weight;

    /**
     * Constructs a new Packet with the specified dimensions and weight.
     *
     * @param length
     *            the length of the package in millimeters
     * @param width
     *            the width of the package in millimeters
     * @param height
     *            the height of the package in millimeters
     * @param weight
     *            the weight of the package in grams
     */
    public Packet(int length, int width, int height, int weight) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
    }
}
