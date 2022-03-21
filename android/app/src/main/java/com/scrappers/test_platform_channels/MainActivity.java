package com.scrappers.test_platform_channels;

import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

/**
 * Uses Platform specific channels (from flutter.io) to communicate with flutter Ui.
 *
 * @author pavl_g.
 */
public class MainActivity extends FlutterActivity implements MethodChannel.MethodCallHandler {
    public enum Methods {;
        public final static String GET_OXYGEN_LEVEL = "getOxygenLevel";
        public final static String GET_CO_LEVEL = "getCarbonMonoOxideLevel";
        public final static String GET_NO_LEVEL = "getNitrogenMonoOxideLevel";
    }
    public static final String CHANNEL = "com.scrappers.sensor/data";
    @Override
    public void configureFlutterEngine(@NonNull @NotNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);

        final BinaryMessenger binaryMessenger = flutterEngine.getDartExecutor().getBinaryMessenger();
        new MethodChannel(binaryMessenger, CHANNEL).setMethodCallHandler(this);
    }

    @Override
    public void onMethodCall(@NonNull @NotNull MethodCall call, @NonNull @NotNull MethodChannel.Result result) {
        fetchGasLevels(call.method, result);
    }

    /**
     * Tests fetch gas levels from bluetooth module.
     * @param method the channel method.
     * @param result the channel result to enqueue data on.
     */
    private void fetchGasLevels(final String method, final MethodChannel.Result result) {
        // gas levels fetched from the IC-Module-MQ135
        final int testOxygenLevels = 150;
        final int testNOLevels = 17;
        final int testCOLevels = 10;
        // send data to flutter on the calling channel
        switch (method) {
            case Methods.GET_OXYGEN_LEVEL:
                result.success(String.valueOf(testOxygenLevels));
                return;
            case Methods.GET_CO_LEVEL:
                result.success(String.valueOf(testCOLevels));
                return;
            case Methods.GET_NO_LEVEL:
                result.success(String.valueOf(testNOLevels));
        }
    }
}
