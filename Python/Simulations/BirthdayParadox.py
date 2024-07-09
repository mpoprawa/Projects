import math
import random
import matplotlib.pyplot as plt
import numpy as np

def los(n):
    foundB=False
    foundD=False
    count=1
    countC=0
    countD=0
    urns=[0]*n
    while foundD==False:
        k=random.randint(0,n-1)
        urns[k]+=1
        #find U
        if count==n:
            U=n-countC
        #find C
        if urns[k]==1:
            countC+=1
            if countC==n:
                C=count
        #find B&D
        elif urns[k]==2:
            countD+=1
            if foundB==False:
                foundB=True
                B=count
            elif countD==n:
                D=count
                foundD=True
        count+=1 
    return B,U,C,D,D-C

def test():
    B=[]
    U=[]
    C=[]
    D=[]
    dC=[]
    for i in range (100):
        n=(i+1)*1000
        print(n)
        for j in range (50):
            (b,u,c,d,dc)=los(n)
            B.append(b)
            U.append(u)
            C.append(c)
            D.append(d)
            dC.append(dc)
    np.savetxt('b.txt',B,fmt='%d')
    np.savetxt('u.txt',U,fmt='%d')
    np.savetxt('c.txt',C,fmt='%d')
    np.savetxt('d.txt',D,fmt='%d')
    np.savetxt('dc.txt',dC,fmt='%d')

def load(name):
    file=open(name)
    file=file.read().split()
    data=[int(x) for x in file]
    return data

def graph(data,size):
    xAxis=[]
    xAvg=[]
    avg=giveAvg(data)
    for i in range(100):
        n=((i+1)*1000)
        xAvg.append(n)
        for j in range(50):
            xAxis.append(n)
    plt.figure()
    plt.scatter(xAxis,data,s=size)
    plt.scatter(xAvg,avg,s=1,color='red')
    plt.ticklabel_format(style='plain')

def graph2(data,f):
    val=[]
    xAxis=[]
    for i in range(100):
        n=(i+1)*1000
        xAxis.append(n)
        val.append(f(data[i],n))
    plt.figure()
    plt.scatter(xAxis,val,s=2)

def giveAvg(data):
    avg=[]
    for i in range(100):
        n=((i+1)*1000)
        sum=0
        for j in range(50):
            sum+=data[i*50+j]
        avg.append(sum/50)
    return avg

def div1(num,n):
    return num/n

def div2(num,n):
    return num/math.sqrt(n)

def div3(num,n):
    return num/(n*math.log(n))

def div4(num,n):
    return num/(n*n)

def div5(num,n):
    return num/(n*math.log(math.log(n)))

test()

B=load('b.txt')
U=load('u.txt')
C=load('c.txt')
D=load('d.txt')
dC=load('dc.txt')
Bn=giveAvg(B)
Un=giveAvg(U)
Cn=giveAvg(C)
Dn=giveAvg(D)
dCn=giveAvg(dC)

graph(B,0.05)
graph(U,0.5)
graph(C,0.01)
graph(D,0.01)
graph(dC,0.05)

graph2(Bn,div1)
graph2(Bn,div2)

graph2(Un,div1)

graph2(Cn,div1)
graph2(Cn,div3)
graph2(Cn,div4)

graph2(Dn,div1)
graph2(Dn,div3)
graph2(Dn,div4)

graph2(dCn,div1)
graph2(dCn,div3)
graph2(dCn,div5)

plt.show()