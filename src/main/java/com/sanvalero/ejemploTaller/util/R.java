package com.sanvalero.ejemploTaller.util;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * Creado por @author: Javier
 * el 28/10/2020
 */
public class R {

    public static InputStream getImage(String name) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("images" + File.separator + name);
    }

    public static URL getUI(String name){
        return Thread.currentThread().getContextClassLoader().getResource("ui" + File.separator + name);
    }

}
