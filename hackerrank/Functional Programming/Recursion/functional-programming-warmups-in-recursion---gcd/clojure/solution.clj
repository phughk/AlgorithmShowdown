(defn gcd [left right]
  (if (= left right)
    left
    (do
      (def x (max left right))
      (def y (min left right))
      (gcd (- x y) y)
      ))
  )
