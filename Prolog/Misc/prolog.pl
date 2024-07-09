mergesort([], []). 
mergesort([X], [X]).
mergesort([X, Y | Rest], S) :-
    divide([X, Y | Rest], L1, L2),
    mergesort(L1, S1),
    mergesort(L2, S2),
    merge(S1, S2, S).

divide([], [], []).
divide([X], [X], []).
divide([X, Y | R], [X | Rx], [Y | Ry]) :-  divide(R, Rx, Ry).

merge(X, [], X).
merge([], Y, Y).
merge([X | Rx], [Y | Ry], [X | M]) :-
    X =< Y,
    merge(Rx, [Y | Ry], M).
merge([X | Rx], [Y | Ry], [Y | M]) :-
    X > Y,
    merge([X | Rx], Ry, M).

de(0,B,X,Y,Z) :- X is 0, Y is 1, Z is B.
de(A,B,X,Y,Z) :- A>0, B1 is B mod A, de(B1,A,X1,Y1,Z1),
    X is Y1 - (B div A) * X1,
    Y is X1,
    Z is Z1.

prime_factors(N, L) :-
    findall(D, prime_factor(N, D), L).

prime_factor(N, D) :-
    find_prime_factor(N, 2, D).

find_prime_factor(N, D, D) :- 0 is N mod D.
find_prime_factor(N, D, R) :- D < N,
    (0 is N mod D
    -> (N1 is N/D, find_prime_factor(N1, D, R))
    ;  (D1 is D + 1, find_prime_factor(N, D1, R))
    ).

gcd(X,0,X) :- X > 0.
gcd(X,Y,G) :- Y > 0, Z is X mod Y, gcd(Y,Z,G).

totient(1,1) :- !.
totient(N,Phi) :- phi(N,Phi,1,0).

phi(N,Phi,N,Phi) :- !.
phi(N,Phi,K,C) :- K < N, 
    gcd(K,N,1), !, 
    C1 is C + 1, K1 is K + 1,
    phi(N,Phi,K1,C1).
phi(N,Phi,K,C) :- K < N,
    K1 is K + 1,
    phi(N,Phi,K1,C).

divisible(X,Y) :- 0 is X mod Y, !.
divisible(X,Y) :- X > Y*Y, divisible(X, Y+1).

isPrime(2) :- true,!.
isPrime(X) :- X < 2,!,false.
isPrime(X) :- not(divisible(X, 2)).

primes(0, []) :- !.
primes(N, [N|L]) :-
    isPrime(N),
    !,
    N1 is N - 1,
    primes(N1, L).
primes(N, L) :-
    N1 is N - 1,
    primes(N1, L).