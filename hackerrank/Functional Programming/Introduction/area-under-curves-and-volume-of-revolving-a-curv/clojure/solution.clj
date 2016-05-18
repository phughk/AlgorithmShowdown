(defn readNumArray []
  (map 
    read-string 
    (clojure.string/split (read-line) #(" "))
  )
)

(defn constructAddend [factor exponent]
  (fn [x] (java.lang.Math/pow (* factor x) exponent)
    )
  )
