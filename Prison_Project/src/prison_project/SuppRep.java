package prison_project;


import java.io.File;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author greg1
 */
public class SuppRep {
    public boolean deleteAll(File dir) {
        if (dir.isDirectory()) {
            File[] children = dir.listFiles();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteAll(children[i]);
                if (!success) return false;                
            }
        }
        return dir.delete();
    }
   
}
