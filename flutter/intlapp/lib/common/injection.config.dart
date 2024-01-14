// GENERATED CODE - DO NOT MODIFY BY HAND

// **************************************************************************
// InjectableConfigGenerator
// **************************************************************************

// ignore_for_file: type=lint
// coverage:ignore-file

// ignore_for_file: no_leading_underscores_for_library_prefixes
import 'package:get_it/get_it.dart' as _i1;
import 'package:hive_flutter/hive_flutter.dart' as _i3;
import 'package:http/http.dart' as _i5;
import 'package:injectable/injectable.dart' as _i2;

import '../feature/weather/data/data_source/remote_data_source.dart' as _i10;
import '../feature/weather/data/model/weather_hive_model.dart' as _i4;
import '../feature/weather/data/repository/weather_hive_repository_impl.dart'
    as _i8;
import '../feature/weather/data/repository/weather_repository_impl.dart'
    as _i12;
import '../feature/weather/domain/repository/weather_hive_repository.dart'
    as _i7;
import '../feature/weather/domain/repository/weather_repository.dart' as _i11;
import '../feature/weather/domain/usecase/get_current_weather.dart' as _i13;
import '../feature/weather/domain/usecase/weather_hive_crud_usecase.dart'
    as _i9;
import '../feature/weather/presentation/bloc/weather_bloc.dart' as _i14;
import '../repository/todo_repository.dart' as _i6;
import 'app_module.dart' as _i15;

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
    gh.singletonAsync<_i3.Box<_i4.WeatherHiveModel>>(
        () => appModule.accountBox);
    gh.singleton<_i5.Client>(appModule.getHttpClient());
    gh.singleton<_i6.TodoRepository>(_i6.TodoRepository());
    gh.factoryAsync<_i7.WeatherHiveRepository>(() async =>
        _i8.WeatherHiveRepositoryImpl(
            weatherHiveModelBox:
                await getAsync<_i3.Box<_i4.WeatherHiveModel>>()));
    gh.factoryAsync<_i9.WeatherHiveUseCase>(() async => _i9.WeatherHiveUseCase(
        weatherHiveRepository: await getAsync<_i7.WeatherHiveRepository>()));
    gh.factory<_i10.WeatherRemoteDataSource>(
        () => _i10.WeatherRemoteDataSourceImpl(client: gh<_i5.Client>()));
    gh.factory<_i11.WeatherRepository>(() => _i12.WeatherRepositoryImpl(
        weatherRemoteDataSource: gh<_i10.WeatherRemoteDataSource>()));
    gh.factory<_i13.GetCurrentWeatherUseCase>(() =>
        _i13.GetCurrentWeatherUseCase(
            weatherRepository: gh<_i11.WeatherRepository>()));
    gh.singletonAsync<_i14.WeatherBloc>(() async => _i14.WeatherBloc(
          gh<_i13.GetCurrentWeatherUseCase>(),
          await getAsync<_i9.WeatherHiveUseCase>(),
        ));
    return this;
  }
}

class _$AppModule extends _i15.AppModule {}
