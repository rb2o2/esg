package io.github.rb2o2.esg;

public final class RegularizedDirac {

    private RegularizedDirac() {}

    /**
     * Evaluates rd(x, y, epsilon) = exp(-(x^2 + y^2) / epsilon^2) / (sqrt(pi) * epsilon).
     *
     * @param x horizontal coordinate
     * @param y vertical coordinate
     * @param epsilon width parameter (must be greater than 0.05)
     * @return regularized Dirac delta value
     * @throws IllegalArgumentException when epsilon too small
     */
    public static double rd(double x, double y, double x0, double y0, double epsilon) {
        if (epsilon < 0.05) {
            throw new IllegalArgumentException("epsilon must be >= than 5e-2");
        }
        double radiusSquared = (x-x0) * (x-x0) + (y-y0) * (y-y0);
        double denom = Math.sqrt(Math.PI) * epsilon;
        double exponent = -radiusSquared / (epsilon * epsilon);
        return Math.exp(exponent) / denom;
    }
}