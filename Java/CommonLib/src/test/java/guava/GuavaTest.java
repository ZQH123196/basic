package guava;

import com.google.common.base.CaseFormat;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class GuavaTest {

    /**
     * 比较智能：
     * 支持多种格式， collection、数组
     * 对 null 的处理
     */
    @Test
    public void joiner() {
        String separator = ",";


        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add(null);
        // Joiner joiner = Joiner.on(separator);
        // 对空值的灵活处理
        Joiner joinerList = Joiner.on(separator)
                // 跳过 null
//                .skipNulls()
                // 对 null 的赋默认值
                .useForNull("空值默认值")
                ;
        String joinRes = joinerList.join(list);
        System.out.println(joinRes);


        // 对数组的兼容性
        String[] strings = {"a", "b", "c"};
        Joiner joinerStrings = Joiner.on(separator).skipNulls();
        String joinerStringsRes = joinerStrings.join(strings);
        System.out.println(joinerStringsRes);

        // 接近的原始代码实现
        String collect = list.stream().filter(StringUtils::isNoneBlank).collect(Collectors.joining(separator));

        System.out.println(collect);
    }

    @Test
    public void splitter() {
        String separator = ",";

        String string = "a,,,,     b   , c, d";

        Splitter splitter = Splitter.on(separator)
                .omitEmptyStrings()
                .trimResults()
                ;
        List<String> list = splitter.splitToList(string);
        System.out.println(list);
    }

    /**
     * 下划线和驼峰互转
     */
    @Test
    public void lowUnderscore2LowerCamel() {
        String name = "student_name";
        String to = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name);
        System.out.println(to);

    }

    /**
     * 原生 List 能力的增强，同理 collect 增强还有 Sets、Maps
     */
    @Test
    public void lists() {

        ArrayList<String> list = Lists.newArrayList("a", "b", "c", "d", "e");

//        便捷的分组
//        两两一组
        List<List<String>> partition = Lists.partition(list, 2);
        System.out.println(partition);
    }

    /**
     * int 增强，同理 primitives 还有 double 之类的
     * 性能会比包装类的表现更好，使用起来也人性化
     *
     */
    @Test
    public void ints() {
        List<Integer> integers = Ints.asList(1, 2, 3);
        System.out.println(integers);
    }

    /**
     * Multimap 可重复 map
     * 很人性化，正常要实现这种是要 Map<List> 的，这里使用 entryset 来表达可计数的情况，elementset 表达原始 set 的语义
     * 轻松表达 Map<String, Collection<String>>
     */
    @Test
    public void multimap() {
        // 等价 Map<String, Collection<String>>
        Multimap<@Nullable String, @Nullable String> multimap = HashMultimap.create();
        multimap.put("a", "a1");
        multimap.put("a", "a2");
        multimap.put("b", "b1");
        multimap.put("c", "c1");
        System.out.println(multimap);
        System.out.println("multimap.values() = " + multimap.values());
        System.out.println(multimap.containsEntry("a", "a2"));
        // 更加丝滑，不需要加什么判断 list.size 之类的东西了， values 自动帮我们 flatten
        for (String value : multimap.values()) {
            System.out.println(value);
        }

        // 转换为 Map<String, Collection<String>>
        Map<@Nullable String, Collection<@Nullable String>> map = multimap.asMap();
        System.out.println(map);
    }


    /**
     * Multisets 可重复 set
     * 很人性化，正常要实现这种是要 Map<List> 的，这里使用 entryset 来表达可计数的情况，elementset 表达原始 set 的语义
     */
    @Test
    public void multiset() {
        Multiset<@Nullable Object> multiset = HashMultiset.create();
        multiset.add("a");
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("d");
        multiset.add("d");

        Set<Multiset.Entry<@Nullable Object>> entries = multiset.entrySet();
        // [a x 2, b, c, d x 2]
        System.out.println(entries);
        for (Multiset.Entry<Object> entry : entries) {
            System.out.println(entry.getElement() + ": " + entry.getCount());
        }

        System.out.println("---------------------");

        Set<@Nullable Object> elementSet = multiset.elementSet();
        System.out.println(elementSet);
        for (Object o : elementSet) {
            System.out.println(o);
        }
    }

    /**
     * 不可变
     * 原生 jdk 的不可变实现问题在于，可以通过原始的引用去改变它，这就需要写额外的代码去隐藏原始引用并确保只暴露被修饰后的不可变对象。
     * 但是。不可变对象往往都是外部传入的，也就是这种是不可控的，就需要我们要深拷贝创建一个全新的不可变对象，与传入的对象一刀两断，这样才能正确的返回这个不可变对象。
     * 这个过程 guava 帮我们实现了
     */
    @Test
    public void immutableList() {
        ArrayList<String> list = Lists.newArrayList("a", "b", "c");

        ImmutableList<String> immutableList = ImmutableList.copyOf(list);
        List<String> unmodifiableList = Collections.unmodifiableList(list);

        list.add("new");

        // 可以看到，unmodifiableList 也加上了 new 被改变了，而 guava 的 immutableList 则没有。
        System.out.printf("list: %s\n", list);
        System.out.printf("unmodifiableList: %s\n", unmodifiableList);
        System.out.printf("immutableList: %s\n", immutableList);
    }


    /**
     * 找两个 map 的差异数据
     * 场景：对账
     */
    @Test
    public void diff4Map() {
        ImmutableMap<String, String> leftMap = ImmutableMap.of("a", "1", "b", "2", "c", "3");
        ImmutableMap<String, String> rightMap = ImmutableMap.of("a", "3", "b", "2", "d", "4");


        MapDifference<String, String> diff = Maps.difference(leftMap, rightMap);

        // 获取全部结果
        System.out.println(diff);
        // 只有 k、v 都一样才能匹配 false
        System.out.println(diff.areEqual());
        // 找出两个 map 中 k、v 都相同的值 {b=2}
        System.out.println(diff.entriesInCommon());
        // 找出 k 相同，v 不同的值 {a=(1, 3)}
        System.out.println(diff.entriesDiffering());
        // 以左边的 key 为重心，找出 key 在左边不在右边的值 {c=3}
        System.out.println(diff.entriesOnlyOnLeft());
        // 与上面方向相反 {d=4}
        System.out.println(diff.entriesOnlyOnRight());

    }

}
