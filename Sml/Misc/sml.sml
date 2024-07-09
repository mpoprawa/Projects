fun binomial n 0 = 1
 |  binomial n k = if n<>k then binomial (n-1) (k) + binomial (n-1) (k-1)
                   else 1;

fun add_rows lst1 lst2 = if (length lst1)=1 then [hd lst1 + hd lst2] 
                         else (hd lst1 + hd lst2) ::  add_rows (tl lst1) (tl lst2);

fun pascal n = if n=0 then [1]
               else let val row = pascal (n-1)
                        val row1 = [0] @ row
                        val row2 = row @ [0]
                    in add_rows row1 row2
                    end;

fun binomial2 n k = 
    List.nth (pascal n,k);

fun merge [ ] M = M
  | merge L [ ] = L
  | merge (L as x::xs) (M as y::ys) = if x < y
                                      then x :: (merge xs M)
                                      else y :: (merge L ys);

fun split L = let
                val t = (length L) div 2
              in ( List.take (L,t) , List.drop (L,t) )
              end;

fun sort [ ] = [ ]
  | sort [x] = [x]
  | sort xs = let
                val (ys,zs) = split xs
              in merge (sort ys) (sort zs)
              end;

fun diophantine 0 b = (0,1,b)
  | diophantine a b = let
                        val (x1,y1,z) = diophantine (b mod a) a
                        val x = y1 - (b div a) * x1
                        val y = x1
                       in (x,y,z)
                       end;

fun primeFactors n =
    let fun getPrimeFactors num k = 
        if k * k > num
            then
                if num > 1
                    then [num]
                    else []
                else if num mod k = 0 
                    then k :: getPrimeFactors (num div k) k
                    else getPrimeFactors num (k + 1)
                in getPrimeFactors n 2
                end;

fun gcd a b = if b = 0
                    then a
                    else gcd b (a mod b);

fun coprime a b = if gcd a b = 1 
                    then 1
                    else 0;

fun phi n = let fun getPhi num k = 
                if num > k 
                    then (coprime num k) + (getPhi num (k + 1))
                    else 0
            in getPhi n 1
            end;

fun remove(x, []) = []
  | remove(x, y::l) =
    if x = y then
      remove(x, l)
    else
      y::remove(x, l);

fun unique [] = []
  | unique(x::l) = x::unique(remove(x, l));

fun product lst = if (length lst)=1 
                    then 1.0 - 1.0 / Real.fromInt (hd lst)
                    else (1.0 - 1.0 / Real.fromInt (hd lst)) * (product (tl lst));

fun phi2 n = let 
                val p = unique (primeFactors n)
            in Real.toInt IEEEReal.TO_NEAREST ((Real.fromInt n) * (product p))
            end;

fun isPrime n = let fun is_divisor num =
                    if (num * num > n)
                        then false
                        else if (n mod num = 0)
                                then true
                                else is_divisor (num + 1)
                in (not (is_divisor (2)))
                end;

fun primes n = let fun getPrimes num k = 
                if num >= k 
                    then if (isPrime k)
                            then [k] @ (getPrimes num (k + 1))
                            else (getPrimes num (k + 1))
                    else []
            in getPrimes n 2
            end;

binomial 15 11;
binomial2 15 11;
merge [1,2,3] [2,7,8];
split [1,2,3,4,5];
sort [5,7,1,0,12,33,1,32,23];
diophantine 254 44;
primeFactors 13860;
phi 13860;
phi2 13860;
primes 50;