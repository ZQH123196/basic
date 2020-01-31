/*
相关视频：https://www.bilibili.com/video/av55251444/?spm_id_from=333.788.videocard.11
相关代码：https://github.com/lohanidamodar/flutter_ui_challenges/blob/master/lib/src/pages/profile/profile4.dart
 */
import 'package:flutter/material.dart';

class ProfileFourPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        elevation: 0,
        automaticallyImplyLeading: false,
        actions: <Widget>[
          IconButton(icon: Icon(Icons.more_vert, color: Colors.black,),onPressed: (){},)
        ],
      ),
      body: SingleChildScrollView(
        child: Column(
          children: <Widget>[
            Row(
              children: <Widget>[
                Container(
                  width: 80.0,
                  child: Image.asset("lib/demo/travel_home_page/assets/images/1.jpg"),),
                SizedBox(width: 20.0,),
                Column(
                  crossAxisAlignment: CrossAxisAlignment.start,

                  children: <Widget>[
                    Text("阿斯顿马丁"),
                    Text("骑士"),
                    Text("喜欢呼吸空气"),
                  ],

                )
              ],
            )
          ],
        ),
      ),
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
  void initState() {

  }

  @override
  Widget build(BuildContext context) {
    return null;
  }


}