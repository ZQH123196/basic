import 'package:flutter/material.dart';
//import 'demo/travel_home_page/travel_home_page.dart';
import 'demo/personal_resume/personal_resume.dart';


void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: ProfileFourPage(),
    );
  }
}
