(fn[lst] 
  (defn isInIndex [item-index index]
    (=
     (mod (- 1 (nth item-index 1)) index)
     0
     )
    )

  (defn filterByIndex [coll index]
    (filter
      #(isInIndex % index)
      (map #(vector %1 %2) coll (range))
      )
    )

  (defn fun [lst]
    (apply concat
           (map
             #(list (nth % 0))
             (filterByIndex lst 2)
             )
           )
    )

  (fun lst)
  )
