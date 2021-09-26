<?php
if($_SERVER["REQUEST_METHOD"] == "POST") {
    $connect = mysqli_connect("localhost", "chibangla", "xQF7XAsPi", "chibangla_unmochon");
    $query = "INSERT INTO screenshot_table(id,userid,image_name) VALUES(NULL,'".$_POST["userid"]."','".$_POST["screenshot"]."')";
//    $query = "UPDATE screenshot_table SET userid=".$_POST["userid"]." WHERE image_name=".$_POST["screenshot"]."";
    if(mysqli_query($connect, $query))
    {
        echo "true".$_POST["userid"]."  ".$_POST["screenshot"]."\n";
    }
    else echo "false ".$_POST["userid"]."  ".$_POST["screenshot"]."\n";
}