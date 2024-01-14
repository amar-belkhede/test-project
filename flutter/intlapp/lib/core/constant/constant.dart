class Urls {
  static const String baseUrl = 'https://api.openweathermap.org/data/2.5';
  // static const String apiKey = 'cc95d932d5a45d33a9527d5019475f2c';
  static const String apiKey = 'b8e4a5fcd56498be6377f84385d3b2a3';
  static String currentWeatherByName(String city) =>
      '$baseUrl/weather?q=$city&appid=$apiKey';
  static String weatherIcon(String iconCode) =>
      'http://openweathermap.org/img/wn/$iconCode@2x.png';
}