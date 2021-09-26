<?php
if($_SERVER["REQUEST_METHOD"] == "POST") {
    $connect = mysqli_connect("localhost", "chibangla", "xQF7XAsPi", "chibangla_unmochon");
    $query = "INSERT INTO user_details(userid,userlink) VALUES('".$_POST["userid"]."','".$_POST["userlink"]."')";
    if(mysqli_query($connect, $query))
    {
        echo "true";
    }
    else echo "false ".$_POST["userid"]."  ".$_POST["userlink"];
}