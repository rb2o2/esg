(ns esg.core 
  (:import [io.github.rb2o2.esg Mesh2D])
  (:require (clojure.string)) 
  )
(comment (def data (let [mesh (Mesh2D. (Integer/parseInt (nth *command-line-args* 0))
                               (Integer/parseInt (nth *command-line-args* 1))
                               (Integer/parseInt (nth *command-line-args* 2)))]
             (.initMoves mesh (java.util.List/of
                               (into-array Double [0.4 0.35 1.0])
                               (into-array Double [0.1 0.83 0.5])))
             (Mesh2D/csvPrint (.uvalues mesh)))))

(defn data-points [nx ny nf]
  (fn [c & charges]
    (let [mesh (Mesh2D. nx ny nf)]
      (.initMoves mesh (apply java.util.List/of 
                        (map (fn [x3] (into-array Double x3))
                             (partition 3 (cons c charges))
                             ))
                  )
      (Mesh2D/csvPrint (.uvalues mesh)))
    )
  )

;; (spit (str "../data/" (.toString (java.util.UUID/randomUUID)) ".csv") data)
;; (print ((data-points 64 64 64) 0.18 0.95 1.0 0.34 0.52 1.0 0.78 0.65 1.0))
