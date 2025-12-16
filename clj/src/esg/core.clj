(ns esg.core 
  (:import [io.github.rb2o2.esg Mesh2D])
  (:require (clojure.string)) 
  )

(spit (str "../data/" (.toString (java.util.UUID/randomUUID)) ".csv") 
      (let [mesh (Mesh2D. (Integer/parseInt (nth *command-line-args* 0)) 
                          (Integer/parseInt (nth *command-line-args* 1)) 
                          (Integer/parseInt (nth *command-line-args* 2)))] 
        (.initMoves mesh (java.util.List/of 
                          (into-array Double [0.4 0.35 1.0]) 
                          (into-array Double [0.1 0.83 0.5])
                          )
                    )
        (Mesh2D/csvPrint (.uvalues mesh))))
