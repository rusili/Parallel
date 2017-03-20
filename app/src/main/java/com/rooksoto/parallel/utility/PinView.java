/*
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

    private PointF pinPoint;
    private List<PointF> receivedPointList = new ArrayList<>();
    private List<PointF> sentPointList = new ArrayList<>();
    private Bitmap sentPin;
    private Bitmap receivedPin;

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

    public void setPin(PointF pinPoint) {
        this.pinPoint = pinPoint;
        receivedPointList.add(new PointF(293.5547f, 1392.5f));
        receivedPointList.add(new PointF(890.15625f, 1356.875f));
        receivedPointList.add(new PointF(866.25f, 1547.5f));
        receivedPointList.add(new PointF(1221.3281f, 1079.375f));
        initialise("");
        invalidate();
    }

    public PointF getPin() {
        return pinPoint;
    }

    private void initialise(String tag) {
        if (sentPin == null || receivedPin == null) {
            float density = getResources().getDisplayMetrics().densityDpi;
            switch (tag) {
                case FragmentEventMapPresenter.RECEIVED_PINS:
                    receivedPin = BitmapFactory.decodeResource(this.getResources(), R.drawable.pin);
                    receivedPin = createPinBitmaps(density, receivedPin);
                    break;
                case FragmentEventMapPresenter.SENT_PINS:
                    sentPin = BitmapFactory.decodeResource(this.getResources(), R.drawable.pushpin_blue);
                    sentPin = createPinBitmaps(density, sentPin);
                    break;
                default:
                    sentPin = BitmapFactory.decodeResource(this.getResources(), R.drawable.pushpin_blue);
                    sentPin = createPinBitmaps(density, sentPin);
                    break;
            }
        }
    }

    private Bitmap createPinBitmaps(float density, Bitmap pin) {
        float w = (density / 420f) * pin.getWidth();
        float h = (density / 420f) * pin.getHeight();
        return Bitmap.createScaledBitmap(pin, (int) w, (int) h, true);
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

        // Not sure if I need this
        if (sentPin != null && pinPoint != null) {
            drawPin(canvas, paint, pinPoint, "none");
        }

        for (PointF point : receivedPointList) {
            drawPin(canvas, paint, point, FragmentEventMapPresenter.RECEIVED_PINS);
        }

        for (PointF point : sentPointList) {
            drawPin(canvas, paint, point, FragmentEventMapPresenter.SENT_PINS);
        }

    }

    private void drawPin(Canvas canvas, Paint paint, PointF point, String tag) {
        Bitmap pin;
        PointF vPin = sourceToViewCoord(point);
        switch (tag) {
            case FragmentEventMapPresenter.RECEIVED_PINS:
                pin = receivedPin;
                break;
            case FragmentEventMapPresenter.SENT_PINS:
                pin = sentPin;
                break;
            default:
                pin = sentPin;
                break;
        }

        float vX = vPin.x - (pin.getWidth() / 2);
        float vY = vPin.y - pin.getHeight();
        canvas.drawBitmap(pin, vX, vY, paint);
    }

}*/

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

    private PointF pinPoint;
    private List<PointF> receivedPointList = new ArrayList<>();
    private List<PointF> sentPointList = new ArrayList<>();
    private Bitmap sentPin;
    private Bitmap receivedPin;

    public PinView(Context context) {
        this(context, null);
    }

    public PinView(Context context, AttributeSet attr) {
        super(context, attr);
        initialise();
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
        initialise();
        invalidate();
    }

    public void setPin(PointF pinPoint) {
        this.pinPoint = pinPoint;
        initialise();
        invalidate();
    }

    public PointF getPin() {
        return pinPoint;
    }

    private void initialise() {
        float density = getResources().getDisplayMetrics().densityDpi;

        if (sentPin == null) {
            sentPin = BitmapFactory.decodeResource(this.getResources(), R.drawable.sentpin);
            sentPin = createPinBitmaps(density, sentPin);
        }

        if (receivedPin == null) {
            receivedPin = BitmapFactory.decodeResource(this.getResources(), R.drawable.receivedpin);
            receivedPin = createPinBitmaps(density, receivedPin);
        }
    }

    private Bitmap createPinBitmaps(float density, Bitmap pin) {
        float w = (density / 420f) * pin.getWidth();
        float h = (density / 420f) * pin.getHeight();
        return Bitmap.createScaledBitmap(pin, (int) w, (int) h, true);
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

        // Not sure if I need this
        if (sentPin != null && pinPoint != null) {
            drawPin(canvas, paint, pinPoint, "none");
        }

        for (PointF point : receivedPointList) {
            drawPin(canvas, paint, point, FragmentEventMapPresenter.RECEIVED_PINS);
        }

        for (PointF point : sentPointList) {
            drawPin(canvas, paint, point, FragmentEventMapPresenter.SENT_PINS);
        }

    }

    private void drawPin(Canvas canvas, Paint paint, PointF point, String tag) {
        Bitmap pin;
        PointF vPin = sourceToViewCoord(point);
        switch (tag) {
            case FragmentEventMapPresenter.RECEIVED_PINS:
                pin = receivedPin;
                break;
            case FragmentEventMapPresenter.SENT_PINS:
                pin = sentPin;
                break;
            default:
                pin = sentPin;
                break;
        }

        float vX = vPin.x - (pin.getWidth() / 2);
        float vY = vPin.y - pin.getHeight();
        canvas.drawBitmap(pin, vX, vY, paint);
    }

}
