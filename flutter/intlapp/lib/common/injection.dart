import 'package:flutter/widgets.dart';
import 'package:injectable/injectable.dart';
import 'package:get_it/get_it.dart';
import 'package:intlapp/common/hive_adapter.dart';

import 'injection.config.dart';

final getIt = GetIt.instance;

@InjectableInit(
  initializerName: 'init', // default
  preferRelativeImports: true, // default
  asExtension: true, // default
)
Future<void> configureDependenciesInjection() async {
  WidgetsFlutterBinding.ensureInitialized();
  await HiveAdapters().initHive();
  getIt.init();
  return await getIt.allReady();
}
//flutter clean && flutter pub get && flutter pub run build_runner build --delete-conflicting-outputs
