package io.github.rb2o2.esg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        new AppFrame();
    }
}

class AppFrame extends JFrame {
    private final Color p1 = new Color(19, 200, 42);
    private final Color p2 = new Color(181, 0, 75);
    private final Color[][] colorMesh;
    private final Mesh2D mesh = new Mesh2D(64, 64, 64);
    public AppFrame() {
        setLayout(new BorderLayout());
        colorMesh = new Color[64][64];
        for (var i = 0; i < 64; i++) {

            var b = new Color[64];
            for (var j = 0; j < 64; j++) {
                b[j] = Color.WHITE;
            }
            colorMesh[i] = b;

        }
        var panel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                var g2d = (Graphics2D) g;
                for (var i = 0; i < colorMesh.length; i++) {
                    for (var j = 0; j< colorMesh[0].length; j++) {
                        g2d.setPaint(colorMesh[i][j]);
                        var r = new Rectangle(i * 8, j * 8, 8, 8);
                        g2d.draw(r);
                        g2d.fill(r);

                    }
                }
            }
        };
        var inputPanel = new JPanel(new FlowLayout());
        var textFieldX = new JTextField("0.5");
        var labelX = new JLabel("x:");
        var textFieldY = new JTextField("0.5");
        var labelY = new JLabel("y:");
        var textFieldC = new JTextField("1.0");
        var labelC = new JLabel("c:");
        var scoreText = new JLabel("0 : 0");
        var okMoveButton = new JButton("Move");
        okMoveButton.addActionListener((ActionEvent a) -> {
            okMoveButton.setText(". . .");
            mesh.updateWithMove(new Double[] {
                    Double.parseDouble(textFieldX.getText()),
                    Double.parseDouble(textFieldY.getText()),
                    Double.parseDouble(textFieldC.getText())});
            for (var i = 0; i< 64; i++) {
                for (var j = 0; j < 64; j++) {
                    colorMesh[i][j] = mesh.uvalues[i][j] > 0 ? p1: p2;
                }
            }
            okMoveButton.setText("Move");
            panel.repaint();
        });
        mesh.initMoves(List.of());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(800, 600);
        setTitle("ESG v.0.1");
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setPreferredSize(new Dimension(512,512));
        add(panel, BorderLayout.CENTER);
        inputPanel.add(labelX);
        inputPanel.add(textFieldX);
        inputPanel.add(labelY);
        inputPanel.add(textFieldY);
        inputPanel.add(labelC);
        inputPanel.add(textFieldC);
        inputPanel.add(scoreText);
        inputPanel.add(okMoveButton);
        add(inputPanel, BorderLayout.EAST);
        setVisible(true);
    }
}
