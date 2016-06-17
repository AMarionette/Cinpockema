package com.c9.cinpockema.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.almeros.android.multitouch.MoveGestureDetector;
import com.c9.cinpockema.R;
import com.c9.cinpockema.adapter.SeatTableView;
import com.c9.cinpockema.model.Constant;
import com.c9.cinpockema.model.FastJsonParser;
import com.c9.cinpockema.model.JsonStringCallBack;
import com.c9.cinpockema.model.NetworkHelper;
import com.c9.cinpockema.model.Seat;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.graphics.Bitmap.createScaledBitmap;


public class SelectSeatActivity extends AppCompatActivity implements OnTouchListener {
    private final static String TAG = SelectSeatActivity.class.getSimpleName();
    private Matrix mMatrix = new Matrix();
    private float mPreScaleFactor = 1.0f;
    private float mScaleFactor = 1.0f;
    private float mPreFocusX = 0.f;
    private float mFocusX = 0.f;
    private float mPreFocusY = 0.f;
    private float mFocusY = 0.f;

    private ScaleGestureDetector mScaleDetector;
    private MoveGestureDetector mMoveDetector;

    SeatTableView seatTableView;
    LinearLayout rowView;
    private Seat[][] seatTable;
    private ArrayList<Seat> seats;//影厅所有座位
    private List<Seat> selectedSeats;//选中的座位

    private int screenWidth;
    private int minLeft;
    private int minTop;
    private int defWidth;
    private AlphaAnimation alpha;

    private LinearLayout showSeatLayout;//显示已选座位

    //call back
    private JsonStringCallBack seatsRequestCallBack = new JsonStringCallBack() {
        @Override
        public void onSuccess(String jsonStr) {
            seats = (ArrayList<Seat>) FastJsonParser.listParse(jsonStr, Seat.class);
            //根据seats初始化座位列表
            initSeatTable();
            //根据seatTable初始化座位列表试图
            initSeatTableView();
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_seat);
        Resources resources = getResources();
        screenWidth = resources.getDisplayMetrics().widthPixels;
        defWidth = resources.getDimensionPixelSize(R.dimen.padding_40dp);
        //获取传递过来的数据
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int cinemaId = bundle.getInt(Constant.CINEMAID);
        int hallId = bundle.getInt(Constant.HALLID);
        //初始化页面控件
        initScreen();

        //初始化actionbar
        initActionBar();
        selectedSeats = new ArrayList<Seat>();
        seatTableView = (SeatTableView) findViewById(R.id.seatviewcont);
        seatTableView.setMinimumHeight(screenWidth/2 );
        rowView = (LinearLayout) findViewById(R.id.seat_row);
        //设置透明度
        alpha = new AlphaAnimation(0.6F, 0.6F);
        alpha.setDuration(0); // Make animation instant
        alpha.setFillAfter(true); // Tell it to persist after the animation ends

        onChanged();
        // Setup Gesture Detectors
        mScaleDetector = new ScaleGestureDetector(this, new ScaleListener());
        mMoveDetector = new MoveGestureDetector(this, new MoveListener());

        //send seats request
        NetworkHelper.sendStrRequest(seatsRequestCallBack, Constant.getSeatsUrl(cinemaId,hallId));
    }

//    //初始化seatTable
//    private void initSeatTable(){
//        seatTable = new Seat[maxRow][maxColumn];
//        for (int i = 0; i < seats.size(); i++) {
//            int coordinateX = seats.get(i).getCoordinateX();
//            int coordinateY = seats.get(i).getCoordinateY();
//            seatTable[coordinateX][coordinateY] = seats.get(i);
//        }
//    }

    //初始化座位视图：根据传入的seatList进行初始化
    private void initSeatTableView() {
        //居中线的画笔
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2f);
        paint.setColor(Color.DKGRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);

        seatTableView.setSeatTable(seatTable);
        seatTableView.setRowSize(maxRow);
        seatTableView.setColumnSize(maxColumn);
        seatTableView.setOnTouchListener(this);
        seatTableView.setLinePaint(paint);
        seatTableView.setDefWidth(defWidth);
    }


    private void initActionBar() {
        //actionbar设置
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ActionBar));
        actionBar.setTitle("MovieName");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.show();
    }
    //初始化页面控件
    private void initScreen() {
        showSeatLayout = (LinearLayout) SelectSeatActivity.this.findViewById(R.id.show_seat_layout);
    }


    //添加座位button
    public Button addSeatButton(Context context, String seatName) {
        Button button = new Button(context);
        button.setText(seatName);
        return button;
    }

    int[] oldClick = new int[2];
    int[] newClick = new int[2];
    boolean eatClick = true;// when drag, ignore click

    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                oldClick = getClickPoint(event);
                eatClick = false;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                eatClick = true;
                break;
            case MotionEvent.ACTION_UP:
                newClick = getClickPoint(event);
                int i = newClick[0];
                int j = newClick[1];
                if (!eatClick && i != -1 && j != -1 && i == oldClick[0] && j == oldClick[1] ) {
                    if (seatTable[i][j].getStatus() == 1) {
                        //如果座位超过4，则不能再选
                        if (selectedSeats.size() > 4) {
                            Toast.makeText(SelectSeatActivity.this, Constant.SEATOVERMAX, Toast.LENGTH_SHORT).show();
                        } else {
                            seatTable[i][j].setStatus(1);
                            selectedSeats.add(seatTable[i][j]);
                            Toast.makeText(this, seatTable[i][j].getName(), Toast.LENGTH_SHORT).show();
                            //添加一个显示已选座位的Button
                            Button button = new Button(SelectSeatActivity.this);
                            button.setText(seatTable[i][j].getName());
                            showSeatLayout.addView(button);
                        }
                    } else {
                        seatTable[i][j].setStatus(1);
                        //index，标记当前点击座位在座位列表的位置，用于移除该button
                        int index = 0;
                        for (int pos = 0; pos < selectedSeats.size();pos++) {
                            if (selectedSeats.get(pos) == seatTable[i][j]) {
                                index = pos;
                            }
                        }
                        showSeatLayout.removeViewAt(index);

                        selectedSeats.remove(seatTable[i][j]);
                    }
                }
                break;
        }


        mScaleDetector.onTouchEvent(event);
        mMoveDetector.onTouchEvent(event);
        float diffScal = Math.abs(mPreScaleFactor - mScaleFactor);
        float diffY = Math.abs(mPreFocusY - mFocusY);
        float diffX = Math.abs(mPreFocusX - mFocusX);
        //Log.i(TAG, "diffScal = " + diffScal + ", preSeatWidth = " + preSeatWidth + ", diffY ＝ " + diffY + ", diffX = " + diffX);
        if (!eatClick || diffY > 5 || diffX > 5 || diffScal > 0.01) {// avoid too many draw
            mMatrix.reset();
            mPreScaleFactor = mScaleFactor;
            mPreFocusY = mFocusY;
            mPreFocusX = mFocusX;
            //drag area
            minLeft = (int) (defWidth * mScaleFactor * maxColumn) - screenWidth;
            mFocusX = minLeft > 0 ?
                    Math.max(-minLeft, Math.min(mFocusX, defWidth * mScaleFactor))
                    : Math.max(0, Math.min(mFocusX, defWidth * mScaleFactor));
            minTop = (int) (defWidth * mScaleFactor * maxRow) - seatTableView.getMeasuredHeight();
            mFocusY = minTop > 0 ? Math.max(-minTop, Math.min(mFocusY, 0)) : 0;
            mMatrix.postScale(mScaleFactor, mScaleFactor);


            seatTableView.mScaleFactor = mScaleFactor;
            seatTableView.mPosX = mFocusX;
            seatTableView.mPosY = mFocusY;

            int seatWidth = (int) (defWidth * mScaleFactor);

            seatTableView.seat_sale = createScaledBitmap(seatTableView.SeatSale, seatWidth, seatWidth, true);
            seatTableView.seat_sold = createScaledBitmap(seatTableView.SeatSold, seatWidth, seatWidth, true);
            seatTableView.seat_selected = createScaledBitmap(seatTableView.SeatSelected, seatWidth, seatWidth, true);
            seatTableView.invalidate();
            onChanged();
        }

        return true;
    }

    //左侧的座位列号
    public void onChanged() {
        rowView.removeAllViews();
        rowView.setPadding(getResources().getDimensionPixelSize(R.dimen.padding_1dp),
                (int) (mFocusY), 0, 0);
        //rowView.setBackgroundColor(getResources().getColor(R.color.black));
//        rowView.startAnimation(alpha);
        for (int i = 0; i < seatTableView.getRowSize(); i++) {
            TextView textView = new TextView(SelectSeatActivity.this);

            for (int j = 0; j < seatTableView.getColumnSize(); j++) {
                if (seatTable[i][j] != null) {
                    textView.setText(Integer.toString(seatTable[i][j].getRow()));
                    break;
                }
            }
            textView.setTextSize(8.0f * mScaleFactor);
            textView.setTextColor(Color.LTGRAY);
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(new ViewGroup.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, (int)(defWidth * mScaleFactor)));
            textView.setPadding(getResources().getDimensionPixelSize(R.dimen.padding_2dp), 0,
                    getResources().getDimensionPixelSize(R.dimen.padding_2dp), 0);
            rowView.addView(textView);
        }
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor(); // scale change since previous event
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 6.0f));

            return true;
        }
    }

    //移动
    private class MoveListener extends MoveGestureDetector.SimpleOnMoveGestureListener {
        @Override
        public boolean onMove(MoveGestureDetector detector) {
            PointF d = detector.getFocusDelta();
            eatClick = d.x > 1 || d.y > 1;
            mFocusX += d.x;
            mFocusY += d.y;

            return true;
        }
    }

    private int maxRow = 10;
    private int maxColumn = 10;
    //初始化，测试用
    private void initSeatTable() {
        seatTable = new Seat[maxRow][maxColumn];// mock data
        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxColumn; j++) {
                Seat seat = new Seat();
                seat.setCoordinateX(i);
                seat.setCoordinateY(j);
                seat.setRow(i + 1);
                seat.setCol(j + 1);
                seat.setName((i + 1) + " Row" + (j + 1) + " Seat");
                seat.setStatus(1);
                seatTable[i][j] = seat.getStatus() == -2 ? null : seat;
            }
        }
    }





    int[] noSeat = {-1, -1};
    //click position(x, y)
    public int[] getClickPoint(MotionEvent event) {
        float currentXPosition = event.getX() - mFocusX;
        float currentYPosition = event.getY() - mFocusY;
        float area = seatTableView.getSeatWidth();
        for (int i = 0; i < seatTableView.getRowSize(); i++) {
            for (int j = 0; j < seatTableView.getColumnSize(); j++) {
                if ((j * area) < currentXPosition
                        && currentXPosition < j * area + area
                        && (i * area) < currentYPosition
                        && currentYPosition < i * area + area
                        && seatTable[i][j] != null
                        && seatTable[i][j].getStatus() >= 1) {

                    return new int[]{i, j};
                }
            }
        }
        return noSeat;
    }

    //actionbar上的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //返回按钮
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
