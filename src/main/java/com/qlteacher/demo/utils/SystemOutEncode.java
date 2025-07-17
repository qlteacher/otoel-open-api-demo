package com.qlteacher.demo.utils;

import java.nio.charset.StandardCharsets;

/**
 * @author 江立国
 */
public class SystemOutEncode {

    public static void outEncodeSet(){
        try {
            System.setOut(new java.io.PrintStream(System.out, true, StandardCharsets.UTF_8));
            System.setErr(new java.io.PrintStream(System.err, true, StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
