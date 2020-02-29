/*
相关视频：https://www.bilibili.com/video/av55251444/?spm_id_from=333.788.videocard.11
相关视频：https://www.youtube.com/watch?v=ERUvJbtaGMI
相关代码：https://github.com/lohanidamodar/flutter_ui_challenges/blob/master/lib/src/pages/profile/profile4.dart
相关截图：https://github.com/lohanidamodar/flutter_ui_challenges#profile-screens
*/

import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:url_launcher/url_launcher.dart';

class ProfileFourPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        elevation: 0,
        automaticallyImplyLeading: false,
        actions: <Widget>[
          IconButton(icon: FaIcon(FontAwesomeIcons.commentDots), color: Colors.black54, onPressed: (){},),
          IconButton(
            icon: Icon(
              Icons.more_vert,
              color: Colors.black,
            ),
            onPressed: () {},
          )
        ],
      ),
      body: SingleChildScrollView(
        child: Column(
          children: <Widget>[
            _buildHeader(),
            Container(
              margin: const EdgeInsets.all(16.0),
              padding: const EdgeInsets.all(16.0),
              decoration: BoxDecoration(color: Colors.grey.shade200),
              child: Text("个人描述，描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述"),
            ),
            _buildTitle("Skills"),
            _buildSkillRow("eat", 0.85),
            _buildSkillRow("java", 0.25),
            _buildSkillRow("python", 0.75),
            _buildSkillRow("c++", 0.15),
            SizedBox(
              height: 20.0,
            ),
            _buildTitle("Experience"),
            _buildExperienceRow(
                company: "conpany",
                position: "SB developer",
                duration: "2010 - 2012"),
            _buildExperienceRow(
                company: "222222",
                position: "SB developer",
                duration: "2013 - 2019"),
            SizedBox(
              height: 20.0,
            ),
            _buildTitle("Education"),
            _buildExperienceRow(
                company: "college",
                position: "student",
                duration: "2008 - 2009"),
            SizedBox(
              height: 20.0,
            ),
            _buildTitle("Socials"),
            Row(
              children: <Widget>[
                SizedBox(
                  width: 20.0,
                ),
                IconButton(
                    iconSize: 18.0,
                  color: Colors.indigo,
                  icon: FaIcon(FontAwesomeIcons.facebookF),
                  onPressed: () {
                      _launchURL("https://github.com/");
                  },
                ),
                SizedBox(
                  width: 10.0,
                ),
                IconButton(
                    iconSize: 18.0,
                    color: Colors.indigo,
                    icon: FaIcon(FontAwesomeIcons.github),
                    onPressed: () {
                      _launchURL("https://pub.dev/");
                    }
                ),
                SizedBox(width: 10.0,)
              ],
            ),
            SizedBox(height: 20.0,)
          ],
        ),
      ),
    );
  }


  _launchURL(String url) async {
    // const url = 'https://flutter.dev';
    if (await canLaunch(url)) {
      await launch(url);
    } else {
      throw 'Could not launch $url';
    }
  }

  ListTile _buildExperienceRow(
      {String company, String position, String duration}) {
    return ListTile(
      leading: Padding(
        padding: const EdgeInsets.only(top: 8.0, left: 20.0),
        child: Icon(
          FontAwesomeIcons.solidCircle,
          size: 12.0,
          color: Colors.black54,
        ),
      ),
      title: Text(company),
      subtitle: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Text("$position ($duration)"),
        ],
      ),
    );
  }

  Row _buildSkillRow(String title, double level) {
    return Row(
      children: <Widget>[
        SizedBox(width: 16.0),
        Expanded(
            flex: 1,
            child: Text(
              title,
              textAlign: TextAlign.right,
            )),
        SizedBox(
          width: 10.0,
        ),
        Expanded(
          flex: 5,
          child: LinearProgressIndicator(
            value: level,
          ),
        )
      ],
    );
  }

  Padding _buildTitle(String title) {
    return Padding(
          padding: const EdgeInsets.only(left: 16.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            Text(
            "$title".toUpperCase(),
            style: TextStyle(fontSize: 18.0, fontWeight: FontWeight.bold),
          ),
        Divider(color: Colors.black54,)
          ]
        ),
      
    );
  }

  Row _buildHeader() {
    return Row(
      children: <Widget>[
        Container(
          // color: Colors.blueGrey,
          width: 80.0,
          height: 80.0,
          child: CircleAvatar(
            radius: 40,
            backgroundColor: Colors.grey,
            child: CircleAvatar(
              radius: 35.0,
              backgroundImage: AssetImage("lib/demo/travel_home_page/assets/images/1.jpg"),
            ),
          )
        ),
        SizedBox(
          width: 20.0,
        ),
        Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Text(
              "阿斯顿马丁",
              style: TextStyle(fontSize: 18.0, fontWeight: FontWeight.bold),
            ),
            Text("骑士"),
            Row(
              children: <Widget>[
                Icon(Icons.favorite, color: Colors.grey),
                Text(
                  "喜欢呼吸空气",
                  style: TextStyle(color: Colors.grey),
                ),
              ],
            ),
          ],
        )
      ],
    );
  }
}

class PersonalResume extends StatefulWidget {
  @override
  State createState() {
    return _PersonalResume_State();
  }
}

class _PersonalResume_State extends State<PersonalResume> {
  @override
  void initState() {}

  @override
  Widget build(BuildContext context) {
    return null;
  }
}
