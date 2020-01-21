import 'dart:math';

import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      backgroundColor: Colors.white,
      body: Column(
        mainAxisAlignment: MainAxisAlignment.start,
        children: <Widget>[
          Padding(
            padding: EdgeInsets.only(
                // const kToolbarHeight = 56.0，这常量应该高亮才对，或许以后会支持吧。
                top: MediaQuery.of(context).padding.top + kToolbarHeight,
                left: 40),
            child: Text(
              "Find your \nnext vacation.",
              style: TextStyle(
                  color: Colors.black,
                  fontWeight: FontWeight.bold,
                  fontSize: 25,
                  height: 1.5),
            ),
          ),
          Expanded( 
            child: PageViewWidget(),
          )
        ],
      ),
      bottomNavigationBar: BottomNavigationBar(
        backgroundColor: Colors.white,
        selectedFontSize: 0,
        unselectedFontSize: 0,
        items: [
          BottomNavigationBarItem(
              icon: Icon(
                Icons.navigation,
                color: Colors.black,
              ),
              title: Text("")),
          BottomNavigationBarItem(
              icon: Icon(
                Icons.favorite,
                color: Colors.black,
              ),
              title: Text("")),
          BottomNavigationBarItem(
              icon: Icon(
                Icons.bookmark,
                color: Colors.black,
              ),
              title: Text("")),
        ],
      ),
    );
  }
}

class PageViewWidget extends StatefulWidget {
  @override
  _PageViewWidgetState createState() => _PageViewWidgetState();
}

class _PageViewWidgetState extends State<PageViewWidget> {
  List urlsOfImage = UrlsOfImage.build();

  PageController pageController;
  double viewportFraction = 0.8;
  double pageOffset;

  @override
  void initState() {
    super.initState();
    pageController =
        PageController(initialPage: 0, viewportFraction: viewportFraction)
          ..addListener(() {
            setState(() {
              pageOffset = pageController.page;
            });
          });
  }

  @override
  Widget build(BuildContext context) {
    return PageView.builder(
      controller: pageController,
      itemCount: urlsOfImage.length,
      itemBuilder: (context, index) {
        // 当 pageOffset-index > 1 时，( 1 - (pageOffset-index).abs()) 必定为负数，因此必定取值 viewportFraction。
        // 由此就得到效果，当前页面 pageOffset 的 scale 为 1.8,其他页面的比例为 viewportFraction。
        double scale = max(viewportFraction,
            (1 - (pageOffset - index).abs()) + viewportFraction);

        double angle = (pageOffset - index).abs();

        if (angle > 0.5) {
          angle = 1 - angle;
        }
        return Container(
          padding: EdgeInsets.only(
            right: 10,
            left: 20,
            // scale 会影响 padding top 的取值，scale 越大 top 越小，图片也就自动拉高了。
            top: 100 - (scale * 25),
            bottom: 50,
          ),
          child: Transform(
              transform: Matrix4.identity()
                ..setEntry(
                  3,
                  2,
                  0.001,
                )
                ..rotateY(angle),
                // alignment: Alignment.center,
              child: Image.asset(
                urlsOfImage[index].url,
                width: MediaQuery.of(context).size.width,
                fit: BoxFit.cover,
              )),
        );
      },
    );
  }
}

class UrlsOfImage {
  String url;
  String name;

  UrlsOfImage(this.url, this.name);

  static List<UrlsOfImage> build() {
    return [
      UrlsOfImage("assets/images/1.jpg", "Japan"),
      UrlsOfImage("assets/images/2.jpg", "Franch"),
      UrlsOfImage("assets/images/3.jpg", "Paris"),
      UrlsOfImage("assets/images/4.jpg", "London"),
      UrlsOfImage("assets/images/5.jpg", "China"),
    ];
  }
}
