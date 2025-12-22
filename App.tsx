import { Text, View, Image, Platform } from 'react-native';
import { HotUpdateHelper, HotUpdateButton } from 'rn-shiply-upgrade'; // 新增导入辅助类


const AppV2 = () => {
  // 打印当前的平台值
  console.log(`currentPlatform: ${Platform.OS}`);
  // 初始化热更新配置（只需一次）
  HotUpdateHelper.getInstance({
    appId: Platform.select({
      ios: require('./config.json').ios.appId,
      android: require('./config.json').android.appId,
      harmony: require('./config.json').harmony.appId
    }),
    appKey: Platform.select({
      ios: require('./config.json').ios.appKey,
      android: require('./config.json').android.appKey,
      harmony: require('./config.json').harmony.appKey
    }),
    deviceId: '866123456789012',
    appVersion: '10.0.0',
    updateCheckIntervalLimit: 60 * 1000, // 60秒限制
    isDebugPackage: false,
    customParamsByJson: JSON.stringify({ age: '22', name: 'testName' }),
    env: 'online',
    shiplyResName: 'testRNExample',
    eventLogger: ({ type, data }) => {
      console.log(`[HotUpdate] ${type}:`, data);
    }
  });
  return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <Text>0.72RN Default Demo 1219 </Text>
        <Text>Hello World 111 local,show dog</Text>
        {/*<Text>Hello World remote,show panda</Text>*/}
        <Image
            source={require('./dog.jpeg')}
            // source={require('./panda.jpg')}
            style={{ width: 200, height: 200 }}
        />
        <Text style={{ fontSize: 20, marginBottom: 20 }}>热更新示例V2</Text>

        {/* 默认样式按钮 */}
        <HotUpdateButton />

        {/* 自定义样式按钮示例 */}
        <HotUpdateButton config={{
          buttonStyle: {
            backgroundColor: '#4CAF50',
            padding: 20,
            borderRadius: 10,
            marginTop: 20
          },
          buttonTextStyle: {
            color: 'white',
            fontSize: 18
          },
          loadingColor: '#FFEB3B',
          buttonText: '立即检查更新'
        }} />
      </View>
  );
};

export default AppV2;
