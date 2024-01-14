import 'package:hive_flutter/hive_flutter.dart';
import 'package:injectable/injectable.dart';
import 'package:intlapp/feature/weather/data/model/weather_hive_model.dart';
import 'package:intlapp/feature/weather/domain/entity/weather.dart';
import 'package:intlapp/feature/weather/domain/repository/weather_hive_repository.dart';

@Injectable(as: WeatherHiveRepository)
class WeatherHiveRepositoryImpl extends WeatherHiveRepository {
  final Box<WeatherHiveModel> weatherHiveModelBox;
  WeatherHiveRepositoryImpl({required this.weatherHiveModelBox});

  @override
  Future<void> deleteAllWeatherHiveModel() async {
    await weatherHiveModelBox.clear();
  }

  @override
  Future<void> deleteWeatherHiveModel(int index) async {
    await weatherHiveModelBox.deleteAt(index);
  }

  @override
  Future<void> addWeatherHiveModel(WeatherHiveModel model) async {
    await weatherHiveModelBox.add(model);
  }

  @override
  Future<List<WeatherEntity>> getAllSearchedWeather() async {
    var result = weatherHiveModelBox.values.toList();
    return result.map((e) => e.toWeatherEntity()).toList();
  }
}
