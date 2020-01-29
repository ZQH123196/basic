/*
相关视频：https://www.bilibili.com/video/av75494009
相关代码：https://github.com/jiang111/flutter_code
 */

import 'package:flutter/material.dart';

import 'dart:math';

class Travel_home extends StatefulWidget {
  @override
  _Travel_homeState createState() => _Travel_homeState();
}

class _Travel_homeState extends State<Travel_home> {
  @override
  Widget build(BuildContext context) {
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
  double pageOffset = 0; // 当前页面的相对偏移值

  @override
  void initState() {
    super.initState();
    pageController = PageController(initialPage: 0, viewportFraction: viewportFraction);
    pageController.addListener( () {
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
        // 报 The method '-' was called on null. 错误，只是 flutter 的问题，尝试重启，代码绝对能运行，尝试动一下。
        // 当 pageOffset-index > 1 时，( 1 - (pageOffset-index).abs()) 必定为负数，因此必定取值 viewportFraction。
        // 由此就得到效果，当前页面 pageOffset 的 scale 为 1.8,其他页面的比例为 viewportFraction。
        double scale = max(viewportFraction, (1-(pageOffset-index).abs()) + viewportFraction);
//        print("scale=[$scale],viewportFraction=[$viewportFraction],pageOffset=[$pageOffset],index=[$index]");

        double angle = (pageOffset-index).abs();

        if (angle > 0.5) {
          angle = 1-angle;
        }
        return Container(
          padding: EdgeInsets.only(
            right: 10,
            left: 20,
            // scale 会影响 padding top 的取值，scale 越大 top 越小，图片也就自动拉高了。
            top: 100-(scale * 25),
            bottom: 50,
          ),
          // child: Text("data"),
          child: Transform(
              transform: Matrix4.identity()
                ..setEntry(
                  3,
                  2,
                  0.001,
                )
                ..rotateY(angle),
              // alignment: Alignment.center,
              child: Stack(
                children: <Widget>[
                  Image.asset(
                    urlsOfImage[index].url,
                    width: MediaQuery.of(context).size.width,
                    fit: BoxFit.cover,
                  ),
                  Positioned(
                    bottom: 30,
                    left: 20,
                    child: AnimatedOpacity( // 文字隐藏效果
                      opacity: angle == 0 ? 1 : 0,
                      duration: Duration(
                        milliseconds: 200,
                      ),
                      child: Text(
                        urlsOfImage[index].name,
                        style: TextStyle(
                          color: Colors.white,
                          fontSize: 25,
                          fontWeight: FontWeight.bold,
                          letterSpacing: 1.2,
                        ),
                      ),
                    ),
                  )
                ],
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
    final String parentPath = 'lib/demo/travel_home_page';
    return [
      UrlsOfImage("$parentPath/assets/images/1.jpg", "Japan"),
      UrlsOfImage("$parentPath/assets/images/2.jpg", "Franch"),
      UrlsOfImage("$parentPath/assets/images/3.jpg", "Paris"),
      UrlsOfImage("$parentPath/assets/images/4.jpg", "London"),
      UrlsOfImage("$parentPath/assets/images/5.jpg", "China"),
    ];
  }
}
