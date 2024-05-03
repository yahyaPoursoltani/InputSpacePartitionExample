public class TriangleCalculator {
    public double calculateTriangleArea(double a, double b, double c)
            throws IllegalArgumentException {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException(
                    "All sides of a triangle must be positive");
        }
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException(
                    "A triangle with these sides does not exist");
        }
        var s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
}

