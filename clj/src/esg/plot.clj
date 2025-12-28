(ns esg.plot
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [esg.core :as core])
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
               (into-array Object (range 0.0 (/ 1.0 (count data))))
               (into-array Object (range 0.0 (/ 1.0 (count (nth data 0)))))
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
              (into-array Object 
                          (map str 
                               (range                                 
                                0.0 1.0 (/ 1.0 (count read)))
                               ))
              (into-array Object 
                          (map str 
                               (range 
                                0.0 1.0 (/ 1.0 (count (nth read 0))))
                               ))
              (into-array
               (map
                #(double-array
                  (map Double/parseDouble %
                       ))
                read))))]))) )

(let [d (read-data ((core/data-points 100 100 100) 0.18 0.95 1.0 0.34 0.52 1.0 0.78 0.65 1.0))] 
    (plot-data d))
(Thread/sleep 800)