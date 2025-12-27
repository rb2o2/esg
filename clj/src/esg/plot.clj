(ns esg.plot
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io])
  (:import [tech.tablesaw.plotly Plot] 
           [tech.tablesaw.plotly.components Figure]
           [tech.tablesaw.plotly.traces HeatmapTrace]))
(comment (def data
           (with-open [file (io/reader (nth *command-line-args* 0))]
             (-> file
                 (slurp)
                 (csv/read-csv)))))
(defn read-data [slurped]
  (-> slurped
      (csv/read-csv)))


(comment
  (Plot/show
   (Figure.
    (into-array
     HeatmapTrace
     [(.build (HeatmapTrace/builder
               (into-array Object (range 0 (count data)))
               (into-array Object (range 0 (count (nth data 0))))
               (into-array
                (map
                 #(double-array
                   (map Double/parseDouble
                        (let [length (count %)] (take (- length 1) %))))
                 (take (- (count data) 1) data)))))])))
  )

(defn plot-data [read] 
 (Plot/show
  (Figure.
   (into-array
    HeatmapTrace
    [(.build (HeatmapTrace/builder
              (into-array Object (range 0 (count read)))
              (into-array Object (range 0 (count (nth read 0))))
              (into-array
               (map
                #(double-array
                  (map Double/parseDouble
                       (let [length (count %)] (take (- length 1) %))))
                (take (- (count read) 1) read)))))]))) )