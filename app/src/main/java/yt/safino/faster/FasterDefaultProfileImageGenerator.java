package yt.safino.faster;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.TypedValue;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

public class FasterDefaultProfileImageGenerator {

    public static Bitmap generate(@NonNull Context context, @NonNull String name, int sizeDp) {
        int sizePx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                sizeDp,
                context.getResources().getDisplayMetrics()
        );

        String trimmedName = name.trim();
        char initial = trimmedName.isEmpty() ? '?' : Character.toUpperCase(trimmedName.charAt(0));
        int bgColor = getColorForName(trimmedName);

        return createBitmapWithInitial(context, initial, bgColor, sizePx);
    }

    private static int getColorForName(String name) {
        int[] colors = {
                Color.parseColor("#F44336"),
                Color.parseColor("#E91E63"),
                Color.parseColor("#9C27B0"),
                Color.parseColor("#673AB7"),
                Color.parseColor("#3F51B5"),
                Color.parseColor("#2196F3"),
                Color.parseColor("#03A9F4"),
                Color.parseColor("#00BCD4"),
                Color.parseColor("#009688"),
                Color.parseColor("#4CAF50"),
                Color.parseColor("#8BC34A"),
                Color.parseColor("#CDDC39"),
                Color.parseColor("#FFEB3B"),
                Color.parseColor("#FFC107"),
                Color.parseColor("#FF9800"),
                Color.parseColor("#FF5722"),
                Color.parseColor("#795548"),
                Color.parseColor("#9E9E9E"),
                Color.parseColor("#607D8B"),
                Color.parseColor("#6A1B9A"),
                Color.parseColor("#1E88E5"),
                Color.parseColor("#43A047"),
                Color.parseColor("#FF7043"),
                Color.parseColor("#5C6BC0"),
                Color.parseColor("#26A69A"),
                Color.parseColor("#D32F2F")
        };

        if (name.isEmpty()) return Color.DKGRAY;

        int hash = Math.abs(name.toLowerCase().hashCode());
        return colors[hash % colors.length];
    }

    private static Bitmap createBitmapWithInitial(Context context, char initial, int bgColor, int sizePx) {
        Bitmap bitmap = Bitmap.createBitmap(sizePx, sizePx, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Paint circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(bgColor);
        canvas.drawCircle(sizePx / 2f, sizePx / 2f, sizePx / 2f, circlePaint);

        Typeface typeface = ResourcesCompat.getFont(context, R.font.varelaround_regular);

        TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTypeface(typeface != null ? typeface : Typeface.DEFAULT_BOLD);
        textPaint.setTextSize(sizePx * 0.5f);
        textPaint.setTextAlign(Paint.Align.CENTER);

        Paint.FontMetrics metrics = textPaint.getFontMetrics();
        float x = sizePx / 2f;
        float y = sizePx / 2f - (metrics.ascent + metrics.descent) / 2;

        canvas.drawText(String.valueOf(initial), x, y, textPaint);
        return bitmap;
    }
}