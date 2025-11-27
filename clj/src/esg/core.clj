(ns esg.core
  ;; (:import [io.github.rb2o2.esg RegularizedDirac])
  ;; (:import [io.github.rb2o2.esg App])
  (:import [io.github.rb2o2.esg Mesh2D])
  (:require (clojure.string))
  ;; (:import [alglib alglib])
  )

;; (print (RegularizedDirac/rd 0.145 0.12 0.5 0.5 0.15))r54
;; (App/main (into-array String []))
;; (print (alglib/dawsonintegral 0.25 nil))
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
