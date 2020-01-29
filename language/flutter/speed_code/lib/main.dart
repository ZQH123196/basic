import 'package:flutter/material.dart';
import 'demo/travel_home_page/travel_home_page.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Travel_home(),
    );
  }
}
