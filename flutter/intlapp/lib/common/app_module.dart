import 'package:injectable/injectable.dart';
import 'package:http/http.dart' as http;

@module
abstract class AppModule {
  @singleton
  http.Client getHttpClient() {
    return http.Client();
  }
}
