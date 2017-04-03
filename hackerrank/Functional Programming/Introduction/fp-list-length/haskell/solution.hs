--Only fill up the blanks for the function named len
--Do not modify the structure of the template in any other way
len :: [a] -> Int
len [] = 0
len lst = 1 + (len (tail lst))

main = print(len [1,2,3])
