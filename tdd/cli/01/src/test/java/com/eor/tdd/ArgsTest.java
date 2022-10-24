package com.eor.tdd;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArgsTest {

    /**
     * 1. 思考其是怎么被使用的
     * 2. 查看多种 API 实现中那种最友好
     * 3. 选定实现思路
     * -l -p 8080 -d /usr/logs
     *
      */
    @Test
    public void should() {
        // l:d -> -l 是 bool 类型
        // p:d -> -p 是 十进制数
        // d:s -> -d 是 String
        Arguments args = Args.parse("l:b, p:d, d:s", "-l", "-p", "8080", "-d", "/usr/logs");
        args.getBool("l");
        args.getInt("p");

        // 很明显下方的 api 使用上的友好程度要高于上面的
        Options options = Args.parse(Options.class, "-l", "-p", "8080", "-d", "/usr/logs");
        options.logging();
        options.port();
    }


    /**
     * 考虑实现方法
     * -l -p 8080 -d /usr/logs
     * 数组实现 [-l], [-p, 8080], [-d, /usr/logs]
     * 哈希表实现 {-l:[], -p:[8080, ], -d:[/usr/logs, ]}
     * 都可以实现功能，选用数组下标来实现
     */



    static record Options(@Option("l") boolean logging, @Option("p") int port, @Option("d") String dirctory) {}
    static record ListOptions(@Option("g") String[] group,  @Option("d") int[] decimals) {}

    @Test
    public void should_example_1() {
        Options options = Args.parse(Options.class, "-l", "-p", "8080", "-d", "/usr/logs");
        assertTrue(options.logging());
        assertEquals(8080, options.port());
        assertEquals("/usr/logs", options.dirctory());
    }

    // -g this is a list -d 1 2 -3 5　
    @Test
    public void should_example_2() {
        ListOptions listOptions = Args.parse(ListOptions.class, "-g", "this", "is", "a", "list", "-d", "1", "2", "-3", "5");
        assertArrayEquals(new String[]{"this", "is", "a", "list"}, listOptions.group());
        assertArrayEquals(new int[]{1, 2, 3, 5}, listOptions.decimals());
    }


    /** 功能分解与任务列表
     * -l -p 8080 -d /usr/logs
     *
     *  Single Option:
     *  TODO:    - Bool -l
     *  TODO:    - Integer -p 8080
     *  TODO:    - String -d /usr/logs
     *  multi options:
     *  TODO:    -l -p 8080 -d /usr/logs
     *  sad path:
     *  TODO:    - bool -l t / -l t f
     *  TODO:    - int -p/ -p 8080 8999
     *  TODO:    - string -d/ -d /user/logs /user/vars
     *  default valur:
     *  TODO:    - bool: false
     *  TODO:    - int: 0
     *  TODO:    - string: ""
     *
     */
}
