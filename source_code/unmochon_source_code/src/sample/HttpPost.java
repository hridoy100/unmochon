package sample;

import encryption.CryptoUtilsTest;
import encryption.FileEncryptor;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class HttpPost {
    private final String crlf = "\r\n";
    private URL url;
    private URLConnection urlConnection;
    private OutputStream outputStream;
    private InputStream inputStream;
    private String[] fileNames;
    private String output;
    private String boundary;
    private final int bufferSize = 4096;

    public HttpPost(URL argUrl) {
        url = argUrl;
        boundary = "---------------------------"+System.currentTimeMillis();
    }

    public void setFileNames(String[] argFiles) {
        fileNames = argFiles;
    }

    public boolean postImage(){
        try {
            System.out.println("url:" + url);
            urlConnection = url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            String postData = "";
            String fileName = fileNames[0];
//            String fileName = "I:\\Codes\\Java programming\\Unmochon_2020\\imageUploadTest\\tree.jpg";
//            File file = new File(fileName);
//            CryptoUtilsTest cryptoUtilsTest = new CryptoUtilsTest();

           /* FileEncryptor.encryptFile(fileName, "hridoy");
            fileName = fileName.substring(0, fileName.indexOf("."));
            String encryptedFileName = fileName+".encrypted";
            */
            InputStream fileInputStream = new FileInputStream(fileName);

//            InputStream fileInputStream = new FileInputStream(encryptedFileName);

            byte[] fileData = new byte[fileInputStream.available()];
            fileInputStream.read(fileData);

            // ::::: PART 1 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
            String part1 = "";
            part1 += "--" + boundary + crlf;
//            File f = new File(encryptedFileName);
//            File f = encryptedFile;
            File f = new File(fileName);
            fileName = f.getName(); // we do not want the whole path, just the name
//            part1 += "Content-Disposition: form-data; name=\"photo\"; filename=\"" + fileName + "\""
            part1 += "Content-Disposition: form-data; name=\"image\"; filename=\"" + fileName + "\""
                    + crlf;

            // CONTENT-TYPE
            // TODO: add proper MIME support here
            if (fileName.endsWith("png")) {
                part1 += "Content-Type: image/png" + crlf;
            } else if(fileName.endsWith("jpg")) {
                part1 += "Content-Type: image/jpg" + crlf;
            } else if(fileName.endsWith("gif")) {
                part1 += "Content-Type: image/gif" + crlf;
            } else if(fileName.endsWith("jpeg")){
                part1 += "Content-Type: image/jpeg" + crlf;
            } else {
                part1 += "Content-Type: image/encrypted" + crlf;
            }

            part1 += crlf;
//            part1 += crlf + boundary + crlf;
            System.out.print(part1);


            // File's binary data will be sent after this part
            String message3 =crlf + boundary + crlf;;
            message3 += "Content-Disposition: form-data; name=\"userid\"" + crlf+crlf;
            message3 +="uid";
            // ::::: PART 2 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
            String part2 = crlf + "--" + boundary + "--" + crlf;
            System.out.print("Content-Length"
                    +  String.valueOf(part1.length() + message3.length() + part2.length() + fileData.length));
            urlConnection.setRequestProperty("Content-Length",
                    String.valueOf(part1.length() + message3.length()+ part2.length() + fileData.length));
            // ::::: File send ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
            outputStream = urlConnection.getOutputStream();
            outputStream.write(part1.getBytes());

            int index = 0;
            int size = bufferSize;
            do {
                System.out.println("wrote " + index + "b");
                if ((index + size) > fileData.length) {
                    size = fileData.length - index;
                }
                outputStream.write(fileData, index, size);
                index += size;
            } while (index < fileData.length);
            System.out.println("wrote " + index + "b");

            System.out.print(message3);
            outputStream.write(message3.getBytes());
            System.out.print(part2);
            outputStream.write(part2.getBytes());
            outputStream.flush();
            // ::::: Download result into the 'output' String :::::::::::::::::::::::::::::::::::::::::::::::
            inputStream = urlConnection.getInputStream();
            StringBuilder sb = new StringBuilder();
            char buff = 1024;
            int len;
            byte[] data = new byte[buff];
            do {
                len = inputStream.read(data);
                if (len > 0) {
                    sb.append(new String(data, 0, len));
                }
            } while (len > 0);
            output = sb.toString();
            System.out.println(output);
            System.out.println("DONE");
            outputStream.close();
            inputStream.close();

            if(output.contains("success"))
                return true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Close connection");
            try {
                outputStream.close();
            } catch (Exception e) {
                System.out.println(e);
            }
            try {
                inputStream.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return false;
    }

    public boolean postString(Map<String, String> map){

        try {

            URLConnection connection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            PrintStream ps = new PrintStream(httpURLConnection.getOutputStream());
//            ps.print("&id="+map.get("id"));
            for(Map.Entry<String, String> entry : map.entrySet()){
                System.out.println(entry.getKey() + " : "+ entry.getValue());
                ps.print("&"+entry.getKey()+"="+entry.getValue());
            }
            BufferedReader in = new BufferedReader( new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
//                System.out.println(inputLine);
            }
            in.close();
            System.out.println(response.toString());
            ps.close();
            String res = response.toString();
            if(res.contains("success")){
                System.out.println("posted user details");
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
