package com.wx.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoadPageClassTest {

    /**
     * 解析包的全路径(比你的包路径为cn.ishow.test)
     * @param webPackage
     * @return
     */
    public String resolvePackagePath(String webPackage) {
        // 扫码所有的包并把其放入到访问关系和方法放入到内存中
        File f = new File(Objects.requireNonNull(getClass().getResource("/")).getPath());
        String totalPath = f.getAbsolutePath();
        System.out.println(totalPath);
        String pageName = getClass().getPackage().getName().replace(".", "\\");
        totalPath = totalPath.replace(pageName, "");
        String packagePath = webPackage.replace(".", "\\");
        totalPath = totalPath + "\\" + packagePath;
        return totalPath;
    }

    /**
     * 解析包下面的所有类
     * @param packagePath 上一步获取包的全路径
     * @param webPackage  包(cn.ishow.test)
     * @return
     */
    public List<String> parseClassName(String packagePath, String webPackage) {
        List<String> array = new ArrayList<>();
        File root = new File(packagePath);
        resolveFile(root, webPackage, array);
        return array;

    }

    private void resolveFile(File root, String webPackage, List<String> classNames) {
        if (!root.exists())
            return;
        File[] childs = root.listFiles();
        if (childs != null && childs.length > 0) {
            for (File child : childs) {
                if (child.isDirectory()) {
                    String parentPath = child.getParent();
                    String childPath = child.getAbsolutePath();
                    String temp = childPath.replace(parentPath, "");
                    temp = temp.replace("\\", "");
                    resolveFile(child, webPackage + "." + temp, classNames);
                } else {
                    String fileName = child.getName();
                    if (fileName.endsWith(".class")) {
                        String name = fileName.replace(".class", "");
                        String className = webPackage + "." + name;
                        classNames.add(className);
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        LoadPageClassTest test = new LoadPageClassTest();
        String packageData = "com.wx.exception";
        String totalPath = test.resolvePackagePath(packageData);
        System.out.println(packageData +"   "+totalPath);
        List<String> datas = test.parseClassName(totalPath,packageData);
        System.out.println(datas);
    }

}
