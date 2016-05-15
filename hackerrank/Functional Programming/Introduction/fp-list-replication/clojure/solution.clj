(defn fun [num lst]
  (apply concat (map #(repeat num %) lst))
)

(fun 5 [1 2 3])
