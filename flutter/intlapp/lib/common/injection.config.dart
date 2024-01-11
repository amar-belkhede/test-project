// GENERATED CODE - DO NOT MODIFY BY HAND

// **************************************************************************
// InjectableConfigGenerator
// **************************************************************************

// ignore_for_file: type=lint
// coverage:ignore-file

// ignore_for_file: no_leading_underscores_for_library_prefixes
import 'package:get_it/get_it.dart' as _i1;
import 'package:http/http.dart' as _i3;
import 'package:injectable/injectable.dart' as _i2;

import '../feature/weather/data/data_source/remote_data_source.dart' as _i5;
import '../feature/weather/data/repository/weather_repository_impl.dart' as _i7;
import '../feature/weather/domain/repository/weather_repository.dart' as _i6;
import '../feature/weather/domain/usecase/get_current_weather.dart' as _i8;
import '../feature/weather/presentation/bloc/weather_bloc.dart' as _i9;
import '../repository/todo_repository.dart' as _i4;
import 'app_module.dart' as _i10;

extension GetItInjectableX on _i1.GetIt {
// initializes the registration of main-scope dependencies inside of GetIt
  _i1.GetIt init({
    String? environment,
    _i2.EnvironmentFilter? environmentFilter,
  }) {
    final gh = _i2.GetItHelper(
      this,
      environment,
      environmentFilter,
    );
    final appModule = _$AppModule();
    gh.singleton<_i3.Client>(appModule.getHttpClient());
    gh.singleton<_i4.TodoRepository>(_i4.TodoRepository());
    gh.factory<_i5.WeatherRemoteDataSource>(
        () => _i5.WeatherRemoteDataSourceImpl(client: gh<_i3.Client>()));
    gh.factory<_i6.WeatherRepository>(() => _i7.WeatherRepositoryImpl(
        weatherRemoteDataSource: gh<_i5.WeatherRemoteDataSource>()));
    gh.factory<_i8.GetCurrentWeatherUseCase>(() => _i8.GetCurrentWeatherUseCase(
        weatherRepository: gh<_i6.WeatherRepository>()));
    gh.singleton<_i9.WeatherBloc>(
        _i9.WeatherBloc(gh<_i8.GetCurrentWeatherUseCase>()));
    return this;
  }
}

class _$AppModule extends _i10.AppModule {}
