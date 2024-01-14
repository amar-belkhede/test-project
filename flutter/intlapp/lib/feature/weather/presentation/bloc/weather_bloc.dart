import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:injectable/injectable.dart';
import 'package:intlapp/feature/weather/data/model/weather_hive_model.dart';
import 'package:intlapp/feature/weather/domain/entity/weather.dart';
import 'package:intlapp/feature/weather/domain/usecase/get_current_weather.dart';
import 'package:intlapp/feature/weather/domain/usecase/weather_hive_crud_usecase.dart';
import 'package:intlapp/feature/weather/presentation/bloc/weather_event.dart';
import 'package:intlapp/feature/weather/presentation/bloc/weather_state.dart';
import 'package:rxdart/rxdart.dart';

@singleton
class WeatherBloc extends Bloc<WeatherEvent, WeatherState> {
  final GetCurrentWeatherUseCase _getCurrentWeatherUseCase;
  final WeatherHiveUseCase _weatherHiveUseCase;
  WeatherBloc(this._getCurrentWeatherUseCase, this._weatherHiveUseCase)
      : super(WeatherEmpty()) {
    on<OnCityChanged>(
      (event, emit) async {
        emit(WeatherLoading());
        final result = await _getCurrentWeatherUseCase.execute(event.cityName);
        List<WeatherEntity> weatherList =
          await _weatherHiveUseCase.getAllSearchedWeather();
        result.fold(
          (failure) {
            emit(WeatherLoadFailue(failure.message));
          },
          (data) {
            emit(WeatherLoaded(data, weatherList));
          },
        );
      },
      transformer: debounce(const Duration(milliseconds: 500)),
    );

    on<OnSaveCity>((event, emit) async {
      await _weatherHiveUseCase.addWeatherHiveModel(
          WeatherHiveModel.fromWeatherEntity(event.weatherEntity));
      emit(WeatherSaved());

      List<WeatherEntity> result =
          await _weatherHiveUseCase.getAllSearchedWeather();
      emit(WeatherLoaded(event.weatherEntity, result));
    });

    on<OnDeleteCity>((event, emit) async {
      await _weatherHiveUseCase.deleteWeatherHiveModel(event.index);
      emit(WeatherDeleted());

      List<WeatherEntity> result =
          await _weatherHiveUseCase.getAllSearchedWeather();
      emit(WeatherLoaded(event.weatherEntity, result));
    });
  }
}

EventTransformer<T> debounce<T>(Duration duration) {
  return (events, mapper) => events.debounceTime(duration).flatMap(mapper);
}
