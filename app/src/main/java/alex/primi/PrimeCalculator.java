package alex.primi;


import java.util.ArrayList;
import java.util.List;

public class PrimeCalculator {
    int i,c;
    public boolean isPrime(int n)
    {
        if (n>3) {
            if (n % 2 == 0) return false;
                i = 3;
                c= (int) Math.sqrt(n);
                while (i <= c) {
                    if (n%i==0) return false;
                    i += 2;
                }
                return true;
        }
        else {
            if (n==2 || n==3) return true;
                return false;
        }
    }

    public int nextPrime(int n)
    {
        if (n<2) return 2;
        while (true) {
            if (n%2==0) n+=1;
            else n+=2;
            while (!isPrime(n)) n+=2;
            return n;
        }
    }

    public int previousPrime(int n)
    {
        if (n>2) {
            if (n==3) return 2;
            else {
                if (n%2==0) n-=1;
                else n-=2;
                while(!isPrime(n)) n-=2;
                return n;
            }
        }
        else return 0;
    }

    public int[] primesFrom(int n,int b)
    {
        int count;
        int p[]=new int[b];
        for (count=1;count<=b;count++) {
            p[count-1]=nextPrime(n);
            n=p[count-1];
        }
        return p;
    }

    public List primesBetween(int n,int n2)
    {
        int i;
        List<Integer> p=new ArrayList<>();
        if(n%2==0) n++;
        else n+=2;
        for (i=n;i<n2;i+=2) {
            if (isPrime(i)) {
                p.add(i);
            }
        }
        return p;
    }

    public List factorize(int n)
    {
        int i=0,count;
        List<Integer> p=new ArrayList<>();
        while(n>1) {
            i=nextPrime(i);
            count=0;
            while (n % i == 0) {
                n = n / i;
                count++;
                }
            if (count>0) {
                p.add(i);
                p.add(count);
            }
        }
        return p;
    }

    public int mcd(int n, int n2) {
        int i,i2,app;
        if (n>n2) {
            i=n2;
            i2=n;
        }
        else {
            i=n;
            i2=n2;
        }
        while ((app= i2%i) !=0) {
            i2=i;
            i=app;
        }
        return i;
    }

    public boolean coprimi(int n, int n2)
    {
        if (mcd(n,n2)==1) return true;
        return false;
    }

    public int eulero(int n)
    {
        if (n>0) {
            if (isPrime(n)) return n-1;
            if (n==1) return 1;
            int count = 1, app = 1, i;
            if (n % 2 == 0) app++;
            else count++;
            for (i = 3; i < n; i += app) if (coprimi(i, n)) count++;
            return count;
        }
        return 0;
    }
}
