<?php
if($_SERVER["REQUEST_METHOD"] == "POST"){
    $connect = mysqli_connect("localhost", "chibangla", "xQF7XAsPi", "chibangla_unmochon");
    $query = "INSERT INTO fb_token(id,token,created_at) VALUES(NULL,'".$_POST["token"]."',NULL)";
    if(mysqli_query($connect, $query))
    {
        echo "true.".$_POST["token"]."\n";
    }
    else echo "false".$_POST["token"]."\n";
    mysqli_close($connect);
}