package cgtjr.academics.general;

import static cgtjr.academics.elctrclengnrng.imgprcssng.annotate.AnnotateFeatureVar.userLogonIdKey;

public class ThemeSet {
    //private static ThemeSet theThemeSet;

    private static ThemeSet theThemeSet;
    private static String themeName = "";
    private static String themeDirPath = "";
    private static String applicationName = "";
    private static String baseSoundDirPath = "";
    private static String baseImageDirPath = "";
    private static String documentBase;
    private static final String userLogonIdKey = "userlogonid";
    private static String docURL;
    public static String userLogonIdValue = "";

    public ThemeSet(String myAppName, String myThemeName, String mythemeDirPath) {
        themeName = myAppName;
        applicationName = myThemeName;
        themeDirPath = mythemeDirPath;
        setSoundBaseDirPath();
        setImageBaseDirPath();
    }

    public static ThemeSet getThemeSet(String myAppName, String myThemeName, String mythemeDirPath) {
        if (theThemeSet == null) {
            theThemeSet = new ThemeSet(myAppName, myThemeName, mythemeDirPath);
        } else {
            theThemeSet.setApplicationName(myAppName);
            theThemeSet.setThemeName(myThemeName);
            theThemeSet.setThemeDirPath(mythemeDirPath);
        }
        return theThemeSet;
    }

    public static String getDocURL() {
        return docURL;
    }

    public static void setDocURL(String myDocURL) {
        ThemeSet.docURL = myDocURL;
    }

    public static String getDocumentBase() {
        return documentBase;
    }

    public static void setDocumentBase(String myDocumentBase) {
        ThemeSet.documentBase = myDocumentBase;
    }

    public void setSoundBaseDirPath() {
        String value = "";
        try {
            value = System.getProperty("user.dir");
            //System.out.println("Themeset property = "+value);
        } catch (Exception RE) {
            value = "unknown property";
        }
        if (value != null) {
            value = "../res/";
        } else {
            value = "resource:/";
        }

        baseSoundDirPath = value;
        //System.out.println("ThemeSet : baseSoundDirPath = "+baseSoundDirPath);
    }

    public static String getSoundBaseDirPath() {
        String value = null;
        try {
            value = System.getProperty("user.dir");
            //System.out.println("Themeset property = "+value);
        } catch (Exception RE) {
            //value = "unknown property";
        }
        if (value != null) {
            value = "../res/";
        } else {
            value = documentBase + "res/";
        }
        baseSoundDirPath = value;
        //System.out.println("ThemeSet : baseSoundDirPath = "+baseSoundDirPath);
        return baseSoundDirPath;
    }

    public void setImageBaseDirPath() {
        String value = "";
        try {
            value = System.getProperty("user.dir");
            //System.out.println("ThemeSet : property = "+value);         
        } catch (Exception RE) {
            value = "unknown property";
        }
        if (value != null) {
            value = "res/";
        } else {
            value = documentBase;
        }
        baseImageDirPath = value;
        //System.out.println("ThemeSet : baseImageDirPath = "+baseImageDirPath);
    }

    public static String getImageBaseDirPath() {

        String value = null;

        try {
            value = System.getProperty("user.dir");
            //System.out.println("Themeset property = "+value);         
        } catch (Exception RE) {
        }

        if (value != null) {
            //value = "res/";
            value = "file:///" + value + "/build/classes/res/";
        } else {
            //value = "/";
            value = documentBase + "res/";
        }
        baseImageDirPath = value;
        System.out.println("ThemeSet : baseImageDirPath = " + baseImageDirPath);

        return baseImageDirPath;
    }
    /*
     public String getImageBaseDirPath()
     {
     return baseImageDirPath; 
     }*/
    /*
     public static ThemeSet getThemeSet(String myAppName,String myThemeName,String mythemeDirPath)
     {
     theThemeSet = new ThemeSet(myAppName,myThemeName,mythemeDirPath);
     return theThemeSet;
     }
     */

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String aString) {
        themeName = aString;
    }

    public String getThemeDirPath() {
        return themeDirPath;
    }

    public void setThemeDirPath(String aString) {
        themeDirPath = aString;
    }

    public String getImageDirPath() {
        return themeDirPath + "images/";
    }

    public String getSoundDirPath() {
        return themeDirPath + "sound/";
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String aString) {
        applicationName = aString;
    }

    public String getImageFileName() {
        //Theme image, for selection purposes.
        return themeDirPath + "images/theme_image";
    }

    public static String getUserLogonIdValue() {
        return userLogonIdValue;
    }

    public static void setUserLogonIdValue(String myUserLogonIdValue) {
        userLogonIdValue = myUserLogonIdValue;
    }

    public static String getUserLogonIdKey() {
        return userLogonIdKey;
    }

    public String toString() {
        return themeName;
    }
}
