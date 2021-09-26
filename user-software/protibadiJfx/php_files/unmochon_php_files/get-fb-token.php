<?php
if($_SERVER["REQUEST_METHOD"] == "GET"){
    $connect = mysqli_connect("localhost", "chibangla", "xQF7XAsPi", "chibangla_unmochon");
    $query = "SELECT token from fb_token ORDER BY token, created_at DESC";
    $res = mysqli_query($connect, $query);
    $row = mysqli_fetch_assoc($res);
    echo $row["token"];

    mysqli_close($connect);
}