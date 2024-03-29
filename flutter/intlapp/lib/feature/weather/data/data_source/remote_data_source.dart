
import 'dart:convert';

import 'package:injectable/injectable.dart';
import 'package:intlapp/core/constant/constant.dart';
import 'package:intlapp/feature/weather/data/model/weather_model.dart';
import 'package:http/http.dart' as http;
import '../../../../core/error/exception.dart';

abstract class WeatherRemoteDataSource {
  
  Future<WeatherModel> getCurrentWeather(String cityName);
}

@Injectable(as: WeatherRemoteDataSource)
class WeatherRemoteDataSourceImpl extends WeatherRemoteDataSource {
  final http.Client client;
  WeatherRemoteDataSourceImpl({required this.client});

  @override
  Future < WeatherModel > getCurrentWeather(String cityName) async {
    final response =
      await client.get(Uri.parse(Urls.currentWeatherByName(cityName)));

     if (response.statusCode == 200) {
      return WeatherModel.fromJson(json.decode(response.body));
    } else {
      throw ServerException();
    }
  }
}