import { RNPackageContext, RNPackage } from '@rnoh/react-native-openharmony/ts';
import { ShiplyReactNativeUpgradePackage } from "ohrn_shiply_upgrade";

export function createRNPackages(ctx: RNPackageContext): RNPackage[] {
  return [new ShiplyReactNativeUpgradePackage(ctx)];
}
