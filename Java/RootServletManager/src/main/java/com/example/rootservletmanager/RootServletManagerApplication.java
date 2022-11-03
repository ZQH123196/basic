package com.example.rootservletmanager;

import cn.hutool.core.util.RuntimeUtil;
import com.sun.jna.Platform;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author ZQH12
 */
@SpringBootApplication
@Slf4j
public class RootServletManagerApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(RootServletManagerApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("ApplicationRunner！");
        log.info("--------------------------------");
        String classPathDir = getClass().getClassLoader().getResource("/").getFile();
        File targetJar = new File(classPathDir+"ruoyi-gateway.jar");
        String jarAbsolutePath = targetJar.getAbsolutePath();
        log.info("---------------路径打印完成！-----------------");


        ProcessBuilder processBuilder = new ProcessBuilder();
        // 将子进程的错误输出重定向到子进程的标准输出，这样我们就只需要或许子进程的标准输出就行了
        processBuilder.redirectErrorStream();
        List<String> cmds = null;
        if (Platform.isWindows()) {
            cmds = new ArrayList<String>() {{
                add("cmd");
                add("/c");
                add("java");
                add("-jar");
                add(jarAbsolutePath);
            }};
        }

        log.info("start child process! child process out:");
        Process process = processBuilder.command(cmds.toArray(new String[cmds.size()])).start();
        ExecutorService singleThreadExecutor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1));
        singleThreadExecutor.submit(() -> {
            try {
                InputStream inputStream = new BufferedInputStream(process.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "GB18030"));
                String line = null;

                while ((line = br.readLine()) != null) {
                    log.info(line);
//                Thread.sleep(100);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }finally {
                process.destroyForcibly();
            }
        });




    }


}
