package com.shiplyreactnativeupgradedemo;

import android.app.Application;
import android.util.Log;
import com.facebook.common.logging.FLog;
import com.facebook.common.logging.LoggingDelegate;
import com.facebook.react.PackageList;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint;
import com.facebook.react.defaults.DefaultReactNativeHost;
import com.facebook.soloader.SoLoader;
import java.util.List;
import com.rnshiplyupgrade.ShiplyReactNativeUpgradeModule;


public class MainApplication extends Application implements ReactApplication {

  private final ReactNativeHost mReactNativeHost =
      new DefaultReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
          return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
          @SuppressWarnings("UnnecessaryLocalVariable")
          List<ReactPackage> packages = new PackageList(this).getPackages();
          // Packages that cannot be autolinked yet can be added manually here, for example:
          // packages.add(new MyReactNativePackage());
          return packages;
        }

        @Override
        protected String getJSMainModuleName() {
          return "index";
        }

        @Override
        protected boolean isNewArchEnabled() {
          return BuildConfig.IS_NEW_ARCHITECTURE_ENABLED;
        }

        @Override
        protected Boolean isHermesEnabled() {
          return BuildConfig.IS_HERMES_ENABLED;
        }

        @Override
        protected String getJSBundleFile() {
            String result = null;
            // 从shiply获取RN bundle路径，业务方需要将资源key修改为中创建的资源key名称
            result = ShiplyReactNativeUpgradeModule.Companion.getJSBundleFilePath(getApplicationContext(), "testRNExample");
            Log.d("TestRN72", "getJSBundleFile result = " + result);
            return result;

        }
      };

  @Override
  public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    SoLoader.init(this, /* native exopackage */ false);
    if (BuildConfig.IS_NEW_ARCHITECTURE_ENABLED) {
      // If you opted-in for the New Architecture, we load the native entry point for this app.
      DefaultNewArchitectureEntryPoint.load();
    }
    ReactNativeFlipper.initializeFlipper(this, getReactNativeHost().getReactInstanceManager());
    FLog.setLoggingDelegate(new LoggingDelegate() {
          private int logLevel = FLog.INFO;  // 默认日志级别
          private final String prefix = "TestFlog_";

          @Override
          public void setMinimumLoggingLevel(int level) {
              logLevel = level;
          }

          @Override
          public int getMinimumLoggingLevel() {
              return logLevel;
          }

          @Override
          public boolean isLoggable(int level) {
              return level >= logLevel;
          }

          @Override
          public void v(String tag, String msg) {
              log(FLog.VERBOSE, tag, msg);
          }

          @Override
          public void v(String tag, String msg, Throwable tr) {
              log(FLog.VERBOSE, tag, msg + "\n" + Log.getStackTraceString(tr));
          }

          @Override
          public void d(String tag, String msg) {
              log(FLog.DEBUG, tag, msg);
          }

          @Override
          public void d(String tag, String msg, Throwable tr) {
              log(FLog.DEBUG, tag, msg + "\n" + Log.getStackTraceString(tr));
          }

          @Override
          public void i(String tag, String msg) {
              log(FLog.INFO, tag, msg);
          }

          @Override
          public void i(String tag, String msg, Throwable tr) {
              log(FLog.INFO, tag, msg + "\n" + Log.getStackTraceString(tr));
          }

          @Override
          public void w(String tag, String msg) {
              log(FLog.WARN, tag, msg);
          }

          @Override
          public void w(String tag, String msg, Throwable tr) {
              log(FLog.WARN, tag, msg + "\n" + Log.getStackTraceString(tr));
          }

          @Override
          public void e(String tag, String msg) {
              log(FLog.ERROR, tag, msg);
          }

          @Override
          public void e(String tag, String msg, Throwable tr) {
              log(FLog.ERROR, tag, msg + "\n" + Log.getStackTraceString(tr));
          }

          @Override
          public void wtf(String tag, String msg) {
              Log.wtf(tag, msg);
          }

          @Override
          public void wtf(String tag, String msg, Throwable tr) {
              Log.wtf(tag, msg, tr);
          }

          @Override
          public void log(int priority, String tag, String msg) {
              String newTag = prefix + tag;
              switch (priority) {
                  case FLog.VERBOSE:
                      Log.v(newTag, msg);
                      break;
                  case FLog.DEBUG:
                      Log.d(newTag, msg);
                      break;
                  case FLog.INFO:
                      Log.i(newTag, msg);
                      break;
                  case FLog.WARN:
                      Log.w(newTag, msg);
                      break;
                  case FLog.ERROR:
                      Log.e(newTag, msg);
                      break;
                  default:
                      Log.println(priority, newTag, msg);
              }
          }
      });

  }
}
