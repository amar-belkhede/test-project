import 'dart:io';

import 'package:dartz/dartz.dart';
import 'package:injectable/injectable.dart';
import 'package:intlapp/core/error/exception.dart';
import 'package:intlapp/core/error/failure.dart';
import 'package:intlapp/feature/weather/data/data_source/remote_data_source.dart';
import 'package:intlapp/feature/weather/domain/entity/weather.dart';
import 'package:intlapp/feature/weather/domain/repository/weather_repository.dart';

@Injectable(as: WeatherRepository)
class WeatherRepositoryImpl extends WeatherRepository {
  
  final WeatherRemoteDataSource weatherRemoteDataSource;
  WeatherRepositoryImpl(
      {required this.weatherRemoteDataSource});

  @override
  Future<Either<Failure, WeatherEntity>> getCurrentWeather(
      String cityName) async {
    try {
      final result = await weatherRemoteDataSource.getCurrentWeather(cityName);
      return Right(result.toEntity());
    } on ServerException {
      return const Left(ServerFailure('An error has occurred'));
    } on SocketException {
      return const Left(ConnectionFailure('Failed to connect to the network'));
    }
  }
}