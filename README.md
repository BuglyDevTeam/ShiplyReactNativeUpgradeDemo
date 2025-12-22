
# ShiplyReactNativeUpgradeDemo
Shiply RN热更新SDK接入使用Demo，Shiply访问地址：https://shiply.tds.qq.com/

1. 在Shiply平台创建项目，项目下分别创建Android/iOS/Harmony产品，具体操作可以参考 https://shiply.tds.qq.com/document/getting-started-guide/integration-process/ ；
2. 在项目RN动态化创建RN模块，模块名称为testRNExample，绑定第一步创建的三个不同平台的产品，具体操作可以参考 https://shiply.tds.qq.com/document/react-native-hot-reload/quick-start/ ；
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


# 鸿蒙端使用说明

使用DevEco-Studio打开ShiplyReactNativeUpgradeDemo/harmony目录;





