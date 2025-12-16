package io.github.rb2o2.esg;

import java.util.List;
import java.util.Locale;

public class Mesh2D {
    public static int dimx = 500;
    public static int dimy = 500;
    public double[][] uvalues;
    public static int maxFreq = 40;
    
    public Mesh2D(int dimx, int dimy, int maxFreq) {
        Mesh2D.dimx = dimx;
        Mesh2D.dimy = dimy;
        Mesh2D.maxFreq = maxFreq;
    }

    public void initMoves(List<Double[]> moves) {
        uvalues = new double[dimx][dimy];
        for (int i = 0; i < dimx; i++) {
            uvalues[i] = new double[dimy];
        }
        long ts0 = System.currentTimeMillis();

        for (int n = 0; n < dimx; n++) {
            for (int m = 0; m < dimy; m++) {
                double xi = 1.0/dimx * n;
                double yi = 1.0/dimy * m;
                for (int i = 1; i <= maxFreq; i++) {
                    for (int j = 1; j < maxFreq; j++) {
                        for (int mv = 0; mv< moves.size(); mv++) {
                            int sign = mv%2==0?1:-1;
                            uvalues[n][m] += - moves.get(mv)[2] * sign *
                                    Math.sin(i * Math.PI * xi) * Math.sin(j * Math.PI * yi) *
                                    Math.sin(i * Math.PI * moves.get(mv)[0]) * 
                                    Math.sin(j * Math.PI * moves.get(mv)[1]) /
                                    (i*i + j*j);
                        }
                    }
                }
            }
        }
        System.out.println("computation took " + (System.currentTimeMillis() - ts0) + " ms");
    }

    public static String csvPrint(double[][] vals) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < vals.length; i++) {
            for (int j = 0; j < vals[0].length; j++) {
                sb.append(String.format(Locale.US, "%.2e,", vals[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
