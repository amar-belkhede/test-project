import 'package:intlapp/feature/weather/data/data_source/remote_data_source.dart';
import 'package:intlapp/feature/weather/domain/repository/weather_repository.dart';
import 'package:intlapp/feature/weather/domain/usecase/get_current_weather.dart';
import 'package:mockito/annotations.dart';
import 'package:http/http.dart' as http;

// $ flutter pub run build_runner build

@GenerateMocks(
  [
    WeatherRepository,
    WeatherRemoteDataSource,
    GetCurrentWeatherUseCase,
  ],
  customMocks: [MockSpec<http.Client>(as: #MockHttpClient)],
)
void main() {

}