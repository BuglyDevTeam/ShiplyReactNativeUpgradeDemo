#import "AppDelegate.h"

#import <React/RCTBundleURLProvider.h>
#import <ShiplyReactNativeUpgrade/ShiplyReactNativeUpgradeUtil.h>

@implementation AppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
  self.moduleName = @"ShiplyReactNativeUpgradeDemo";
  // You can add your custom initial props in the dictionary below.
  // They will be passed down to the ViewController used by React Native.
  self.initialProps = @{};

  return [super application:application didFinishLaunchingWithOptions:launchOptions];
}

- (NSURL *)sourceURLForBridge:(RCTBridge *)bridge
{
  NSLog(@"sourceURLForBridge testRN72 called");
#if DEBUG
  NSLog(@"sourceURLForBridge testRN72 debug");
  return [[RCTBundleURLProvider sharedSettings] jsBundleURLForBundleRoot:@"index"];
#else
    // return [[NSBundle mainBundle] URLForResource:@"main" withExtension:@"jsbundle"];
    // 发布模式下优先使用热更新包
    NSString *resourceName = @"testRNExample"; // 替换为你的资源名称
    NSString *bundlePath = [ShiplyReactNativeUpgradeUtil getJSBundleFilePath:resourceName];

    if (bundlePath) {
    NSLog(@"sourceURLForBridge testRN72 使用热更新包路径: %@", bundlePath);
    return [NSURL fileURLWithPath:bundlePath];
    }

    // 如果没有热更新包，则使用内置包
    NSLog(@"sourceURLForBridge testRN72 使用内置包");
    return [[NSBundle mainBundle] URLForResource:@"main" withExtension:@"jsbundle"];
#endif
}

@end
