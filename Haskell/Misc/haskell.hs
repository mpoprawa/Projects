import Data.Time
import qualified Data.Set as Set

binomial :: Int -> Int -> Int
binomial n 0 = 1
binomial n k = if n/=k then binomial (n-1) (k) + binomial (n-1) (k-1)
               else 1

pascal :: Int -> [Int]
pascal 0 = [1]
pascal n = zipWith (+) ([0] ++ pascal (n-1)) (pascal (n-1) ++ [0])

binomial2 :: Int -> Int -> Int
binomial2 n k = pascal (n) !! k

split :: [a] -> ([a],[a])
split xs = (take half xs, drop half xs)
    where half = length xs `div` 2

merge :: Ord a => [a] -> [a] -> [a]
merge xs [] = xs
merge [] ys = ys
merge (x:xs) (y:ys)
  | x <= y = x : merge xs (y:ys)
  | otherwise = y : merge (x:xs) ys

merge_sort :: Ord a => [a] -> [a]
merge_sort [] = []
merge_sort [x] = [x]
merge_sort xs = merge (merge_sort (fst halves)) (merge_sort (snd halves))
    where halves = split xs

diophantine :: Int -> Int -> (Int,Int,Int)
diophantine 0 b = (0,1,b)
diophantine a b = (x,y,z) where
    (x1,y1,z)=diophantine (b `mod` a) (a)
    x = y1 - (b `div` a) *x1
    y = x1

prime_factors :: Int -> [Int]
prime_factors 1 = []
prime_factors n
    | factor == [] = [n]
    | otherwise = factor ++ prime_factors (n `div` (head factor))
    where factor = take 1 [x | x <- [2..n], n `mod` x == 0]

coprime :: Int -> Int -> Bool
coprime n m = (gcd n m) == 1

totient :: Int -> Int
totient n = length [x | x <- [1..n], coprime x n]

totient2:: Int -> Int
totient2 m = n where
    factors = prime_factors m
    distinct = unique factors
    n = round (fromIntegral(m) * product [(1 - 1/fromIntegral(p))  | p <- distinct])

unique :: Ord a => [a] -> [a]
unique = Set.toList . Set.fromList

isPrime :: Int->Bool
isPrime n = (prime_factors n) == [n]

primes :: Int->[Int]
primes n = [x | x<-[2..n], isPrime x]

main = do
    startTime <- getCurrentTime
    print(binomial 15 11)
    endTime <- getCurrentTime
    let diff = diffUTCTime endTime startTime
    putStrLn $ "Time: " ++ (show diff)

    startTime <- getCurrentTime
    print(binomial2 15 11)
    endTime <- getCurrentTime
    let diff = diffUTCTime endTime startTime
    putStrLn $ "Time: " ++ (show diff)

    print(merge_sort[5,1,2,4,7,8,11,0,-1])
    print(diophantine 254 44)
    print(prime_factors 13860)

    startTime <- getCurrentTime
    print(totient 13860)
    endTime <- getCurrentTime
    let diff = diffUTCTime endTime startTime
    putStrLn $ "Time: " ++ (show diff)
    
    startTime <- getCurrentTime
    print(totient2 13860)
    endTime <- getCurrentTime
    let diff = diffUTCTime endTime startTime
    putStrLn $ "Time: " ++ (show diff)

    print(primes(50))