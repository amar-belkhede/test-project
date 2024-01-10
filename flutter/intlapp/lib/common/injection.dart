import 'package:injectable/injectable.dart';
import 'package:get_it/get_it.dart';
	
import 'injection.config.dart';


final getIt = GetIt.instance;  
  
@InjectableInit(  
  initializerName: 'init', // default  
  preferRelativeImports: true, // default  
  asExtension: true, // default  
)  
void configureDependenciesInjection() => getIt.init();  

//flutter clean && flutter pub get && flutter pub run build_runner build --delete-conflicting-outputs