import 'package:flutter/material.dart';


class DrawerDemo extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Drawer(
          child: ListView(
            padding: EdgeInsets.zero,
            children: <Widget>[
              UserAccountsDrawerHeader(
                accountName: Text('eor', style: TextStyle(fontWeight: FontWeight.bold)),
                accountEmail: Text('eoreoreor@gmail.com'),
                currentAccountPicture: CircleAvatar(backgroundImage: NetworkImage('https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1888269904,626529998&fm=26&gp=0.jpg'),),
                decoration: BoxDecoration(
                  color: Colors.yellow[400],
                  image: DecorationImage(
                    image: NetworkImage("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2523632251,3308731951&fm=26&gp=0.jpg"),
                    fit: BoxFit.cover,
                    colorFilter: ColorFilter.mode(Colors.yellow[300].withOpacity(0.5), BlendMode.hardLight)
                    )
                  ),
              ),
              
              DrawerHeader(
                child: Text('header'.toUpperCase()),
                decoration: BoxDecoration(color: Colors.grey[100]),
              ),
              ListTile(
                title: Text('Message', textAlign: TextAlign.right),
                trailing: Icon(Icons.message, color: Colors.black12, size: 22.0),
              ),
              ListTile(
                title: Text('Favorite', textAlign: TextAlign.right),
                trailing: Icon(Icons.favorite, color: Colors.black12, size: 22.0),
              ),
              ListTile(
                title: Text('Settings', textAlign: TextAlign.right),
                trailing: Icon(Icons.settings, color: Colors.black12, size: 22.0),
              ),
            ],
          ),
        );
  }
}
