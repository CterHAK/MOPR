package vn.iotstar.recyclerviewwithindicator.decorator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LinePagerIndicatorDecoration extends RecyclerView.ItemDecoration {
    private final float DP;
    private final int colorActive = 0xFF000000; // Màu đen cho indicator được chọn
    private final int colorInactive = 0x66000000; // Màu xám cho indicator chưa chọn

    private final float indicatorHeight = 16; // Chiều cao indicator
    private final float indicatorStrokeWidth = 4; // Độ dày của indicator
    private final float indicatorItemPadding = 8; // Khoảng cách giữa các dấu chấm indicator

    private final Paint paint = new Paint();

    public LinePagerIndicatorDecoration(Context context) {
        this.DP = context.getResources().getDisplayMetrics().density;
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(indicatorStrokeWidth * DP);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
    }

    @Override
    public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int itemCount = parent.getAdapter().getItemCount();
        if (itemCount == 0) return;

        float totalLength = (indicatorStrokeWidth + indicatorItemPadding) * DP * itemCount;
        float paddingBetweenItems = Math.max(0, itemCount - 1) * indicatorItemPadding * DP;
        float indicatorTotalWidth = totalLength + paddingBetweenItems;
        float startX = (parent.getWidth() - indicatorTotalWidth) / 2f;
        float posY = parent.getHeight() - indicatorHeight * DP;

        drawInactiveDots(canvas, startX, posY, itemCount);

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (!(layoutManager instanceof LinearLayoutManager)) return;

        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        int activePosition = linearLayoutManager.findFirstVisibleItemPosition();
        if (activePosition == RecyclerView.NO_POSITION) return;

        View activeChild = linearLayoutManager.findViewByPosition(activePosition);
        if (activeChild == null) return;

        float progress = (activeChild.getLeft() * -1f) / activeChild.getWidth();
        drawActiveDot(canvas, startX, posY, activePosition, progress);
    }

    private void drawInactiveDots(Canvas canvas, float startX, float posY, int itemCount) {
        paint.setColor(colorInactive);
        float itemWidth = (indicatorStrokeWidth + indicatorItemPadding) * DP;
        for (int i = 0; i < itemCount; i++) {
            float x = startX + itemWidth * i;
            canvas.drawLine(x, posY, x + indicatorStrokeWidth * DP, posY, paint);
        }
    }

    private void drawActiveDot(Canvas canvas, float startX, float posY, int activePosition, float progress) {
        paint.setColor(colorActive);
        float itemWidth = (indicatorStrokeWidth + indicatorItemPadding) * DP;
        float highlightStart = startX + itemWidth * activePosition;
        canvas.drawLine(highlightStart, posY, highlightStart + indicatorStrokeWidth * DP, posY, paint);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.bottom = (int) (indicatorHeight * DP);
    }
}
