select i,j,v from (select *, rank() over (partition by i,j order by v) as rank from equ) as temp where rank <=4 and i =5 and j= 1 and d <= 10;

