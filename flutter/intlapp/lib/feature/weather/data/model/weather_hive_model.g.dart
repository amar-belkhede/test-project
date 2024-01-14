// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'weather_hive_model.dart';

// **************************************************************************
// TypeAdapterGenerator
// **************************************************************************

class WeatherHiveModelAdapter extends TypeAdapter<WeatherHiveModel> {
  @override
  final int typeId = 0;

  @override
  WeatherHiveModel read(BinaryReader reader) {
    final numOfFields = reader.readByte();
    final fields = <int, dynamic>{
      for (int i = 0; i < numOfFields; i++) reader.readByte(): reader.read(),
    };
    return WeatherHiveModel(
      id: fields[0] as String,
      cityName: fields[1] as String,
      main: fields[2] as String,
      description: fields[3] as String,
      iconCode: fields[4] as String,
      temperature: fields[5] as double,
      pressure: fields[6] as int,
      humidity: fields[7] as int,
    );
  }

  @override
  void write(BinaryWriter writer, WeatherHiveModel obj) {
    writer
      ..writeByte(8)
      ..writeByte(0)
      ..write(obj.id)
      ..writeByte(1)
      ..write(obj.cityName)
      ..writeByte(2)
      ..write(obj.main)
      ..writeByte(3)
      ..write(obj.description)
      ..writeByte(4)
      ..write(obj.iconCode)
      ..writeByte(5)
      ..write(obj.temperature)
      ..writeByte(6)
      ..write(obj.pressure)
      ..writeByte(7)
      ..write(obj.humidity);
  }

  @override
  int get hashCode => typeId.hashCode;

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is WeatherHiveModelAdapter &&
          runtimeType == other.runtimeType &&
          typeId == other.typeId;
}
