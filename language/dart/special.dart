void main(List<String> args) {
  // is!
  print(1 is! String);

  // 常量
  var t = const [1, 2];
  print(t == const [1, 2,]);
  print(const [1, 2,] == const [1, 2,]);

  var tt = const {'first': 'first'};
  print(tt == const {'first': 'first'});
  print(const {'first': 'first'} == const {'first': 'first'});



  print(Point().x == null);
  print(Point.name1().x == 1);
  print(Point.name2().x == 2);

  // dynamic 判断类型
  dynamic name = 'Bob';
  print(name.runtimeType == String);
  print(isBool(name) == false);

  name = false;
  print(name.runtimeType == bool);
  print(isBool(name) == true);

  // If the expression to the right of the spread operator might be null, you can avoid exceptions by using a null-aware spread operator (...?):
  var list;
  var list2 = [0, ...?list];
  assert(list2.length == 1);

  var list3 = [
    'a',
    if (false) 'b',
    'c',
    if (true) 'd',
    for (int i = 0; i < 3; i++) '#$i'
  ];
  print(list3.contains('b') == false);
  print(list3.contains('d') == true);
  print(list3.contains('#2'));

  var setTest = {'1', '2'};
  setTest.add('1');
  print(setTest.runtimeType.toString() == "_CompactLinkedHashSet<String>");
  print(setTest.length == 2);

  var mapTest1 = {"k": "v"};
  print(mapTest1.runtimeType.toString() ==
      "_InternalLinkedHashMap<String, String>");
  var mapTest2 = {...mapTest1, 'kk': 'vv'};
  print(mapTest1.length == 1);
  print(mapTest2.length == 2);

  namedParameters('emmm', who: 'eor', msg: '命名变量');
  namedParameters('emmm', who: 'eor');

  print(positionalParameters('eor', '呸！') == 'eor says 呸！default!');
  print(positionalParameters('eor', '呸！', 'eeeeor!') == 'eor says 呸！eeeeor!');

  print(doStuff(list: [10086])[0] == 10086);
  print(doStuff(gifts: {"gg": "gg"})["gg"] == "gg");
}

// 默认变量值必须设置在 {} 或 []
void namedParameters(String emmm, {String who, String msg = 'default!'}) {
  print('hi, $who, 这是$msg, $emmm');
}

String positionalParameters(String from, String msg,
    [String more = 'default!']) {
  var result = '$from says $msg';
  if (more != null) {
    result = '$result$more';
  }
  return result;
}

// 动态返回
dynamic doStuff(
    {List<int> list = const [1, 2],
    Map<String, String> gifts = const {
      'first': 'first',
    }}) {
  if (list != const [1, 2]) return list;
  if (gifts != const {'first': 'first'}) return gifts;
  return false;
}


class Point {
  num x, y;

  Point();

  // Named constructor
  Point.name1() {
    x = 1;
    y = 1;
  }

  Point.name2() {
    x = 2;
    y = 2;
  }
}

class varAdmitControl {
  int get readOnly => 1;
}

/// 作为入参不错，可以判断入参类型
bool isBool(dynamic arg) {
  if (arg is bool) return true;
  return false;
}
