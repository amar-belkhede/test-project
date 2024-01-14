import 'package:dartz/dartz.dart';
import 'package:intlapp/core/error/failure.dart';
import 'package:intlapp/feature/weather/domain/entity/weather.dart';

abstract class WeatherRepository {
  Future<Either<Failure, WeatherEntity>> getCurrentWeather(String cityName);
}