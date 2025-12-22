
# ShiplyReactNativeUpgradeDemo
Shiply RN热更新SDK接入使用Demo，Shiply访问地址：https://shiply.tds.qq.com/

1. 在Shiply平台创建项目，项目下分别创建Android/iOS/Harmony产品，具体操作可以参考 https://shiply.tds.qq.com/document/getting-started-guide/integration-process/ ；
2. 在项目RN动态化创建RN模块，模块名称为 testRNExample，绑定第一步创建的三个不同平台的产品，具体操作可以参考 https://shiply.tds.qq.com/document/react-native-hot-reload/quick-start/ ；
3. 在项目根目录创建config.json文件，文件内容如下：
```json
{
   "ios": {
      "appId": "xx",
      "appKey": "xxxx"
   },
   "android": {
      "appId": "xx",
      "appKey": "xxxx"
   },
   "harmony": {
      "appId": "xx",
      "appKey": "xxxx"
   }
}
```
 appId/appKey替换为第一步创建的各个产品的实际值；
4. 修改App.tsx文件内容，编译出不同平台的RN产物，并上传到Shiply平台；
   在项目根目录创建bundle/androidBundle文件夹，执行以下命令，将Android端的RN产物打包到androidBundle文件夹中：
```shell
react-native bundle --entry-file ./index.js --bundle-output ./bundle/androidBundle/index.android.bundle --platform android --assets-dest ./bundle/androidBundle --dev false
```
   在项目根目录创建bundle/iOSBundle文件夹，执行以下命令，将iOS端的RN产物打包到iOSBundle文件夹中：
```shell
react-native bundle --entry-file ./index.js --bundle-output ./bundle/iOSBundle/index.ios.bundle --platform ios --assets-dest ./bundle/iOSBundle --dev false
```
   在项目根目录创建bundle/harmonyBundle文件夹，执行以下命令，将鸿蒙端的RN产物打包到harmonyBundle文件夹中：
```shell
react-native bundle-harmony --entry-file ./index.js --bundle-output ./bundle/harmonyBundle/bundle.harmony.js --assets-dest ./bundle/harmonyBundle/assets --dev false
```

# iOS端使用说明

在ShiplyReactNativeUpgradeDemo目录执行 bundle install 安装依赖；
在ShiplyReactNativeUpgradeDemo/ios目录执行 bundle exec pod install 安装依赖；
可能会遇到以下错误：
```shell
[!] Error installing boost
Verification checksum was incorrect, expected f0397ba6e982c4450f27bf32a2a83292aba035b827a5623a14636ea583318c41, got 1c162b579a423fa6876c6c5bc16d39ab4bc05e28898977a0a6af345f523f6357

```
解决方法：
在ShiplyReactNativeUpgradeDemo/ios目录执行 bundle exec pod install --verbose 安装依赖，查看具体错误信息；
根据错误信息，./node_modules/react-native/third-party-podspecs/boost.podspec中修改：

```shell
#   spec.source = { :http => 'https://boostorg.jfrog.io/artifactory/main/release/1.76.0/source/boost_1_76_0.tar.bz2',
#                   :sha256 => 'f0397ba6e982c4450f27bf32a2a83292aba035b827a5623a14636ea583318c41' }
  spec.source = { :http => 'https://sourceforge.net/projects/boost/files/boost/1.76.0/boost_1_76_0.tar.bz2',
                  :sha256 => 'f0397ba6e982c4450f27bf32a2a83292aba035b827a5623a14636ea583318c41' }

```

使用Xcode打开ShiplyReactNativeUpgradeDemo/ios/ShiplyReactNativeUpgradeDemo.xcworkspace，选择ShiplyReactNativeUpgradeDemo项目，点击运行；

编译时如果遇到以下错误：
```shell
unknown type name '_START_GOOGLE_NAMESPACE_'
```

解决方法：
参考 https://github.com/facebook/react-native/issues/34189#issuecomment-1358075564 ，下载glog.zip，解压后替换ShiplyReactNativeUpgradeDemo/ios/Pods/glog目录；

还有可能遇到以下错误：
```shell
Command PhaseScriptExecution failed with a nonzero exit code
```

解决方法：
在 ios/.xcode.env 中添加以下内容：
```shell
export NODE_BINARY=/Users/xxx/.nvm/versions/node/v20.19.1/bin/node # 这里xxx改为使用方的真实node路径
```

# 鸿蒙端使用说明

使用DevEco-Studio打开ShiplyReactNativeUpgradeDemo/harmony目录;





