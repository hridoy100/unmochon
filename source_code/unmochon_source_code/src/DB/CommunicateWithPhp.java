package DB;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.*;
import java.util.List;

import javafx.scene.*;
import javafx.scene.image.Image;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.DEBUG_MODE;
import sample.HttpPost;
//import org.json.*;
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.swing.*;

public class CommunicateWithPhp {

    private final String CrLf = "\r\n";

    public List<User> Connection() {
        String url="http://onlinesohopathi.com/v1/read.php/";
        //url = "http://extension.mitu.latentsoft.com/";
        url = "http://banglachi.org/unmochon/get-user-details.php";

        List<User> res = new ArrayList<>();
        try {
            URL website = new URL(url);
            URLConnection connection = website.openConnection();
            BufferedReader in = new BufferedReader( new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                //System.out.println(inputLine);
            }
            in.close();

            if(DEBUG_MODE.DEBUG_CommunicateWithPhp)
                System.out.println(response.toString());

            JSONArray array;
            try {
                JSONParser helper = new JSONParser();
                array = (JSONArray) helper.parse(response.toString());
            } catch (ParseException parse) {
                // Invalid syntax
                return res;
            }
            if(DEBUG_MODE.DEBUG_CommunicateWithPhp)
                System.out.println(array);

            for (int i = 0; i < array.size(); i++) {
                JSONObject jsonobject = (JSONObject) array.get(i);
                JSONObject user = (JSONObject) jsonobject.get("user");
                String userid = (String) user.get("userid");
                String userlink = (String) user.get("userlink");
                //Blob foto = (Blob) user.get("screenshot");
                if(DEBUG_MODE.DEBUG_CommunicateWithPhp)
                    System.out.println("userid: "+userid + " userlink: "+userlink);
                User userObj = new User(userid, userlink, null);
                res.add(userObj);
            }

        } catch (Exception e) {
                e.printStackTrace();
        }
        return res;
    }

    public List<ImageList> getImageList(String userid) {
        String url="http://onlinesohopathi.com/v1/read.php/";
        //url = "http://extension.mitu.latentsoft.com/";
//        url = "http://banglachi.org/unmochon/get-image-list.php?userid="+userid;
        url = "http://banglachi.org/unmochon/user-image-list.php";

        List<ImageList> res = new ArrayList<>();
        try {
            URL website = new URL(url);
            URLConnection con = website.openConnection();
            HttpURLConnection connection = (HttpURLConnection) con;
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            PrintStream ps = new PrintStream(connection.getOutputStream());
            ps.print("&userid="+userid);

            BufferedReader in = new BufferedReader( new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
//                System.out.println(inputLine);
            }
            in.close();
            if(DEBUG_MODE.DEBUG_CommunicateWithPhp)
                System.out.println(response.toString());
            JSONArray array;
            try {
                JSONParser helper = new JSONParser();
                array = (JSONArray) helper.parse(response.toString());
            } catch (ParseException parse) {
                // Invalid syntax
                return res;
            }
            if(DEBUG_MODE.DEBUG_CommunicateWithPhp)
                System.out.println(array);

            for (int i = 0; i < array.size(); i++) {
                JSONObject jsonobject = (JSONObject) array.get(i);
                JSONObject screenshot = (JSONObject) jsonobject.get("screenshot");
                userid = (String) screenshot.get("userid");
//                Long screenshotid = (Long) screenshot.get("screenshotid");
                String image_name = (String) screenshot.get("image_name");
//                String imgTime = (String) screenshot.get("image_date");
                if(DEBUG_MODE.DEBUG_CommunicateWithPhp)
                    System.out.println("userid: "+userid + " image_name: "+image_name);
                ImageList imageObj = new ImageList(userid, image_name);
                res.add(imageObj);
            }

        } catch (Exception e) {
                e.printStackTrace();
        }
        return res;
    }

    public Image getImage(String image_name) {
        String url="http://onlinesohopathi.com/v1/read.php/";
        //url = "http://extension.mitu.latentsoft.com/";
        //url = "http://198.211.96.87/v1/protibadi.php/getImage";
        url ="http://banglachi.org/unmochon/protibadiGetImage.php";
        url ="http://banglachi.org/unmochon/get-single-image.php";
        JSONArray array = null;
        Image image = null;
        try {
            url = "http://banglachi.org/unmochon/img/"+image_name.replaceAll(" ", "%20");

            URL website = new URL(url);
            BufferedImage c = ImageIO.read(website);
            File outputfile = new File("screenshot.jpg");
            ImageIO.write(c, "jpg", outputfile); // Write the Buffered Image into an output file
            /*
            image  = ImageIO.read(new File("saved.png")); // Opening again as an Image
            return image;


            URLConnection con = website.openConnection();
            HttpURLConnection connection = (HttpURLConnection) con;
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            PrintStream ps = new PrintStream(connection.getOutputStream());
            ps.print("&image_name="+image_name);

            /*

            DEBUG::
            This Part is an alternate test code...

            BufferedReader in = new BufferedReader( new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
//                System.out.println(inputLine);
            }
            in.close();
            System.out.println(response.toString());

             */

/*            CODE::
              worked in previous code for fetching BLOB. why doesn't it work afterwards. I don't get it..

 */
            /*Image img = null;
            InputStream is = connection.getInputStream();
            OutputStream os = new FileOutputStream(new File("screenshot.jpg"));
            StringBuilder sb = new StringBuilder();
            byte[] content = new byte[1024];
            int size =0;
            while ((size=is.read(content))!=-1){
                sb.append(new String(content, 0, size));
                os.write(content, 0, size);
            }*/

            //System.out.println(sb.toString());
            //os.close();
            //is.close();
            //ps.close();
            return new Image("file:screenshot.jpg");

            /*
            try {
                JSONParser helper = new JSONParser();
                array = (JSONArray) helper.parse(response.toString());
            } catch (ParseException parse) {
                // Invalid syntax
                //return;
            }
            //System.out.println(array);
            Image img = null;
            for (int i = 0; i < array.size(); i++) {
                JSONObject jsonobject = (JSONObject) array.get(i);
                JSONObject user = (JSONObject) jsonobject.get("user");
                InputStream is = (InputStream) user.get("screenshot");
                OutputStream os = new FileOutputStream(new File("screenshot.jpg"));
                byte[] content = new byte[1024];
                int size =0;
                while ((size=is.read(content))!=-1){
                    os.write(content, 0, size);
                }
                os.close();
                is.close();
                return new Image("file:screenshot.jpg");
            }
            */
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getToken() {
        String url;
//        url = "http://198.211.96.87/protibadi/protibadi.php/getFbToken";
        url = "http://banglachi.org/unmochon/get-fb-token.php";
        String token="";
        try {
            URL website = new URL(url);
            URLConnection connection = website.openConnection();
            BufferedReader in = new BufferedReader( new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                //System.out.println(inputLine);
            }
            in.close();
            if(DEBUG_MODE.DEBUG_CommunicateWithPhp)
                System.out.println("token: "+response.toString());
            token = response.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    public boolean setToken(String token) {
        String url;
//        url = "http://198.211.96.87/protibadi/protibadi.php/setFbToken";
        url = "http://banglachi.org/unmochon/set-fb-token.php";
        try {
            URL website = new URL(url);
            URLConnection connection = website.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            PrintStream ps = new PrintStream(httpURLConnection.getOutputStream());
            ps.print("&token="+token);

            BufferedReader in = new BufferedReader( new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                //System.out.println(inputLine);
            }
            in.close();
            if(DEBUG_MODE.DEBUG_CommunicateWithPhp)
                System.out.println(response.toString());
            ps.close();

            if (response.toString().contains("true"))
                return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean verifyAdmin(String username, String password) {
        String url;
//        url = "http://198.211.96.87/protibadi/protibadi.php/verifyAdmin";
        url = "http://banglachi.org/unmochon/verify-user.php";
        try {
            URL website = new URL(url);
            HttpPost httpPost = new HttpPost(website);
            Map<String, String> map = new HashMap<>();
            map.put("username",username);
            map.put("password",password);
            return httpPost.postString(map);
            /*URLConnection connection = website.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            PrintStream ps = new PrintStream(httpURLConnection.getOutputStream());
            ps.print("&username="+username);
            ps.print("&password="+password);

            BufferedReader in = new BufferedReader( new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                //System.out.println(inputLine);
            }
            in.close();
            if(DEBUG_MODE.DEBUG_CommunicateWithPhp)
                System.out.println(response.toString());
            ps.close();
            String res = response.toString();
            if(res.contains("true")){
                return true;
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
    public boolean InsertDetailsIntoDB(String ID, String link, String imgFileName){
        try{
            // ::UPLOAD IMAGE--------------------------------------------------------------------
//            URL imageInsertUrl = new URL("http://banglachi.org/unmochon/upload-image.php");
            URL imageInsertUrl = new URL("http://banglachi.org/unmochon/insert-image-blob.php");
            HttpPost httpPost = new HttpPost(imageInsertUrl);
            String [] fileNames = new String[1];
            fileNames[0] = imgFileName;
            httpPost.setFileNames(fileNames);
            String response = httpPost.postImage();
            if(DEBUG_MODE.DEBUG_CommunicateWithPhp)
                System.out.println(response);

            String screenshotName = response.substring(response.indexOf(".")+1);

            if(DEBUG_MODE.DEBUG_CommunicateWithPhp)
                System.out.println(screenshotName);

            // ::USER_DETAILS INTO DATABASE--------------------------------------------------------------------
            URL userDetailsUrl;
            userDetailsUrl = new URL("http://banglachi.org/unmochon/insert-user-details.php");
            httpPost = new HttpPost(userDetailsUrl);
            Map<String, String> map = new HashMap<>();
            map.put("userid", ID);
            map.put("userlink", link);
            httpPost.postString(map);
            // ::IMAGE DETAILS INTO DATABASE--------------------------------------------------------------------
            URL textUrl;
            textUrl = new URL("http://banglachi.org/unmochon/insert-screenshot-table.php");
            httpPost = new HttpPost(textUrl);

            map = new HashMap<>();

            map.put("userid", ID);
            map.put("screenshot", screenshotName);
            return httpPost.postString(map);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }*/

    public boolean InsertDetailsIntoDB3(String ID, String link, String imgFileName) {



        URL userDetailsUrl, imgUploadUrl;
        try {
            userDetailsUrl = new URL("http://unmochon.org/upload_details");
            imgUploadUrl = new URL("http://unmochon.org/upload_pic/"+ID);

        HttpPost httpPost = new HttpPost(userDetailsUrl);
        httpPost = new HttpPost(userDetailsUrl);

        Map<String, String> map = new HashMap<>();
        map.put("userid", ID);
        map.put("userlink", link);
        if(httpPost.postString(map)){
            httpPost = new HttpPost(imgUploadUrl);
            String [] fileNames = new String[1];
            fileNames[0] = imgFileName;
            httpPost.setFileNames(fileNames);
            return httpPost.postImage();
        }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean InsertDetailsIntoDB2(String ID, String link, String imgFileName) {
        if(link.contains("+"))
            link = link.replace("+", "_");

        if(link.contains("&"))
            link = link.replace("&", "$");

        String url;
//        url = "http://banglachi.org/unmochon/protibadiInsertData.php?userid="+ID+"&userlink="+link;
//        url = "https://unmochon.org/protibadiInsertData.php?userid="+ID+"&userlink="+link;
        url = "http://unmochon.org/upload_pic";



        FileInputStream inputStream = null;
        OutputStream os = null;
        try {
            File image = new File(imgFileName);
            inputStream = new FileInputStream(image);

            URL website = new URL(url);
            URLConnection connection = website.openConnection();
            /*HttpURLConnection connection = (HttpURLConnection) con;
            /*Map<String,String> arguments = new HashMap<>();
            arguments.put("userid", "root");
            arguments.put("userlink", "sjh76HSn!"); // This is a fake password obviously
            StringJoiner sj = new StringJoiner("&");
            for(Map.Entry<String,String> entry : arguments.entrySet())
                sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                        + URLEncoder.encode(entry.getValue(), "UTF-8"));
            byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
            int length = out.length;
            //We can then attach our form contents to the http request with proper headers and send it.

            connection.setFixedLengthStreamingMode(length);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            connection.connect();
            //try(OutputStream os = httpURLConnection.getOutputStream()) {
//                os.write(out);
            //}
            //*/
            connection.setDoOutput(true);
            /*PrintStream ps = new PrintStream(connection.getOutputStream());
            ps.print("&userid=" + ID);
            ps.print("&userlink=" + link);
            ps.close();
        */
            //String imageString = null;
//            String imageString = encodeFileToBase64Binary(image);
//            System.out.println(imageString);
            /*ByteArrayOutputStream bos = new ByteArrayOutputStream();
            BuffImg buffImg = new BuffImg();
            BufferedImage bI = buffImg.toBufferedImage(imgFileName);
        try {
            ImageIO.write(bI, "jpg", bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();

            imageString = encoder.encode(imageBytes);


            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //FileOutputStream fileOutputStream = new FileOutputStream(new File("test.jpg"));
        //System.out.println("imgstring: "+imageString);
        ps.print("&screenshot="+imageString);
        ps.flush();
            //ps.close();
           */


            //InputStream imgIs = getClass().getResourceAsStream("/"+imgFileName);
            FileInputStream imgIs = new FileInputStream(new File(imgFileName));


            //InputStream imgIs = inputStream;
            byte[] imgData = new byte[imgIs.available()];
            imgIs.read(imgData);
            //ps.print("&screenshot=" +imgData);
            //System.out.println("imagedata: "+ imgData);
            String message1 = "";
            message1 += "-----------------------------4664151417711" + CrLf;
//            message1 += "Content-Disposition: form-data; name=\"userid\"" + CrLf;
//            message1 +="uid" + CrLf;
            //message1+= "Content-Disposition: form-data; name=\"userlink\"" + CrLf;
            //message1+="ulk" + CrLf;
//            message1 += "Content-Disposition: form-data; name=\"screenshot\"; filename=\""+imgFileName+"\""
            message1 += "Content-Disposition: form-data; name=\"image\"; filename=\""+imgFileName+"\""
                    + CrLf;
//            message1 += "Content-Type: image/jpeg" + CrLf;
            message1 += "Content-Type: image/encrypted" + CrLf;
            message1 += CrLf;

            String message3 ="";
            message3 += "Content-Disposition: form-data; name=\"userid\"" + CrLf;
            message3 +="uid" + CrLf;
            message3 += CrLf;

            String message4 ="";
            message4 += "Content-Disposition: form-data; name=\"userlink\"" + CrLf;
            message4 +="uilink" + CrLf;
            message4 += CrLf;

            String message2 = "";
            message2 += CrLf + "-----------------------------4664151417711--"
                    + CrLf;

            //connection.setRequestMethod("POST");
            //httpURLConnection.setDoOutput(true);
            connection.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=---------------------------4664151417711");
            // might not need to specify the content-length when sending chunked
            // data.


            connection.setRequestProperty("Content-Length", String.valueOf((message1
                    .length() + message2.length() + imgData.length)));

            //System.out.println("open os");
            os = connection.getOutputStream();
            //os.write(out);
            //System.out.println(message1);
            os.write(message1.getBytes());

            // SEND THE IMAGE
            int index = 0;
            int size = 1024;
            do {
                //System.out.println("write:" + index);
                if ((index + size) > imgData.length) {
                    size = imgData.length - index;
                }
                os.write(imgData, index, size);
                index += size;
            } while (index < imgData.length);
            //System.out.println("written:" + index);

            //System.out.println(message2);
            //System.out.println(imgData);
//            os.write(message3.getBytes());
//            os.write(message4.getBytes());
            os.write(message2.getBytes());
            os.flush();

            BufferedReader in = new BufferedReader( new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
//                System.out.println(inputLine);
            }
            in.close();
            //ps.close();
            //os.close();

            inputStream.close();
            return true;





        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static String encodeFileToBase64Binary(File file) throws Exception{
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        fileInputStreamReader.read(bytes);
        return new String(Base64.getEncoder().encode(bytes), "UTF-8");
    }


}
