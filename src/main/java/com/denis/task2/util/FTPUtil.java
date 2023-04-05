package com.denis.task2.util;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPClient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FTPUtil {

    private static List<Response> responses;
    private static final String FOLDER_NAME= "фотографии";
    private static final String PREFIX = "GRP327_";
    private static final String USER = "epiz_33891104";
    private static final String PASS = "**";

    public static List<Response> getInfo()  {

        responses = new ArrayList<>();
        String server = "185.27.134.11";
        int port = 21;
        FTPClient fClient = new FTPClient();
        fClient.setControlEncoding("utf-8");
        try {
            fClient.connect(server, port);
            fClient.enterLocalPassiveMode();
            //fClient.setFileType(FTP.BINARY_FILE_TYPE);
            fClient.login(USER, PASS);
            parsingFTP(fClient, "", "");

            fClient.logout();
            fClient.disconnect();
        } catch (IOException ex) {
            System.err.println(ex);
        } finally {
            try {
                fClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responses;
    }

    static void parsingFTP(FTPClient ftpClient, String parentDir,
                              String currentDir) throws IOException {

        String dirToList = parentDir;
        if (!currentDir.equals("")) {
            dirToList += "/" + currentDir;
        }

        FTPFile[] subFiles;
        if(dirToList.contains(" "))
        {
            ftpClient.cwd(dirToList);
            subFiles = ftpClient.listFiles();
            ftpClient.cdup();
        }
        else
        {
            subFiles = ftpClient.listFiles(dirToList);
        }

        if (subFiles != null && subFiles.length > 0) {
            for (FTPFile aFile : subFiles) {
                String currentFileName = aFile.getName();
                if (currentFileName.equals(".")
                        || currentFileName.equals("..")) {
                    // skip parent directory and directory itself
                    continue;
                }
                if (aFile.isDirectory()) {
                    parsingFTP(ftpClient, dirToList, currentFileName);
                } else {

                    if (aFile.getName().startsWith(PREFIX) && currentDir.equals(FOLDER_NAME) ) {
                        Response response = new Response();
                        response.setData(aFile.getTimestamp().getTime());
                        response.setPath(dirToList + "/" + aFile.getName());
                        response.setSize(aFile.getSize());
                        responses.add(response);
                    }

                }
            }
        }
    }

}
