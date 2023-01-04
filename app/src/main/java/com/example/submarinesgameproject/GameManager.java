package com.example.submarinesgameproject;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
public class GameManager
{
    Random rnd=new Random();

    private Game gameActivity;
    private int [][] board; //הלוח של המחשב
    private int size=9; //גודל הלוח
    private int subNum;//מספר הצוללות


    public GameManager(Game gameActivity,int subNum)
    {
        this.gameActivity = gameActivity;
        this.board=new int[size][size];
        this.subNum=subNum;//מספר הצוללות- אם 4 צוללות הצוללות הן 1 2 3 4 אם 7 צוללות הצוללות הן 1 1 2 3 3 4 4
    }
    public void randomBoard()
    {
        int x;

        ArrayList<Position> arrlst1=new ArrayList<>();
        ArrayList<Position> arrlst2=new ArrayList<>();
        ArrayList<Position> arrlst3=new ArrayList<>();
        ArrayList<Position> arrlst4=new ArrayList<>();
        if(this.subNum==4)
        {
            arrlst4 = possiblePlaces(4);  //מיקום צוללת 4
            x = rnd.nextInt(arrlst4.size());
            placeSub(arrlst4.get(x), 4);
            arrlst4.remove(x);

            arrlst3 = possiblePlaces(3); //מיקום צוללת 3
            x = rnd.nextInt(arrlst3.size());
            placeSub(arrlst3.get(x), 3);
            arrlst3.remove(x);

            arrlst2 = possiblePlaces(2);//מיקום צוללת 2
            x = rnd.nextInt(arrlst2.size());
            placeSub(arrlst2.get(x), 2);
            arrlst2.remove(x);

            arrlst1 = possiblePlaces(1);//מיקום צוללת 1
            x = rnd.nextInt(arrlst1.size());
            placeSub(arrlst1.get(x), 1);
            arrlst1.remove(x);
        }
        else
        {
            for(int i=0; i<2; i++)  //לולאה הממקמת i צוללות באורך 4
            {
                arrlst4 = possiblePlaces(4);  //מיקום צוללת 4
                x = rnd.nextInt(arrlst4.size());
                placeSub(arrlst4.get(x), 4);
                arrlst4.remove(x);
            }

            for(int i=0; i<2; i++) //לולאה הממקמת i צוללות באורך 3
            {
                arrlst3 = possiblePlaces(3); //מיקום צוללת 3
                x = rnd.nextInt(arrlst3.size());
                placeSub(arrlst3.get(x), 3);
                arrlst3.remove(x);
            }

            arrlst2 = possiblePlaces(2);//מיקום צוללת 2
            x = rnd.nextInt(arrlst2.size());
            placeSub(arrlst2.get(x), 2);
            arrlst2.remove(x);

            for(int i=0; i<2; i++) //לולאה הממקמת i צוללות באורך 1
            {
                arrlst1 = possiblePlaces(1);//מיקום צוללת 1
                x = rnd.nextInt(arrlst1.size());
                placeSub(arrlst1.get(x), 1);
                arrlst1.remove(x);
            }
        }

    }


    public int getNum(int i, int j) {
        return board[i][j];
    }

    public int getSize() {
        return size;
    }

    public int[][] getMat()
    {
        return this.board;
    }

    public boolean canPlaceHorizontal(int x,int y , int subSize)  //פעולה שבודקת האם אפשר למקם צוללת במיקום באופן אופקי
    {
        boolean ok=true;
        for (int i=0; i<subSize;i++)
        {
            if(this.board[x][y+i]!=0)
                ok=false;
        }
        return ok;
    }

    public boolean canPlaceVertical(int x,int y , int subSize) //פעולה שבודקת האם אפשר למקם צוללת במיקום באופן אנכי
    {
        boolean ok=true;
        for (int i=0; i<subSize;i++)
        {
            if(this.board[x+i][y]!=0)
                ok=false;
        }
        return ok;
    }

    public ArrayList<Position> possiblePlaces(int subSize) //פעולה שמחזירה arraylist עם כל המיקומים האפשריים לצוללת והכיוונים בהתחשב בגודל הצוללת
    {
        ArrayList<Position> arrlst=new ArrayList<>();
        for (int i=0;i<this.size-subSize;i++)
        {
            for(int j=0; j<this.size; j++)
            {
                if (canPlaceHorizontal(i,j,subSize))
                    arrlst.add(new Position(i,j,false));
            }
        }
        for (int i=0;i<this.size;i++)
        {
            for(int j=0; j<this.size-subSize; j++)
            {
                if (canPlaceVertical(i,j,subSize))
                    arrlst.add(new Position(i,j,true));
            }
        }
        return arrlst;
    }

    public void placeSub(Position p, int subSize) //פעולה המקבלת עצם מסוג position וגודל צוללת וממקמת את הצוללת במיקום ועל פי הכיוון הנמצא בעצם p מטיפוס position
    {
        if(p.isVertical())
        {

            for (int k=-1;k<subSize+1;k++)
                {
                    for(int j=-1;j<2;j++)
                    {
                        if(0<=p.getX()+k&& p.getX()+k<=8 && 0<=p.getY()+j && p.getY()+j<=8 &&this.board[p.getX()+k][p.getY()+j]==0)
                        {
                            this.board[p.getX() + k][p.getY() + j] = subSize * (-1);
                        }

                    }
                }
            for(int i=0;i<subSize;i++)
            {
                this.board[p.getX()+i][p.getY()]=subSize;
            }
        }
        else
        {

            for (int k=-1;k<2;k++)
            {
                for(int j=-1;j<subSize+1;j++)
                {

                    if(0<=p.getX()+k&& p.getX()+k<=8 && 0<=p.getY()+j && p.getY()+j<=8 &&this.board[p.getX()+k][p.getY()+j]==0)
                    {
                        this.board[p.getX() + k][p.getY() + j] = subSize * (-1);
                    }

                }
            }
            for(int i=0;i<subSize;i++)
            {
                this.board[p.getX()][p.getY()+i]=subSize;
            }
        }
    }

    public String show()
    {
            String str = " ";
            for (int i=0;i<9;i++)
            {
                for (int j=0;j<9;j++)
                {
                    str+= " "+ this.board[i][j] + " ";
                }
                str +="\n";
           }
            return str;
    }

}
