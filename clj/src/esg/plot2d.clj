(ns esg.plot2d
  (:import [javax.swing JFrame]
           [javax.swing JPanel]
           [java.awt BorderLayout]
           [java.awt FlowLayout]
           [java.awt Dimension]
           [javax.swing BorderFactory]
           [java.awt Color]
           [javax.swing WindowConstants]
           [javax.swing JTextField]
           [javax.swing JLabel]
           [javax.swing JButton]
           [io.github.rb2o2.esg Mesh2D]
           [java.util List]
           [java.awt.event ActionListener])
  (:require [esg.plot :as plot]))


(def main-window 
  (let [frame (JFrame.)
        panel (JPanel. (BorderLayout.))
        inputPanel (JPanel. (FlowLayout.))
        textFieldx (JTextField.)
        labelx (JLabel.)
        labely (JLabel.)
        labelc (JLabel.)
        textFieldy (JTextField.)
        textFieldC (JTextField.)
        scoreLabel (JLabel.)
        okMoveButton (JButton.)
        mesh (Mesh2D. 64 64 64)]
    (.initMoves mesh (apply List/of '()) )
    (.setDefaultCloseOperation frame WindowConstants/EXIT_ON_CLOSE) 
    (.setSize frame 800 600)
    (.setText textFieldx "0.5")
    (.setText textFieldy "0.5")
    (.setText textFieldC "1.0")
    (.setText scoreLabel "0 : 0")
    (.setTitle frame "ESG 0.1")
    (.setVisible frame true)
    (.setBorder panel (BorderFactory/createLineBorder Color/BLUE))
    (.setText okMoveButton "Move")
    (.addActionListener okMoveButton 
                        (proxy [ActionListener] [] (actionPerformed [a]
                                           (.updateWithMove mesh (into-array Double
                                                                  [(Double/parseDouble (.getText textFieldx))
                                                                   (Double/parseDouble (.getText textFieldy))
                                                                   (Double/parseDouble (.getText textFieldC))]))
                                           (let [g (.getGraphics panel)
                                                 data (plot/read-data (Mesh2D/csvPrint (.uvalues mesh)))]
                                             (.setText okMoveButton "Moved")))))
    (.setPreferredSize panel (Dimension. 500 500))
    (.setMaximumSize panel (Dimension. 500 500))
    (.add frame panel BorderLayout/CENTER)
    (.setText labelx "x:")
    (.setText labely "y:")
    (.setText labelc "c:")
    (.add inputPanel labelx)
    (.add inputPanel textFieldx )
    (.add inputPanel labely)
    (.add inputPanel textFieldy )
    (.add inputPanel labelc)
    (.add inputPanel textFieldC )
    (.add inputPanel scoreLabel )
    (.add inputPanel okMoveButton)
    (.add frame inputPanel BorderLayout/EAST)
    frame))

;; (main-window)
(Thread/sleep 800)