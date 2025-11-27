package io.github.rb2o2.esg;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        var mesh = new Mesh2D(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        mesh.initMoves(new ArrayList<>(){{add(new Double[]{0.5,0.5,1.0});}});
        Mesh2D.csvPrint(mesh.uvalues);
    }
}
