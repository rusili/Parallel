package com.rooksoto.parallel.utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.activityhub.eventmap.FragmentEventMapPresenter;

import java.util.ArrayList;
import java.util.List;

public class PinView extends SubsamplingScaleImageView {

    private PointF sPin;
    private List<PointF> receivedPointList = new ArrayList<>();
    private List<PointF> sentPointList = new ArrayList<>();
    private Bitmap pin;

    public PinView(Context context) {
        this(context, null);
    }

    public PinView(Context context, AttributeSet attr) {
        super(context, attr);
        initialise("");
    }

    public void addPin(String tag, PointF point) {
        switch (tag) {
            case FragmentEventMapPresenter.RECEIVED_PINS:
                receivedPointList.add(point);
                break;
            case FragmentEventMapPresenter.SENT_PINS:
                sentPointList.add(point);
                break;
        }
        initialise(tag);
        invalidate();
    }

    public void setPin(PointF sPin) {
        this.sPin = sPin;
        initialise("");
        invalidate();
    }

    public PointF getPin() {
        return sPin;
    }

    private void initialise(String tag) {
        if (pin == null) {
            float density = getResources().getDisplayMetrics().densityDpi;
            switch (tag) {
                case FragmentEventMapPresenter.RECEIVED_PINS:
                    pin = BitmapFactory.decodeResource(this.getResources(), R.drawable.pushpin_blue);
                    break;
                case FragmentEventMapPresenter.SENT_PINS:
                    pin = BitmapFactory.decodeResource(this.getResources(), R.drawable.pin);
                    break;
                default:
                    pin = BitmapFactory.decodeResource(this.getResources(), R.drawable.pushpin_blue);
                    break;
            }
            float w = (density / 420f) * pin.getWidth();
            float h = (density / 420f) * pin.getHeight();
            pin = Bitmap.createScaledBitmap(pin, (int) w, (int) h, true);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Don't draw pin before image is ready so it doesn't move around during setup.
        if (!isReady()) {
            return;
        }
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        if (sPin != null && pin != null) {
            drawPin(canvas, paint, sPin);
        }

        for (PointF point : receivedPointList) {
            drawPin(canvas, paint, point);
        }
        for (PointF point : sentPointList) {
            drawPin(canvas, paint, point);
        }

    }

    private void drawPin(Canvas canvas, Paint paint, PointF sPin) {
        PointF vPin = sourceToViewCoord(sPin);
        float vX = vPin.x - (pin.getWidth() / 2);
        float vY = vPin.y - pin.getHeight();
        canvas.drawBitmap(pin, vX, vY, paint);
    }

}