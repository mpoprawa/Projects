import networkx as nx # type: ignore
import matplotlib.pyplot as plt
import random
import numpy as np

def graph(g):
    g.add_edge(1, 2,rel=0.93,c=200000000)
    g.add_edge(2, 3,rel=0.93,c=200000000)
    g.add_edge(3, 4,rel=0.93,c=200000000)
    g.add_edge(4, 9,rel=0.93,c=200000000)
    g.add_edge(9, 13,rel=0.93,c=200000000)
    g.add_edge(13, 20,rel=0.93,c=200000000)
    g.add_edge(20, 19,rel=0.93,c=200000000)
    g.add_edge(19, 18,rel=0.93,c=200000000)
    g.add_edge(18, 17,rel=0.93,c=200000000)
    g.add_edge(17, 10,rel=0.93,c=200000000)
    g.add_edge(10, 5,rel=0.93,c=200000000)
    g.add_edge(5, 1,rel=0.93,c=200000000)

    g.add_edge(6, 7,rel=0.985,c=200000000)
    g.add_edge(7, 8,rel=0.985,c=200000000)
    g.add_edge(8, 12,rel=0.985,c=200000000)
    g.add_edge(12, 16,rel=0.985,c=200000000)
    g.add_edge(16, 15,rel=0.985,c=200000000)
    g.add_edge(15, 14,rel=0.985,c=200000000)
    g.add_edge(14, 11,rel=0.985,c=200000000)
    g.add_edge(11, 6,rel=0.985,c=200000000)

    g.add_edge(1, 6,rel=0.99,c=500000000)
    g.add_edge(4, 8,rel=0.99,c=500000000)
    g.add_edge(17, 14,rel=0.99,c=500000000)
    g.add_edge(20, 16,rel=0.99,c=500000000)

    g.add_edge(2, 5,rel=0.99,c=500000000)
    g.add_edge(3, 9,rel=0.99,c=500000000)
    g.add_edge(10, 18,rel=0.99,c=500000000)
    g.add_edge(13, 19,rel=0.99,c=500000000)

    g.add_edge(8, 14,rel=0.999,c=500000000)
    g.add_edge(6, 16,rel=0.999,c=500000000)

    #g.add_edge(8, 6,rel=0.99,c=300000000)
    #g.add_edge(14, 16,rel=0.99,c=300000000)
    #g.add_edge(8, 16,rel=0.99,c=300000000)
    #g.add_edge(14, 6,rel=0.99,c=300000000)

def test(g,N,C,size,sumN):
    rem=[]
    A=np.full((size,size),0)
    for edge in g.edges(data=True):
        n=random.random()
        rel=edge[2]["rel"]
        if n>rel:
            rem.append(edge)
    for edge in rem:
        g.remove_edge(edge[0],edge[1])
    if not nx.is_connected(g):
        return 0
    
    for i in range(0,size):
        for j in range(0,size):
            if i!=j:
                path=nx.shortest_path(g,i+1,j+1)
                for k in range(len(path)-1):
                    if path[k]>path[k+1]:
                        A[path[k]-1][path[k+1]-1]+=N[path[k]-1][path[k+1]-1]
                    else:
                        A[path[k+1]-1][path[k]-1]+=N[path[k]-1][path[k+1]-1]

    sumE=0
    for i in range(size):
        for j in range(i):
            a=A[i][j]
            c=C[i][j]
            #print(i," ",j," ",a," ",c)
            if a>c:
                return 0
            if a!=0:
                sumE+=a/(c-a)
    if sumE/sumN>0.25:
        return 0
    return 1

def montecarlo(N,n,size,Cmult,sumN,packetSize):
    g = nx.Graph()
    graph(g)
    C=np.full((size,size),0)
    for edge in g.edges(data=True):
        c=edge[2]["c"]*Cmult
        if edge[0]>edge[1]:
            C[edge[0]-1][edge[1]-1]+=c/packetSize
        else:
            C[edge[1]-1][edge[0]-1]+=c/packetSize

    succ=0
    for i in range(n):
        succ+=test(g.copy(),N,C,size,sumN)
        print(succ)
    return succ,succ/n

Nmult=1
Cmult=1
size=20
packetSize=12000
sumN=0
k1=310
k2=150
N=np.full((size,size),0)
for i in range(size):
    for j in range(size):
        if i==6 or i==8 or i==16 or i==14 or j==6 or j==8 or j==16 or j==14:
            N[i][j]+=k1*Nmult
            sumN+=k1*Nmult
        else:
            N[i][j]+=k2*Nmult
            sumN+=k2*Nmult

print(montecarlo(N,1000,size,Cmult,sumN,packetSize))