/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyslav.fileDate;

import com.shyslav.controller.UploadController;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class dataDelete {

    public static void Delete(String name) {
        Properties props = new Properties();
        try (InputStream in = UploadController.class.getResourceAsStream("application.properties")) {
            props.load(in);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        String path = props.getProperty("downloadDirectory") + "/" + name;
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }
}
