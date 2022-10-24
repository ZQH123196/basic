package org.eor.example;

import org.apache.ibatis.io.Resources;

import java.io.File;
import java.io.IOException;

public class Tmp {
    public static void main(String[] args) throws IOException {
        some();
    }

    public static void some() throws IOException {
        File resourceAsFile = Resources.getResourceAsFile("");
    }
}
