
programInputFromLine x = map (\n -> n*2) .  map (read :: String -> Int) . words $ x

main = do
   input <- getLine
   print . programInputFromLine $ input
