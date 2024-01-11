import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:get_it/get_it.dart';
import 'package:intlapp/common/injection.dart';
import 'package:intlapp/feature/weather/presentation/bloc/weather_bloc.dart';
import 'package:intlapp/feature/weather/presentation/page/weather_page.dart';
import 'package:intlapp/l10n/l10n.dart';
import 'package:flutter_gen/gen_l10n/app_localizations.dart';
import 'package:intlapp/repository/todo_repository.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();
  configureDependenciesInjection();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      supportedLocales: L10n.all,
      locale: const Locale('ar'),
      localizationsDelegates: [
        AppLocalizations.delegate,
        GlobalMaterialLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
      ],
      // home: const MyHomePage(title: 'Flutter Demo Home Page'),
      home: MultiBlocListener(
        listeners: [
          BlocProvider(create: (_)=> GetIt.I.get<WeatherBloc>()),
        ],
        child: WeatherPage(),
      ),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  final TodoRepository todoRepository = GetIt.I.get<TodoRepository>();
  int _counter = 0;

  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      // body: Center(
      //   child: Column(
      //     mainAxisAlignment: MainAxisAlignment.center,
      //     children: <Widget>[
      //       const Text(
      //         'You have pushed the button this many times:',
      //       ),
      //       Text(AppLocalizations.of(context).language),
      //       Text(AppLocalizations.of(context).greeting("amar belkhede")),
      //       Text(
      //         '$_counter',
      //         style: Theme.of(context).textTheme.headlineMedium,
      //       ),
      //     ],
      //   ),
      // ),
      body: FutureBuilder(
        future: todoRepository.getDummy(),
        builder: (context, snapshot) {
          if (snapshot.hasData && snapshot.data != null) {
            return ListTile(
              title: Text(snapshot.data?.title ?? ""),
            );
          } else {
            return Text("injection failed");
          }
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
