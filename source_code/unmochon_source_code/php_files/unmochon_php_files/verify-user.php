<?php
if($_SERVER["REQUEST_METHOD"] == "POST") {
    $connect = mysqli_connect("localhost", "chibangla", "xQF7XAsPi", "chibangla_unmochon");
    $query = "SELECT password from admin where username='".$_POST["username"]."'";
    $result = mysqli_query($connect, $query);
    $row = mysqli_fetch_array($result);
    if(!strcmp($row["password"], $_POST["password"])){
        echo "true";
    }
    else echo "false";
}