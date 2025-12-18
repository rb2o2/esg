(ns esg.plot
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io])
  (:import [tech.tablesaw.plotly Plot] 
           [tech.tablesaw.plotly.components Figure]
           [tech.tablesaw.plotly.traces HeatmapTrace]))
(def data
  (with-open [file (io/reader (nth *command-line-args* 0))]
    (-> file
        (slurp)
        (csv/read-csv))))
(print(count data))
(Plot/show
 (Figure.
  (into-array
   HeatmapTrace
   [(.build (HeatmapTrace/builder
             (into-array Object (range 0 100))
             (into-array Object (range 0 100))
             (into-array 
              (map 
               #(double-array 
                 (map Double/parseDouble 
                      (let [length (count %)] (take (- length 1) %)))) 
               (take (- (count data) 1) data)))) 
            )]
   )))