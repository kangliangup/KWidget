package com.k.kwidget.shadow.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;



public class FiveChessView extends View {

    private Paint paint;

    //棋子大小
    public static final int chessSize=200;

    //发光点的偏移大小
    public static final int offset=20;


    enum ChessType{
        BLACK,WHITE
    }

    public FiveChessView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width=getMeasuredWidth();
        int height=getMeasuredHeight();

        //行数
        int rows=height/chessSize;
        //列数
        int cols=width/chessSize;

        //画棋盘
        drawChessBoard(canvas,rows,cols);

        drawChess(canvas,1,1,ChessType.BLACK);

        drawChess(canvas,2,2,ChessType.WHITE);

    }

    /**
     *
     * @param x 第n列
     * @param y 第n行
     * @param chessType 棋子类型  黑色或白色
     */
    private void drawChess(Canvas canvas,int x,int y,ChessType chessType){

        //定义棋子颜色
        int colorOuter=chessType==ChessType.BLACK?Color.BLACK:Color.GRAY;
        int colorInner=Color.WHITE;

        //定义渐变，发光点向右下角偏移
        RadialGradient rg=new RadialGradient(x*chessSize,y*chessSize+offset,chessSize / 1.5f,
                colorInner,colorOuter, Shader.TileMode.CLAMP);

        //给棋子添加阴影
        setLayerType(View.LAYER_TYPE_SOFTWARE,paint);
        paint.setShadowLayer(6,4,4,Color.parseColor("#AACCCCCC"));

        paint.setShader(rg);
        canvas.drawCircle(x*chessSize,y*chessSize,chessSize/2,paint);

    }




    /**
     * 画棋盘
     */
    private void drawChessBoard(Canvas canvas, int rows, int cols){

        paint.setColor(Color.GRAY);

        //取消阴影
        paint.setShadowLayer(0,0,0,Color.GRAY);

        //画横轴
        for (int i = 0; i < rows+1; i++) {
            canvas.drawLine(0,i*chessSize,cols*chessSize,
                    i*chessSize,paint );
        }

        //画纵轴
        for (int i = 0; i < cols+1; i++) {
            canvas.drawLine(i*chessSize,0,i*chessSize,
                    rows*chessSize,paint );
        }

    }


}
