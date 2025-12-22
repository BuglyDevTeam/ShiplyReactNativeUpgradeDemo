# ShiplyReactNativeUpgradeDemo

> Shiply RN 热更新 SDK 接入使用 Demo
> 
> 🔗 Shiply 平台访问地址：https://shiply.tds.qq.com/

---

## 📋 目录

- [快速开始](#-快速开始)
- [RN 产物打包](#-rn-产物打包)
- [Android 端](#-android-端)
- [iOS 端](#-ios-端)
- [鸿蒙端](#-鸿蒙端)
- [常见问题](#-常见问题)

---

## 🚀 快速开始

### 1. 创建 Shiply 项目

在 [Shiply 平台](https://shiply.tds.qq.com/) 创建项目，并分别创建 Android/iOS/Harmony 产品。

📖 参考文档：[接入流程](https://shiply.tds.qq.com/document/getting-started-guide/integration-process/)

### 2. 创建 RN 模块

在项目的「RN 动态化」中创建 RN 模块：
- **模块名称**：`testRNExample`
- **绑定产品**：上一步创建的三个不同平台产品

📖 参考文档：[RN 热更新快速开始](https://shiply.tds.qq.com/document/react-native-hot-reload/quick-start/)

### 3. 配置 AppId/AppKey

在项目根目录创建 `config.json` 文件：

```json
{
   "ios": {
      "appId": "your_ios_app_id",
      "appKey": "your_ios_app_key"
   },
   "android": {
      "appId": "your_android_app_id",
      "appKey": "your_android_app_key"
   },
   "harmony": {
      "appId": "your_harmony_app_id",
      "appKey": "your_harmony_app_key"
   }
}
```

> ⚠️ 请将 `appId` 和 `appKey` 替换为各产品的实际值

---

## 📦 RN 产物打包

修改 `App.tsx` 文件内容后，使用以下命令打包各平台 RN 产物并上传至 Shiply 平台。

### Android 产物

```bash
# 创建输出目录
mkdir -p bundle/androidBundle

# 打包
react-native bundle \
  --entry-file ./index.js \
  --bundle-output ./bundle/androidBundle/index.android.bundle \
  --platform android \
  --assets-dest ./bundle/androidBundle \
  --dev false
```

### iOS 产物

```bash
# 创建输出目录
mkdir -p bundle/iOSBundle

# 打包
react-native bundle \
  --entry-file ./index.js \
  --bundle-output ./bundle/iOSBundle/index.ios.bundle \
  --platform ios \
  --assets-dest ./bundle/iOSBundle \
  --dev false
```

### 鸿蒙产物

```bash
# 创建输出目录
mkdir -p bundle/harmonyBundle

# 打包
react-native bundle-harmony \
  --entry-file ./index.js \
  --bundle-output ./bundle/harmonyBundle/bundle.harmony.js \
  --assets-dest ./bundle/harmonyBundle/assets \
  --dev false
```

---

## 🤖 Android 端

### 编译运行

```bash
cd android
./gradlew app:assembleRelease
```

安装生成的 APK 后即可检测更新。

---

## 🍎 iOS 端

### 环境准备

```bash
# 1. 安装 Ruby 依赖（项目根目录执行）
bundle install

# 2. 安装 CocoaPods 依赖（ios 目录执行）
cd ios
bundle exec pod install
```

### 运行项目

使用 Xcode 打开 `ios/ShiplyReactNativeUpgradeDemo.xcworkspace`，选择目标设备后点击运行。

---

## 🔷 鸿蒙端

### 运行项目

使用 **DevEco Studio** 打开 `harmony` 目录即可。

---

## ❓ 常见问题

### 1. boost 校验失败

**错误信息：**
```
[!] Error installing boost
Verification checksum was incorrect...
```

**解决方法：**

修改 `./node_modules/react-native/third-party-podspecs/boost.podspec`：

```ruby
# 将原来的源地址
# spec.source = { :http => 'https://boostorg.jfrog.io/artifactory/main/release/1.76.0/source/boost_1_76_0.tar.bz2', ... }

# 替换为
spec.source = { 
  :http => 'https://sourceforge.net/projects/boost/files/boost/1.76.0/boost_1_76_0.tar.bz2',
  :sha256 => 'f0397ba6e982c4450f27bf32a2a83292aba035b827a5623a14636ea583318c41' 
}
```

### 2. glog 编译失败

**错误信息：**
```
unknown type name '_START_GOOGLE_NAMESPACE_'
```

**解决方法：**

参考 [GitHub Issue #34189](https://github.com/facebook/react-native/issues/34189#issuecomment-1358075564)，下载 `glog.zip` 解压后替换 `ios/Pods/glog` 目录。

> 💡 **提示**：如果同时配置了 OpenHarmony 开发环境，可能是环境变量污染导致。执行以下命令后重试：
> ```bash
> # 使用项目提供的环境切换脚本
> source scripts/env_switch.sh
> ohos_off  # 禁用 OpenHarmony 环境
> ```

### 3. PhaseScriptExecution 失败

**错误信息：**
```
Command PhaseScriptExecution failed with a nonzero exit code
```

**解决方法：**

在 `ios/.xcode.env` 中添加 Node 路径：

```bash
# 将 xxx 替换为实际用户名，路径替换为实际 node 安装路径
export NODE_BINARY=/Users/xxx/.nvm/versions/node/v20.19.1/bin/node
```

---

## 📁 项目结构

```
ShiplyReactNativeUpgradeDemo/
├── android/          # Android 原生代码
├── ios/              # iOS 原生代码
├── harmony/          # 鸿蒙原生代码
├── bundle/           # RN 打包产物输出目录
├── config.json       # Shiply 配置文件
├── App.tsx           # RN 入口文件
└── README.md
```


