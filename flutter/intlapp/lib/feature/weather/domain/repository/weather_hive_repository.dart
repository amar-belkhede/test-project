
import 'package:intlapp/feature/weather/data/model/weather_hive_model.dart';
import 'package:intlapp/feature/weather/domain/entity/weather.dart';
import 'package:intlapp/feature/weather/presentation/bloc/weather_state.dart';

abstract class WeatherHiveRepository {
  
  Future<List<WeatherEntity>> getAllSearchedWeather();
  Future<void> deleteWeatherHiveModel(int index);
  Future<void> addWeatherHiveModel(WeatherHiveModel model);
  Future<void> deleteAllWeatherHiveModel();
}