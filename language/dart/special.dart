// TODO https://dart.dev/guides/language/language-tour#functions 

// dart 中的 => expr语法是对{ return expr; }的简写，并非真正的函数。
// 所以 => 并不等同于我们所说的箭头表达式，只是对返回值的语法塘。方法体只包含一个表达式时，可以使用箭头表达式方法进行简写。
// 因此 JS 中可以用 => 当作匿名函数，而 dart 不能用 => 要用 (){} 才能作为匿名函数
 
// TODO 初始化检查
void main(List<String> args) {
  
  // is!
  print(1 is! String);

  // 常量 
  dynamic t = const [1, 2];
  print(t ==
      const [
        1,
        2,
      ]);
  print(const [
        1,
        2,
      ] ==
      const [
        1,
        2,
      ]);

  var tt = const {'first': 'first'};
  print(tt == const {'first': 'first'});
  print(const {'first': 'first'} == const {'first': 'first'});

  print(Point(null, null).x == null);
  print(Point.name1(1, 1).x == 1);
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

  print("返回同一个实例");
  var logger = new Logger('UI');
  var logger2 = new Logger("UI");
  print("logger == logger2 :==> ${logger == logger2}");

  print("返回新实例");
  var logger3 = new Logger("newUI");
  print("logger == logger3 :==> ${logger == logger3}");

  t = varAdmitControl();
  t.x = 10086;
  print(t.x);

  final v = Vector(2, 3);
  final w = Vector(2, 2);

  print(v + w == Vector(4, 5));
  print(v - w == Vector(0, 1));
  print(Vector(1, 2) == Vector(1, 2));
  print(Vector(1, 2).hashCode == Vector(1, 2).hashCode);

  // 级联表达式（..）允许你在同一个对象上连续使用操作符。".."语法调用一个方法（getter或setter）并丢弃它的返回值，同时返回级联操作符最初的接收者。s
  var sortedColors = ['red', 'green', 'orange', 'blue', 'black', 'pink'].sublist(1, 4).sort();
  // print(sortedColors); // 报错找不到
  var colors = ['red', 'green', 'orange', 'blue', 'black', 'pink'].sublist(1, 4);
  colors.sort();
  print(colors); // 这样就可以
  // 注意双点，在这里级联用的是已经构造完毕的对象了，相当于在双点之前的已经运行了，自己的级联相当于另起一行，跟 i++ 这种会展开很多行的语法糖类似。
  var sortedColors1 = ['red', 'green', 'orange', 'blue', 'black', 'pink'].sublist(1, 4)..sort();
  print(sortedColors1); // 级联最方便
}

class Todo {
  final String who;
  final String what;

  const Todo(this.who, this.what);
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

  Point(this.x, this.y);

  // Named constructor
  // 类属性自动初始化语法糖
  Point.name1(this.x, this.y) {}

  Point.name2() {
    x = 2;
    y = 2;
  }

  Point.name3(num x) : this(x, null);
}

class varAdmitControl {
  int some = 0;
  int get readOnly => 1;
  int get x => some;
  set x(int v) => some = v;
  varAdmitControl();
}

/// 作为入参不错，可以判断入参类型
bool isBool(dynamic arg) {
  if (arg is bool) return true;
  return false;
}

class Logger {
  final String name;
  bool mute = false;
  static final Map<String, Logger> _cache = <String, Logger>{};

  // 工厂构造函数，如果Logger存在name值相同的记录，则在new 一个Logger时候返回原对象 （原理: 工厂构造函数可以缓存实例）
  factory Logger(String name) {
    if (_cache.containsKey(name)) {
      return _cache[name];
    } else {
      final logger = new Logger._internal(name);
      _cache[name] = logger;
      return logger;
    }
  }

  // 给name赋值
  Logger._internal(this.name);

  // 里面的方法
  void log(String msg) {
    if (!mute) {
      print(msg);
    }
  }
}

class Vector {
  final int x, y;

  Vector(this.x, this.y);
  // 重载 == 号还需要重载 hashcode
  Vector operator +(Vector rightV) => Vector(x + rightV.x, y + rightV.y);
  Vector operator -(Vector rightV) => Vector(x - rightV.x, y - rightV.y);

  // Override hashCode using strategy from Effective Java,
  // Chapter 11.
  @override
  int get hashCode {
    int result = 17;
    result = 37 * result + this.x.hashCode;
    result = 37 * result + this.y.hashCode;
    return result;
  }

  // You should generally implement operator == if you
  // override hashCode.
  @override
  bool operator ==(dynamic other) {
    if (other is! Vector) return false;
    return (other.x == this.x && other.y == this.y);
  }
}

