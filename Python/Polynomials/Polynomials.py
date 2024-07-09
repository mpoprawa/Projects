def display(X):
    n = len(X)
    for i in range(n):
        print(X[i], end = ""); 
        if (i != 0): 
            print("x^"+str(i), end = ""); 
        if (i != n - 1): 
            print(" + ", end = "");
    print()
    print("------------------")

def empty(X):
    for i in X:
        if i!=0:
            return False
    return True

def multiply(X, Y):
    lx = len(X)
    ly = len(Y) 
    res = [0] * (lx + ly - 1); 
    for i in range(lx): 
        for j in range(ly): 
            res[i + j] += X[i] * Y[j]; 
    return res; 

def subtract(X,Y):
    lx = len(X)
    ly = len(Y)
    res=X.copy()
    if lx<ly:
        for i in range(lx-1,ly-1):
            res.append(-Y[i+1])
    for i in range(lx):
        res[i]-=Y[i]
    while res[-1]==0 and len(res)>1:
        res.pop()
    return res

def add(X,Y):
    lx = len(X)
    ly = len(Y)
    res=X.copy()
    if lx<ly:
        for i in range(lx-1,ly-1):
            res.append(Y[i+1])
    for i in range(lx):
        res[i]+=Y[i]
    while res[-1]==0 and len(res)>1:
        res.pop()
    return res

def div(x,y):
    X=x.copy()
    Y=y.copy()
    LTx = len(X)-1
    LTy = len(Y)-1
    if LTx >= LTy:
        q = [0] * (LTx-LTy+1)
        while LTx >= LTy:
            temp = [0]*(LTx - LTy) + Y
            mult = X[-1] / temp[-1]
            q[LTx-LTy] = mult
            for i in range(len(temp)):
                temp[i]=temp[i]*mult
            for i in range(LTx+1):
                X[i]=X[i]-temp[i]
            while len(X)>0 and X[-1] == 0:
                X.pop()
            LTx = len(X)-1
        if len(X)==0:
            r = [0]
        else:
            r = X
    else:
        q = [0]
        r = X
    return q, r

def nwd(X,Y):
    if empty(X):
        return Y
    if empty(Y):
        return X
    if len(X) < len(Y):
        (q,r) = div(Y,X)
        return nwd(X,r)
    else:
        (q,r) = div(X,Y)
        return nwd(Y,r)

def nww(X,Y):
    n = nwd(X,Y)
    q=n
    q,r = div(multiply(X,Y),n)
    return q

def eucl(X,Y):
    if empty(X) and empty(Y):
        return "brak nwd"
    if len(Y)>len(X):
        a,b=eucl(Y,X)
        return b,a
    if empty(Y):
        return [1],[0]
    q,r=div(X,Y)
    temp=eucl(Y,r)
    return temp[1],subtract(temp[0],multiply(q,temp[1]))

#q,r=div([-42,0,-12,1],[-3,1])
#display(q)
#display(r)
#X = [1,2,1]
#Y = [1,0,1]
#print(div(X,Y))
#display(nwd(X.copy(),Y.copy()))
#display(nww(X,Y))
X = [-4,-4,1,1]
Y = [2,-1,-2,1]
a,b=eucl(X,Y)
print("A")
display(a)
print("B")
display(b)
print("A*X+B*Y")
display(add(multiply(X,a),multiply(Y,b)))
print("NWD(X,Y)")
display(nwd(X,Y))
X = [-1,0,0,1]
Y = [-1,-2,-1,0,1]
a,b=eucl(X,Y)
print("A")
display(a)
print("B")
display(b)
print("A*X+B*Y")
display(add(multiply(X,a),multiply(Y,b)))
print("NWD(X,Y)")
display(nwd(X,Y))