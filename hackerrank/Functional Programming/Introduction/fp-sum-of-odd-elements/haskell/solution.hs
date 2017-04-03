
mod2 n = mod n 2
isNotPar n = (mod2 n) /= 0
f arr = sum (filter isNotPar arr)

-- This part handles the Input/Output and can be used as it is. Do not change or modify it.
main = do
   inputdata <- getContents
   putStrLn $ show $ f $ map (read :: String -> Int) $ lines inputdata
