<?php
// Check if the form was submitted
if($_SERVER["REQUEST_METHOD"] == "POST"){
    // Check if file was uploaded without errors
    if(isset($_FILES["photo"]) && $_FILES["photo"]["error"] == 0){
        $allowed = array("jpg" => "image/jpg", "jpeg" => "image/jpeg", "gif" => "image/gif", "png" => "image/png");
        $filename = $_FILES["photo"]["name"];
        $filetype = $_FILES["photo"]["type"];
        $filesize = $_FILES["photo"]["size"];
    
        // Verify file extension
        $ext = pathinfo($filename, PATHINFO_EXTENSION);
        if(!array_key_exists($ext, $allowed)) die("Error: Please select a valid file format.");
    
        // Verify file size - 5MB maximum
        $maxsize = 5 * 1024 * 1024;
        if($filesize > $maxsize) die("Error: File size is larger than the allowed limit.");
    
        // Verify MYME type of the file
        if(in_array($filetype, $allowed)){
            // Check whether file exists before uploading it
            while (file_exists("img/".$filename)){
                if(!strcmp($filetype, "image/jpeg"))
                    $filename=substr($filename, 0, strlen($filename)-5)."1.".substr($filetype,6) ;
                else
                    $filename=substr($filename, 0, strlen($filename)-4)."1.".substr($filetype,6) ;
            }
            move_uploaded_file($_FILES["photo"]["tmp_name"], "img/" . $filename);
//            $connect = mysqli_connect("localhost", "chibangla", "xQF7XAsPi", "chibangla_unmochon");
//            $query = "INSERT INTO screenshot_table(id,userid,image_name,image_blob) VALUES(NULL,NULL,'".$filename."','".addslashes(file_get_contents($_FILES["photo"]["tmp_name"]))."')";
//            if(mysqli_query($connect, $query))
//            {
//                echo "inserted screenshot.";
//            }
//            else echo "could not insert into screenshot\n";
            echo "Your file was uploaded successfully". "." .$filename; // "." is the separator used to determine filename in java
        } else{
            echo "Error: There was a problem uploading your file. Please try again."; 
        }
    } else{
        echo "Error: " . $_FILES["photo"]["error"];
    }
}
?>