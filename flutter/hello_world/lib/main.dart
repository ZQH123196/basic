import 'package:flutter/material.dart';
import 'demo/drawer_demo.dart';
import 'demo/bottomNavigationBar_demo.dart';

main() => runApp(APP());

class APP extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return MaterialApp(
      home: Home(),
      theme: ThemeData(
        primaryColor: Colors.deepPurple,
        highlightColor: Color.fromRGBO(255, 255, 255, 0.5),
        splashColor: Colors.red[300], // 水波纹
      ),
    );
  }
}

class Home extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return DefaultTabController(
      length: 3,
      child: Scaffold(
        backgroundColor: Colors.grey[100],
        appBar: AppBar(
          title: Text('Hello Flutter!'),
          elevation: 10, // 阴影
          leading: null,
          actions: <Widget>[
            IconButton(
              icon: Icon(Icons.search),
              tooltip: "Search",
              onPressed: () => debugPrint("Search button is on pressed."),
            ),
            IconButton(
              icon: Icon(Icons.more_horiz),
              tooltip: "more_horiz",
              onPressed: () => debugPrint("More_horiz button is on pressed."),
            ),
          ],
          bottom: TabBar(
            unselectedLabelColor: Colors.black38,
            indicatorColor: Colors.black54, // 指示器(下面那条横线)的颜色
            indicatorSize: TabBarIndicatorSize.label,
            indicatorWeight: 1.0,
            tabs: <Widget>[
              Tab(icon: Icon(Icons.local_florist)),
              Tab(icon: Icon(Icons.change_history)),
              Tab(icon: Icon(Icons.directions_bike)),
            ],
          ),
        ),
        body: TabBarView(
          children: <Widget>[
            Icon(Icons.local_florist, size: 128.0, color: Colors.black12),
            Icon(Icons.change_history, size: 128.0, color: Colors.black12),
            Icon(Icons.directions_bike, size: 128.0, color: Colors.black12),
          ],
        ),
        drawer: DrawerDemo(),
        bottomNavigationBar: BottomNavigationBarDemo(),
      ),
    );
  }
}
