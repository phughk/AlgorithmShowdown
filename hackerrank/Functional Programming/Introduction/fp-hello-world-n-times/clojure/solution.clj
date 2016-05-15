;(apply println (repeat (Integer/parseInt (read-line)) "Hello World"))

(doseq [i (range (Integer/parseInt (read-line)))]
  (println "Hello World"))
