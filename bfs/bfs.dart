import 'dart:collection';

main(List<String> args) {
    List<String> grid = [
        "+++++++++++++++",
        "+             +",
        "+             +",
        "+             +",
        "+     e       +",
        "+             +",
        "+             +",
        "+             +",
        "+ s           +",
        "+++++++++++++++",
    ];
    Map result = searchForStartAndEnd(grid);
    List start = result['start'];
    List end = result['end'];

    // bfs(start, grid);

    var t1 = [1, 2];
    var t2 = [3, 4];
    var m1 = {t1: t2};
    print(m1[[1, 2]]);
    print(m1[t1]);

    

}

void bfs(List start, List<String> grid) {
    // [row, col]
    var direction = [
                    [-1, -1], [-1, 0], [-1, 1],
                    [0, -1], [0, 1],
                    [1, -1], [1, 0], [1, 1]];

    Queue<List> queue = new Queue();
    Map<String, String> related = {};
    List<String> visited = [];
    List endPoint = null;
    
    queue.add(start);
    visited.add(start.toString());
    while (queue.length > 0 && endPoint == null) {
        List nowPoint = queue.first;
        queue.removeFirst();

        for (var dir in direction) {
        int row = dir[0];
        int col = dir[1];

        int nowRowTo = nowPoint[0] + row;
        int nowColTo = nowPoint[1] + col;

        if ( (nowRowTo >= 0 && nowColTo >= 0)
        &&   (grid[nowRowTo][nowColTo] != '+') 
        &&   (visited.indexOf([nowRowTo, nowColTo].toString()) == -1)) {
            visited.add([nowRowTo, nowColTo].toString());
            queue.add([nowRowTo, nowColTo]);
            related[[nowRowTo, nowColTo].toString()] = [nowPoint[0], nowPoint[1]].toString();

            if (grid[nowRowTo][nowColTo] == 'e') {
              endPoint = [nowRowTo, nowColTo];
              break;
            }
        }
      }
    }
    print("bfs found the endPoint:");
    print(endPoint);

    String nowPoint = endPoint.toString();
    print("print the result path:");
    print(nowPoint);
    while ( related.containsKey(nowPoint) 
    && !(related[nowPoint] == start.toString()) ) {
      nowPoint = related[nowPoint].toString();
      print(nowPoint);
    }
    nowPoint = related[nowPoint].toString();
    print(nowPoint);
}
// 
Map searchForStartAndEnd(List<String> grid) {
    Map result = {};
    for (var row = 0; row < grid.length; row++) {
        String s = grid[row];
        for (var col = 0; col < s.length; col++) {
            if (s[col] == 's') {
                result['start'] = [row, col];
                print("start:");
                print([row, col]);
            }
            if (s[col] == 'e') {
                result['end'] = [row, col];
                print("end:");
                print([row, col]);
            }
        }
    }
    print("search done!");
    return result;
}
