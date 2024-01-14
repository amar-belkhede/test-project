import 'package:equatable/equatable.dart';
import 'package:intlapp/feature/weather/domain/entity/weather.dart';

abstract class WeatherEvent extends Equatable {
  const WeatherEvent();

  @override
  List<Object?> get props => [];
}

class OnCityChanged extends WeatherEvent {
  final String cityName;

  const OnCityChanged(this.cityName);

  @override
  List<Object?> get props => [cityName];
}

class OnSaveCity extends WeatherEvent {
  final WeatherEntity weatherEntity;

  const OnSaveCity({required this.weatherEntity});

  @override
  List<Object?> get props => [weatherEntity];
}

class OnDeleteCity extends WeatherEvent {
  final WeatherEntity weatherEntity;
  final int index;

  const OnDeleteCity({required this.weatherEntity, required this.index});

  @override
  List<Object?> get props => [weatherEntity];
}
