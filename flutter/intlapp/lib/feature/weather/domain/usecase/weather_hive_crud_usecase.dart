import 'package:injectable/injectable.dart';
import 'package:intlapp/feature/weather/data/model/weather_hive_model.dart';
import 'package:intlapp/feature/weather/domain/entity/weather.dart';
import 'package:intlapp/feature/weather/domain/repository/weather_hive_repository.dart';

@injectable
class WeatherHiveUseCase {
  final WeatherHiveRepository weatherHiveRepository;

  WeatherHiveUseCase({required this.weatherHiveRepository});

  Future<List<WeatherEntity>> getAllSearchedWeather() async {
    return await weatherHiveRepository.getAllSearchedWeather();
  }

  Future<void> deleteWeatherHiveModel(int index) async {
    await weatherHiveRepository.deleteWeatherHiveModel(index);
  }  
  
  Future<void> addWeatherHiveModel(WeatherHiveModel model) async {
    await weatherHiveRepository.addWeatherHiveModel(model);
  }

  Future<void> deleteAllWeatherHiveModel() async {
    await weatherHiveRepository.deleteAllWeatherHiveModel();
  }
}
