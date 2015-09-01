package com.castis.winter.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileUtil {

    private static FileUtil instance = new FileUtil();

    public static FileUtil getInstance() {
	return instance;
    }

    private FileUtil() {
    }

    private final String fileName = "cache";

    private String getDirectory() {
	String directory = "";//STBInfoAgent.getInstance().getRootDirectory() + File.separator + "cvod";
	return directory;
    }

    private String getPath() {
	String path = getDirectory() + File.separator + fileName  + ".dat";
	return path;
    }
 
    public boolean checkCacheFile() {
	if (!new File(getDirectory()).exists())
	    new File(getDirectory()).mkdir();

	boolean exist = new File(getPath()).exists();
	Logger.I(this, "Check CacheFile : " + fileName + " : " + exist);
	return exist;
    }

    public Object readCachingDataFromFlash() {
	Logger.I(this, "readData Start");
	Object object = null;
	File file = new File(getPath());
	FileInputStream fileInputStream = null;
	ObjectInputStream objectInputStream = null;
	BufferedInputStream bufferedInputStream = null;
	try {
	    fileInputStream = new FileInputStream(file);
	    bufferedInputStream = new BufferedInputStream(fileInputStream);
	    objectInputStream = new ObjectInputStream(bufferedInputStream);
	    object = objectInputStream.readObject();
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}finally {
	    try {
		if (objectInputStream != null)
		    objectInputStream.close();
		if (fileInputStream != null)
		    fileInputStream.close();
		if (bufferedInputStream != null)
		    bufferedInputStream.close();
	    } catch (IOException e) {
		e.printStackTrace();
		return null;
	    } finally {
		objectInputStream = null;
		fileInputStream = null;
	    }
	    objectInputStream = null;
	    fileInputStream = null;
	}
	Logger.I(this, "readData End");
	return object;
    }

    /**
     *  deleteCachingFile
     */
    public void deleteCachingFile(){
	File file = new File(getDirectory() + File.separator + fileName + 11 + ".dat");
	if (file.exists()){
	    Logger.I(this , "file.11 exists");
	    file.delete();
	}
	
	file = new File(getDirectory() + File.separator + fileName + 13 + ".dat");
	if (file.exists()){
	    Logger.I(this , "file.13 exists");
	    file.delete();
	}
	
    }
    public void writeCachingDataToFlash(Object data) {
	Logger.I(this, "writeMenuData Start");
	File file = new File(getPath());
	if (file.exists())
	    file.delete();
	FileOutputStream fileOutputStream = null;
	ObjectOutputStream objectOutputStream = null;
	BufferedOutputStream bufferedOutputStream = null;
	try {
	    fileOutputStream = new FileOutputStream(file);
	    bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
	    objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
	    objectOutputStream.writeObject(data);

	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    try {
		if(objectOutputStream != null)
		objectOutputStream.close();
		if(fileOutputStream != null)
		fileOutputStream.close();
		if(bufferedOutputStream != null)
		bufferedOutputStream.close();
	    } catch (Exception e) {
		e.printStackTrace();
	    } finally {
		fileOutputStream = null;
		objectOutputStream = null;
		bufferedOutputStream = null;
	    }
	    fileOutputStream = null;
	    objectOutputStream = null;
	    bufferedOutputStream = null;
	}

	Logger.I(this, "write End");
    }

  
}
