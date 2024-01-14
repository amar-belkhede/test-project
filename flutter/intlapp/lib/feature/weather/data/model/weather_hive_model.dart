import 'package:hive_flutter/hive_flutter.dart';
import 'package:intlapp/feature/weather/domain/entity/weather.dart';
import 'package:uuid/uuid.dart';


part 'weather_hive_model.g.dart';


@HiveType(typeId: 0)
class WeatherHiveModel extends HiveObject{
  WeatherHiveModel({
    required this.id,
    required this.cityName,
    required this.main,
    required this.description,
    required this.iconCode,
    required this.temperature,
    required this.pressure,
    required this.humidity,
  });


  @HiveField(0)
  final String id;

  @HiveField(1)
  final String cityName;

  @HiveField(2)
  final String main;

  @HiveField(3)
  final String description;

  @HiveField(4)
  final String iconCode;

  @HiveField(5)
  final double temperature;

  @HiveField(6)
  final int pressure;

  @HiveField(7)
  final int humidity;

  static String BOX_NAME = "weather_hive_model"; 

    factory WeatherHiveModel.fromJson(Map < String, dynamic > json) => WeatherHiveModel(
      id: const Uuid().v1(),
      cityName: json['name'],
      main: json['weather'][0]['main'],
      description: json['weather'][0]['description'],
      iconCode: json['weather'][0]['icon'],
      temperature: json['main']['temp'],
      pressure: json['main']['pressure'],
      humidity: json['main']['humidity'],
    );

    factory WeatherHiveModel.fromWeatherEntity(WeatherEntity entity) => WeatherHiveModel(
      id: const Uuid().v1(),
      cityName: entity.cityName,
      main: entity.main,
      description: entity.description,
      iconCode: entity.iconCode,
      temperature: entity.temperature,
      pressure: entity.pressure,
      humidity: entity.humidity,
    );

    WeatherEntity toWeatherEntity() => WeatherEntity(
      cityName: cityName,
      main: main,
      description: description,
      iconCode: iconCode,
      temperature: temperature,
      pressure: pressure,
      humidity: humidity,
    );

}
