import math
import random
import matplotlib.pyplot as plt
import numpy as np

def los(n):
    l1=0
    l2=0
    urns1=[0]*n
    urns2=[0]*n
    for i in range (n):
        k1=random.randint(0,n-1)
        k2=random.randint(0,n-1)
        urns1[k1]+=1
        if(urns1[k1]>l1):
                l1=urns1[k1]
        if (urns2[k2]>urns2[k1]):
            urns2[k1]+=1
            if(urns2[k1]>l2):
                l2=urns2[k1]
        else:
            urns2[k2]+=1
            if(urns2[k2]>l2):
                l2=urns2[k2]
    return l1,l2

def lostest():
    L1=[]
    L2=[]
    for i in range (100):
        n=(i+1)*1000
        print(n)
        for j in range (50):
            (l1,l2)=los(n)
            #print(l1,l2)
            L1.append(l1)
            L2.append(l2)
    np.savetxt('l1.txt',L1,fmt='%d')
    np.savetxt('l2.txt',L2,fmt='%d')

def sort(n):
    comp=0
    swap=0
    arr=[0]*n
    
    for i in range(n): 
        arr[i] = i + 1
    random.shuffle(arr)

    for j in range(1,n):
        key=arr[j]
        i=j-1
        comp+=1
        while (arr[i]>key):
            arr[i+1]=arr[i]
            i=i-1
            swap+=1
            if (i<0):
                break
            comp+=1
        arr[i+1]=key

    return comp,swap

def sortest():
    Cmp=[]
    S=[]
    for i in range (100):
        n=(i+1)*100
        print(n)
        for j in range (50):
            (cmp,s)=sort(n)
            print(j)
            Cmp.append(cmp)
            S.append(s)
    np.savetxt('cmp.txt',Cmp,fmt='%d')
    np.savetxt('s.txt',S,fmt='%d')

def load(name):
    file=open(name)
    file=file.read().split()
    data=[int(x) for x in file]
    return data

def graph(data,inc,size):
    xAxis=[]
    xAvg=[]
    avg=giveAvg(data)
    for i in range(100):
        n=((i+1)*inc)
        xAvg.append(n)
        for j in range(50):
            xAxis.append(n)
    plt.figure()
    plt.scatter(xAxis,data,s=size)
    plt.scatter(xAvg,avg,s=1,color='red')
    plt.ticklabel_format(style='plain')

def graph2(data,f,inc):
    val=[]
    xAxis=[]
    for i in range(100):
        n=(i+1)*inc
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
    return num/(math.log(n)/math.log(math.log(n)))

def div2(num,n):
    return num/(math.log(math.log(n))/math.log(2))

def div3(num,n):
    return num/n

def div4(num,n):
    return num/(n*n)

#lostest()

#L1=load('l1.txt')
#L2=load('l2.txt')
#l1n=giveAvg(L1)
#l2n=giveAvg(L2)

#graph(L1,1000,0.5)
#graph(L2,1000,0.5)
#graph2(l1n,div1,1000)
#graph2(l2n,div2,1000)

#sortest()

cmp=load('cmp.txt')
s=load('s.txt')
cmpn=giveAvg(cmp)
sn=giveAvg(s)

graph(cmp,100,0.05)
graph(s,100,0.05)
graph2(cmpn,div3,100)
graph2(sn,div3,100)
graph2(cmpn,div4,100)
graph2(sn,div4,100)

plt.show()