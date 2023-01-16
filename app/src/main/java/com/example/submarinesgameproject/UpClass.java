package com.example.submarinesgameproject;

import android.util.Log;

public class UpClass {

    public static final int Size = 9;

    public static boolean canPlaceHorizontal(int[][] mat,int x,int y , int subSize)  //פעולה שבודקת האם אפשר למקם צוללת במיקום באופן אופקי
    {
        boolean ok=true;
        if(y+subSize-1<=8) {
            for (int i = 0; i < subSize; i++) {
                if (mat[x][y + i] != 0)
                    ok = false;
            }
        }

        else
        {
            ok=false;
        }
        return ok;
    }

    public static Boolean canPlaceVertical(int[][] mat,int x,int y , int subSize) //פעולה שבודקת האם אפשר למקם צוללת במיקום באופן אנכי
    {
        boolean ok=true;
        if(x+subSize-1<=8)
        {
            for (int i=0; i<subSize;i++)
            {
                if(mat[x+i][y]!=0)
                    ok=false;
            }
        }
        else
        {
            ok=false;
        }
        return ok;
    }

    public static void placeSub(int[][] mat,Position p, int subSize) //פעולה המקבלת עצם מסוג position וגודל צוללת וממקמת את הצוללת במיקום ועל פי הכיוון הנמצא בעצם p מטיפוס position
    {
        if(p.isVertical())
        {

            for (int k=-1;k<subSize+1;k++)
            {
                for(int j=-1;j<2;j++)
                {
                    if(0<=p.getX()+k&& p.getX()+k<=8 && 0<=p.getY()+j && p.getY()+j<=8 &&mat[p.getX()+k][p.getY()+j]==0)
                    {
                        Log.d("***verticat***** ",(p.getX() + k) + ":" +(p.getY() + j)+ " ");
                        mat[p.getX() + k][p.getY() + j] = subSize * -1;
                    }

                }
            }
            for(int i=0;i<subSize;i++)
            {
                Log.d("***vertical sub***** ",(p.getX()+i) + ":" +(p.getY())+ " ");
                mat[p.getX()+i][p.getY()]=subSize;
            }
        }
        else
        {

            for (int k=-1;k<2;k++)
            {
                for(int j=-1;j<subSize+1;j++)
                {

                    if(0<=p.getX()+k&& p.getX()+k<=8 && 0<=p.getY()+j && p.getY()+j<=8 &&mat[p.getX()+k][p.getY()+j]==0)
                    {
                        Log.d("***horizntal***** ",(p.getX() + k) + ":" +(p.getY() + j)+ " ");
                        mat[p.getX() + k][p.getY() + j] = subSize * -1;
                    }

                }
            }
            for(int i=0;i<subSize;i++)
            {
                Log.d("***horizntal sub***** ",(p.getX()) + ":" +(p.getY()+i)+ " ");
                mat[p.getX()][p.getY()+i]=subSize;
            }
        }
    }




}
