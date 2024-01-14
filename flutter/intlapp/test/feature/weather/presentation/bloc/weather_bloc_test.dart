import 'package:dartz/dartz.dart';
import 'package:intlapp/core/error/failure.dart';
import 'package:intlapp/feature/weather/presentation/bloc/weather_bloc.dart';
import 'package:intlapp/feature/weather/presentation/bloc/weather_event.dart';
import 'package:intlapp/feature/weather/presentation/bloc/weather_state.dart';

import '../../../../helper/test_helper.mocks.dart';
import 'package:bloc_test/bloc_test.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:intlapp/feature/weather/domain/entity/weather.dart';
import 'package:mockito/mockito.dart';

void main() {
  late MockGetCurrentWeatherUseCase mockGetCurrentWeatherUseCase;
  late MockWeatherHiveUseCase mockWeatherHiveUseCase;
  late WeatherBloc weatherBloc;

  setUp(() {
    mockGetCurrentWeatherUseCase = MockGetCurrentWeatherUseCase();
    mockWeatherHiveUseCase = MockWeatherHiveUseCase();
    weatherBloc = WeatherBloc(
      mockGetCurrentWeatherUseCase,
      mockWeatherHiveUseCase
    );
  });

  const testWeather = WeatherEntity(
    cityName: 'New York',
    main: 'Clouds',
    description: 'few clouds',
    iconCode: '02d',
    temperature: 302.28,
    pressure: 1009,
    humidity: 70,
  );

  const testCityName = 'New York';

  test('initial state should be empty', () {
    expect(weatherBloc.state, WeatherEmpty());
  });

  blocTest<WeatherBloc, WeatherState>(
      'should emit [WeatherLoading, WeatherLoaded] when data is gotten successfully',
      build: () {
        when(mockGetCurrentWeatherUseCase.execute(testCityName))
            .thenAnswer((_) async => const Right(testWeather));
        return weatherBloc;
      },
      act: (bloc) => bloc.add(const OnCityChanged(testCityName)),
      wait: const Duration(milliseconds: 500),
      expect: () => [WeatherLoading(), WeatherLoaded(testWeather, List.empty())]);

  blocTest<WeatherBloc, WeatherState>(
      'should emit [WeatherLoading, WeatherLoadFailure] when get data is unsuccessful',
      build: () {
        when(mockGetCurrentWeatherUseCase.execute(testCityName)).thenAnswer(
            (_) async => const Left(ServerFailure('Server failure')));
        return weatherBloc;
      },
      act: (bloc) => bloc.add(const OnCityChanged(testCityName)),
      wait: const Duration(milliseconds: 500),
      expect: () => [
            WeatherLoading(),
            const WeatherLoadFailue('Server failure'),
          ]);
}
