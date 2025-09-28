package yt.safino.faster;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private final Context context;
    private final Thread.UncaughtExceptionHandler defaultHandler;

    public CrashHandler(Context context) {
        this.context = context.getApplicationContext();
        this.defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        String errorDetails = getErrorDetailsSafely(throwable);
        String deviceInfo = getDeviceInfo();
        String crashReport = deviceInfo + "\n\n" + errorDetails;

        String savedFilePath = saveCrashToScopedStorage(crashReport);

        Intent intent = new Intent(context, DebugActivity.class);
        intent.putExtra("error", crashReport);
        intent.putExtra("filePath", savedFilePath);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}

        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    private String getErrorDetailsSafely(Throwable throwable) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            throwable.printStackTrace(pw);
            String trace = sw.toString().trim();
            return trace.isEmpty() ? "Unknown error occurred. No stack trace." : trace;
        } catch (Exception e) {
            return "Error capturing stack trace: " + e.toString();
        }
    }

    @NonNull
    private String getDeviceInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Time: ").append(getFormattedDate()).append("\n");
        sb.append("Model: ").append(Build.MODEL).append("\n");
        sb.append("Brand: ").append(Build.BRAND).append("\n");
        sb.append("Manufacturer: ").append(Build.MANUFACTURER).append("\n");
        sb.append("Android: ").append(Build.VERSION.RELEASE)
                .append(" (API ").append(Build.VERSION.SDK_INT).append(")").append("\n");
        sb.append("Screen: ").append(getScreenSize()).append("\n");
        sb.append("CPU ABI: ").append(Build.SUPPORTED_ABIS.length > 0 ? Build.SUPPORTED_ABIS[0] : "Unknown").append("\n");
        sb.append("Package: ").append(context.getPackageName()).append("\n");
        return sb.toString();
    }

    private String getFormattedDate() {
        return new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.US).format(new Date());
    }

    private String getScreenSize() {
        try {
            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            float width = dm.widthPixels / dm.xdpi;
            float height = dm.heightPixels / dm.ydpi;
            return String.format(Locale.US, "%.2f inches (%dx%d)", Math.sqrt(width * width + height * height),
                    dm.widthPixels, dm.heightPixels);
        } catch (Exception e) {
            return "Unknown";
        }
    }

    private String saveCrashToScopedStorage(String report) {
        try {
            File dir = new File(context.getExternalFilesDir(null), "crash_report");
            if (!dir.exists()) dir.mkdirs();

            String fileName = "crash_" + System.currentTimeMillis() + ".txt";
            File crashFile = new File(dir, fileName);

            try (OutputStream os = new FileOutputStream(crashFile)) {
                os.write(report.getBytes());
            }

            return crashFile.getAbsolutePath();
        } catch (Exception e) {
            return "Could not save crash report.";
        }
    }
}