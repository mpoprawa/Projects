#include <stdio.h>
#include <math.h>
#include <stdbool.h>
#include <stdlib.h>

int conv(int x)
{
    int kod=0;
    for(int i=0; i<4; i++)
    {
        kod+=(((x%6)+1))*(1000/pow(10,i));
        x=x/6;
    }
    return kod;
}

bool match(int kod, int wzor, int red, int white)
{
    int r=0,w=0,var=1000,t_1[4],t_2[4]; 
    for(int i=0; i<4; i++)
    {
        if(kod/var==wzor/var)
        {
            r+=1;
            t_1[i]=0;
            t_2[i]=0;
        }
        else
        {
            t_1[i]=kod/var;
            t_2[i]=wzor/var;
        }
        kod=kod%var;
        wzor=wzor%var;
        var=var/10;
    }
    for(int a=0; a<4; a++)
    {
        for(int b=0; b<4; b++)
        {
            if(t_1[a]==t_2[b] && t_1[a]!=0 && a!=b)
            {
                w+=1;
                t_2[b]=0;
                break;
            }
        }
    }
    //printf("%d %d\n",r,w);
    if(r==red && w==white)
    {
        return true;
    }
    else
    {
        return false;
    }
}

int main(void)
{
    int base[1296],guess,red,white,size=1295,new_size=1295;
    for(int n=0; n<1296; n++)
    {
        base[n]=conv(n);
    }
    for(int z=0; z<8; z++)
    {
        size=new_size;
        guess=base[rand()%size];
        printf("Round %d\n",z+1);
        printf("\n%d\nRed:\n",guess);
        scanf("%d",&red);
        if(red==4)
        {
            printf("I win\n");
            break;
        }
        printf("White:\n");
        scanf("%d",&white);
        new_size=0;
        for(int x=0; x<size+1; x++)
        {
            if(match(base[x],guess,red,white)==true)
            {
                base[new_size]=base[x];
                new_size++;
            }
        }
        if(new_size==0)
        {
            printf("You're cheating");
            break;
        }
    }
    return 0;
}