f :: [Int] -> [Int]
f [] = []
f lst = let (_ : b : rem) = lst in [b]++(f rem)

-- This part deals with the Input and Output and can be used as it is. Do not modify it.
main = do
   inputdata <- getContents
   mapM_ (putStrLn. show). f. map read. lines $ inputdata
