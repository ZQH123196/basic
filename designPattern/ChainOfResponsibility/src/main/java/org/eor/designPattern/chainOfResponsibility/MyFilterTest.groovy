abstract class MyFilter {
    public doFilter;
}

class MyFilterChain {
    List<MyFilterChain> filterList = new LinkedList<>();

    public void addFilter(MyFilter filter) {
        this.filterList.add(filter);
    }

    /**
     * side effect
     */
    public StringBuilder handle(StringBuilder value) {
        println filterList
        for (MyFilter filter : filterList) {
            filter.doFilter(value);
        }
        return value
    }
}

class MyFilterA extends MyFilter {
    public void doFilter(StringBuilder value) {
        value.append("MyFilterA");
    }
}

class MyFilterB extends MyFilter {
    public void doFilter(StringBuilder value) {
        value.append("MyFilterB");
    }
}


MyFilterChain filterChain = new MyFilterChain();

filterChain.addFilter(new MyFilterA())
filterChain.addFilter(new MyFilterB())

String res = filterChain.handle(new StringBuilder("res")).toString()

println("res = [${res}]")


