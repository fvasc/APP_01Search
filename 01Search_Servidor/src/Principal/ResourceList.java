/**
 *
 */
package Principal;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ResourceList {

   final Log log = LogFactory.getLog(ResourceList.class);

   /**
    * Identificacao da versao do CVS
    */
   public static final String rcsid = "$Id$";

   private String packageName;

   private String path;

   private FilenameFilter filter;


   public static class ResourceData implements Comparable<ResourceData> {

       private String name;

       private URL url;

       private ResourceData(String name, URL url) {
           this.name = name;
           this.url = url;
       }

       public String getName() {
           return name;
       }

       public String toString() {
           return "[" + name + "," + url + "]";
       }

       public URL getUrl() {
           return url;
       }

       public int compareTo(ResourceData arg0) {
           return name.compareTo(arg0.name);
       }

   }

   public ResourceList(final String packageName,
                       final String startsWith,
                       final String endsWith) {
       this.packageName = packageName;
       path = packageName.replace('.', '/');
       filter = new FilenameFilter() {
           public boolean accept(File dir, String name) {
               boolean starts = (startsWith == null) ||
name.startsWith(startsWith);
               boolean ends = (endsWith == null) || name.endsWith(endsWith);
               return starts && ends;
           }

       };
   }

   public ResourceList(final String packageName,
                       final FilenameFilter filter) {
       this.packageName = packageName;
       path = packageName.replace('.', '/');
       this.filter = filter;
   }

   private void list(List<ResourceList.ResourceData> resourceList,
           File directory) {
       log.debug(directory + " " + directory.exists());
       if (directory.isDirectory()) {
           File[] files = directory.listFiles(filter);
           for (File file : files) {
               if (!file.isDirectory()) {
                   try {
                       resourceList.add(new
ResourceData(file.getName(), file.toURL()));
                   } catch (MalformedURLException e) {
                       log.error("Error creating URL for file "
                               + file, e);
                   }
               }
           }
       }
   }

   private void list(List<ResourceList.ResourceData> resourceList, URL jar) {
       log.debug("JAR: " + jar);
       int z = jar.getPath().indexOf("!");
       String nm = jar.getPath().substring(5, z);
       String base = jar.toExternalForm().substring(0,
jar.toExternalForm().indexOf("!")+1);
       try {
           ZipFile zip = new ZipFile(nm);
           Enumeration en = zip.entries();
           while (en.hasMoreElements()) {
               ZipEntry ze = (ZipEntry) en.nextElement();
               // log.debug(ze.getName());
               int lastSlash = ze.getName().lastIndexOf("/");
               String dir = "";
               if (lastSlash != -1) {
                   dir = ze.getName().substring(0, lastSlash);
               }
               if (dir.equals(path)) {
                   String n = ze.getName().substring(lastSlash + 1);
                   if ((n.length() > 0) && filter.accept(null, n)) {
                       resourceList.add(new ResourceData(n, new
URL(base + "/" + ze.getName())));
                   }
               }
           }
       } catch (Exception e) {
           log.error("Error accessing jar file " + jar, e);
       }
   }

   public List<ResourceList.ResourceData> list() {
       return list(getClass().getClassLoader());
   }


   protected void list(List<ResourceList.ResourceData> resourceList,
Enumeration<URL> resources) {
       try {
           while (resources.hasMoreElements()) {
               URL url = resources.nextElement();
               log.debug("URL = " + url + " " + url.getClass());
               if (url.getProtocol().equals("jar")) {
                   list(resourceList, url);
               } else if (url.getProtocol().equals("file")) {
                   try {
                       File directory = new File(URLDecoder.decode(url
                               .getPath(), "UTF-8"));
                       list(resourceList, directory);
                   } catch (Exception e) {
                       log.error("Error decoding url  " + url, e);
                   }
               } else {
                   log.debug("Unknown protocol : "
                           + url.getProtocol());
               }
           }
       } catch (Exception e) {
           log.error("Error getting resource list for package "
                   + packageName, e);
       }
   }

   public List<ResourceList.ResourceData> list(ClassLoader classLoader) {
       List<ResourceList.ResourceData> resourceList = new
ArrayList<ResourceList.ResourceData>();

       log.debug("list(" + packageName + ") classLoader=" + classLoader);
       if (classLoader == null) {
           classLoader = getClass().getClassLoader();
       }
       try {
           list(resourceList, classLoader.getResources(path));
       } catch (IOException e) {
           log.error("Error in getResources", e);
       }
       try {
           list(resourceList, ClassLoader.getSystemResources(path));
       } catch (IOException e) {
           log.error("Error in getSystemResources", e);
       }
       log.debug("Found " + resourceList.size()
               + " files in package '" + packageName + "'");
       return resourceList;
   }

}