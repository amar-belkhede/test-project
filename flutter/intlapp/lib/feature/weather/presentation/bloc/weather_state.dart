import 'package:equatable/equatable.dart';
import 'package:intlapp/feature/weather/domain/entity/weather.dart';

abstract class WeatherState extends Equatable {
  const WeatherState();

  @override
  List<Object?> get props => [];
}

class WeatherEmpty extends WeatherState {}

class WeatherLoading extends WeatherState {}

class WeatherSaved extends WeatherState {}

class WeatherDeleted extends WeatherState {}

class WeatherLoaded extends WeatherState {
  final WeatherEntity result;
  final List<WeatherEntity> savedWeatherList;

  const WeatherLoaded(this.result, this.savedWeatherList);

  @override
  List<Object?> get props => [result, savedWeatherList];
}

class WeatherLoadFailue extends WeatherState {
  final String message;

  const WeatherLoadFailue(this.message);

  @override
  List<Object?> get props => [message];
}
