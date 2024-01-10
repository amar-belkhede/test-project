import 'package:hive/hive.dart';
import 'package:injectable/injectable.dart';
import 'package:intlapp/data/todo_item.dart';

@singleton
class TodoRepository {
  final String _boxName = "todoBox";

  Future<Box<TodoItem>> get _box async =>
      await Hive.openBox<TodoItem>(_boxName);

  Future<void> addItem(TodoItem todoItem) async {
    var box = await _box;
    await box.add(todoItem);
  }

  Future<List<TodoItem>> getAllTodoItems() async {
    var box = await _box;
    return box.values.toList();
  }

  Future<void> deleteTodo(int index) async {
    var box = await _box;
    await box.deleteAt(index);
  }

  Future<void> updateIsCompleted(int index, TodoItem todoItem) async {
    var box = await _box;
  }

  Future<TodoItem> getDummy() async {
    return TodoItem(title: "dummy item", isCompleted: false);
  }
}
