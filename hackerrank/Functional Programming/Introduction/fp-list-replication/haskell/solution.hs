f :: Int -> [Int] -> [Int]
f _ [] = []
f n arr = (replicate n (head arr))++(f n (tail arr))

-- This part handles the Input and Output and can be used as it is. Do not modify this part.
main :: IO ()
main = getContents >>=
       mapM_ print. (\(n:arr) -> f n arr). map read. words
