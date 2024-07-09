import math
import random
import matplotlib.pyplot as plt
def los(a,b,M,n,f):
    count=0
    for i in range (n):
        x=random.uniform(a,b)
        y=random.uniform(0,M)
        if(y<=f(x)):
            count+=1
    return count*(b-a)*M/n
    
def f1(x):
    return pow(x,1/3)

def f2(x):
    return math.sin(x)

def f3(x):
    return 4*x*pow(1-x,3)

def f4(x):
    return 2*math.pow(1-x*x,1/2)

def cal(a,b,M,f):
    value=[]
    scale=[]
    avg=[]
    avg_scale=[]
    for i in range (100):
        x=0
        t=[]
        for j in range(50):
            t.append(los(a,b,M,50*(i+1),f))
            x+=t[j]
        value+=[t]
        scale += [50*(i+1) for _ in t]
        avg+=[x/50]
        avg_scale+=[50*(i+1)]
    return [scale,value,avg_scale,avg]

plt.figure(1)
(x,y,a_s,a)=cal(0,8,f1(8),f1)
plt.plot([50,5000],[12,12],color='green')
plt.scatter(x,y,s=0.1)
plt.scatter(a_s,a,s=5,color='red')
plt.figure(2)
(x,y,a_s,a)=cal(0,math.pi,1,f2)
plt.plot([50,5000],[2,2],color='green')
plt.scatter(x,y,s=0.1)
plt.scatter(a_s,a,s=5,color='red')
plt.figure(3)
(x,y,a_s,a)=cal(0,1,f3(0.25),f3)
plt.plot([50,5000],[0.2,0.2],color='green')
plt.scatter(x,y,s=0.1)
plt.scatter(a_s,a,s=5,color='red')
plt.figure(4)
(x,y,a_s,a)=cal(-1,1,2,f4)
plt.plot([50,5000],[math.pi,math.pi],color='green')
plt.scatter(x,y,s=0.1)
plt.scatter(a_s,a,s=5,color='red')
plt.show()