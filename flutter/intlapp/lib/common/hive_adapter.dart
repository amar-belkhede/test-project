import 'package:flutter/foundation.dart';
import 'package:hive_flutter/hive_flutter.dart';
import 'package:intlapp/feature/weather/data/model/weather_hive_model.dart';

class HiveAdapters {
  Future<void> initHive() async {
    await Hive.initFlutter(hivePath);
    Hive.registerAdapter(WeatherHiveModelAdapter());
  }

  String? get hivePath {
    if (kIsWeb) {
      return 'test';
    } else if (TargetPlatform.windows == defaultTargetPlatform) {
      return 'test';
    } else {
      return null;
    }
  }
}
