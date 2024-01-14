import 'package:hive_flutter/hive_flutter.dart';
import 'package:injectable/injectable.dart';
import 'package:http/http.dart' as http;
import 'package:intlapp/feature/weather/data/model/weather_hive_model.dart';

@module
abstract class AppModule {
  @singleton
  http.Client getHttpClient() {
    return http.Client();
  }

  @singleton
  Future<Box<WeatherHiveModel>> get accountBox =>
      Hive.openBox<WeatherHiveModel>(WeatherHiveModel.BOX_NAME);
}
