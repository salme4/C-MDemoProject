package com.castis.winter.window;

import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class ImageResource {
    static HashMap imageLibrary = new HashMap();
    static String path = "image";
    static File directory;
    static MediaTracker tracker = new MediaTracker(new Component() {
    });
    private static ImageResource instance = new ImageResource();

    ImageResource getInstance() {
	return instance;
    }

    private ImageResource() {
	if (new File(path).isDirectory())
	    directory = new File(path);
	else
	    directory = new File(getCurrentPath() + path);
    }

    public static void flush() {
	imageLibrary.clear();
	imageLibrary = null;
    }
//    static Hashtable arr = null;

    public static void makeImageLibrary() {
	if (directory.isDirectory()) {
//	    arr = new Hashtable();
	    for (int i = 0; i < directory.list().length; i++) {
		String imgName = directory.list()[i];
		if (imgName.endsWith(".jpg") || imgName.endsWith(".png") || imgName.endsWith(".gif")) {
//		    arr.put(imgName, "0");
		    imageLibrary.put(imgName, loadImageFromDirectory(path + File.separator + imgName));
		}
	    }
	}
    }

    public static int getImageLibrarySize() {
	return imageLibrary.size();
    }

    public static void addImageToMediaTracker(Image img) {
	tracker.addImage(img, 0);
	try {
	    tracker.waitForID(0);
	    tracker.removeImage(img, 0);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    public static void checkImageToMediaTracker(Image img) {
	tracker.addImage(img, 0);
	try {
	    tracker.checkID(0);
	    tracker.removeImage(img, 0);
	} catch (Exception e) {
	}
    }

//    public static Image[] createPosters(CustomContent[] contents) {
//	if (contents != null && contents.length > 0) {
//	    Image[] posters = new Image[contents.length];
//	    for (int i = 0; i < posters.length; i++) {
//		posters[i] = getPosterImage(contents[i].getImageFileName());
//		if (posters[i] == null)
//		    posters[i] = getImage("default_poster_vod.jpg");
//	    }
//	    return posters;
//	} else
//	    return null;
//    }

    public static String getCurrentPath() {
	File path = new File("");
	return path.getAbsolutePath();
    }

    public static Image getImage(String fileName) {
	if (imageLibrary.containsKey(fileName)) {
//	    String str = (String) arr.get(fileName);
//	    int j = Integer.parseInt(str);
//	    j = j + 1;
//	    arr.put(fileName, "" + j);
	    return (Image) imageLibrary.get(fileName);
	} else {
	    return null;
	}
    }

//    public static void show() {
//	System.out.println();
//	Enumeration enu = arr.keys();
//	while (enu.hasMoreElements()) {
//	    String key = (String) enu.nextElement();
//	    String uu = (String) arr.get(key);
//	    int gg = Integer.parseInt(uu);
//	    if (gg == 0)
//		System.out.println(key);
//	}
//
//    }

    public static byte[] getImageUsingHTTP(URL url) {
	byte[] buf = new byte[1024];
	BufferedInputStream bis = null;
	ByteArrayOutputStream baos = null;
	BufferedOutputStream bos = null;
	try {
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    try {
		bis = new BufferedInputStream(conn.getInputStream());
	    } catch (FileNotFoundException e1) {
		buf = null;
		return buf;
	    }
	    baos = new ByteArrayOutputStream();
	    bos = new BufferedOutputStream(baos);
	    int count = 0;
	    while ((count = bis.read(buf)) != -1) {
		bos.write(buf, 0, count);
	    }
	    bos.flush();
	    return baos.toByteArray();
	} catch (Exception e) {
	    // e.printStackTrace();
	} finally {
	    buf = null;
	    try {
		if (bis != null)
		    bis.close();
		if (baos != null)
		    baos.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    bis = null;
	    bos = null;
	    baos = null;
	}
	return buf;
    }

//    public static Image getPosterImage(String urlstr) {
//	URL url = null;
//	if (urlstr != null && (urlstr.equals("")))
//	    return null;
//	try {
//	    url = new URL((URL) null, urlstr, new HttpTimeoutHandler(5000));
//	} catch (MalformedURLException e) {
//	    e.printStackTrace();
//	}
//	return getURLImage(url);
//    }

    public static Image getURLImage(String urlstr) {
	if (urlstr != null && (urlstr.equals("")))
	    return null;
	try {
	    return loadImageFromURL(new URL(urlstr));
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public static Image getURLImage(URL url) {
	if (url == null)
	    return null;
	return loadingImage(getImageUsingHTTP(url), url.getFile());
    }

    public static Image getURLImageWithoutWaiting(String urlstr) {
	if (urlstr != null && urlstr.equals(""))
	    return null;
	try {
	    return loadImageFromURLWithoutWaiting(new URL(urlstr));
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public static Image loadImageFromDirectory(String fileName) {
	Image tempImg = Toolkit.getDefaultToolkit().getImage(fileName);
	addImageToMediaTracker(tempImg);
	return tempImg;
    }

    public static Image loadImageFromURL(URL url) {
	Image tempImg = Toolkit.getDefaultToolkit().getImage(url);
	addImageToMediaTracker(tempImg);
	if (tempImg.getWidth(null) <= 0) {
	    return null;
	}
	return tempImg;
    }

    public static Image loadImageFromURLWithoutWaiting(URL url) {
	Image tempImg = Toolkit.getDefaultToolkit().getImage(url);
	return tempImg;
    }

    public static Image loadingImage(byte[] _data) {
	Image image;
	if (_data == null) {
	    image = null;
	} else {
	    image = Toolkit.getDefaultToolkit().createImage(_data);
	}
	return image;
    }

    /**
     * @param fileName
     */
    public static Image loadingImage(byte[] _data, String fileName) {
	Image image = null;
	image = loadingImage(_data);
	return image;
    }

}
