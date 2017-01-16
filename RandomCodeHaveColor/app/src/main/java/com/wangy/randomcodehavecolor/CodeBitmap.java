package com.wangy.randomcodehavecolor;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by xhb on 2016/11/29.
 */
public class CodeBitmap {


    //随机数数组
    private static final char[] CHARS = {
            '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm',
            'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };
    //padding值
    private static final int BASE_PADDING_LEFT = 10, RANGE_PADDING_LEFT = 15, BASE_PADDING_TOP = 30, RANGE_PADDING_TOP = 20;
    //random word space and pading_top
    private int base_padding_left = BASE_PADDING_LEFT, range_padding_left = RANGE_PADDING_LEFT,
            base_padding_top = BASE_PADDING_TOP, range_padding_top = RANGE_PADDING_TOP;
    private Random random = new Random();
    private String returncode;
    private int painsize=40;
//线条数
    int line_number=5;
    private int padding_left, padding_top;
    private static CodeBitmap bmpCode;
    public static CodeBitmap getInstance() {
        if(bmpCode == null)
            bmpCode = new CodeBitmap();
        return bmpCode;
    }
    //验证码图片
    public Bitmap cratebitmap(int width,int height) {
        padding_left = 0;
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        //创建验证码
        returncode = crateCode();
        canvas.drawColor(Color.WHITE);

        Paint paint=new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(painsize);

        //画验证码
        for (int i=0;i<returncode.length();i++) {
            randomTextStyle(paint);
            randomPadding();
            canvas.drawText(returncode.charAt(i) + "", padding_left, padding_top, paint);
        }
        //画线条
        for (int i = 0; i < line_number; i++) {
            drawLine(canvas, paint,width,height);
        }

        //画小圆点
        for (int i=0;i<20;i++){
           drawpoint(canvas,paint,width,height);
        }
        canvas.save( Canvas.ALL_SAVE_FLAG );//保存
        canvas.restore();//
        return bitmap;
    }

    //画干扰线
    private void drawLine(Canvas canvas, Paint paint,int width,int height) {
        int color = randomColor();
        int startX = random.nextInt(width);
        int startY = random.nextInt(height);
        int stopX = random.nextInt(width);
        int stopY = random.nextInt(height);
        paint.setStrokeWidth(1);
        paint.setColor(color);
        canvas.drawLine(startX, startY, stopX, stopY, paint);
    }
    //画圆dian
    private void drawpoint(Canvas canvas, Paint paint,int width,int height){
        int color = randomColor();
        paint.setStrokeWidth(1);
        paint.setColor(color);
        int[] rang=getPoint(height,width);
        canvas.drawCircle(rang[0], rang[1],random.nextInt(5),paint);
    }
    // 随机产生点的圆心点坐标
    public static int[] getPoint(int height, int width) {
        int[] tempCheckNum = { 0, 0, 0, 0 };
        tempCheckNum[0] = (int) (Math.random() * width);
        tempCheckNum[1] = (int) (Math.random() * height);
        return tempCheckNum;
    }
    //随机生成文字样式，颜色，粗细，倾斜度
    private void randomTextStyle(Paint paint) {
        int color = randomColor();
        paint.setColor(color);
        paint.setFakeBoldText(random.nextBoolean());  //true为粗体，false为非粗体
        float skewX = random.nextInt(11) / 10;
        skewX = random.nextBoolean() ? skewX : -skewX;
        paint.setTextSkewX(skewX); //float类型参数，负数表示右斜，整数左斜
        //paint.setUnderlineText(true); //true为下划线，false为非下划线
        //paint.setStrikeThruText(true); //true为删除线，false为非删除线
    }
    //随机生成颜色
    private int randomColor() {
        return randomColor(1);
    }

    private int randomColor(int rate) {
        int red = random.nextInt(256) / rate;
        int green = random.nextInt(256) / rate;
        int blue = random.nextInt(256) / rate;
        return Color.rgb(red, green, blue);
    }
    //随机生成padding值
    private void randomPadding() {
        padding_left += base_padding_left + random.nextInt(range_padding_left);
        padding_top = base_padding_top + random.nextInt(range_padding_top);
    }
    //生成验证码
    private String crateCode() {
        StringBuffer returnbf = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            returnbf.append(CHARS[random.nextInt(CHARS.length)]);
        }
        return returnbf.toString();
    }

    //获取验证码的值
    public String getcode(){
        return returncode;
    }
}
