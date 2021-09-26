<?php
if($_SERVER["REQUEST_METHOD"] == "POST") {
    $connect = mysqli_connect("localhost", "chibangla", "xQF7XAsPi", "chibangla_unmochon");
    $query = "SELECT * from screenshot_table where userid='".$_POST["userid"]."' ORDER BY image_name DESC";
    $result = mysqli_query($connect, $query);
    $totalArray = array();
    while ($row = mysqli_fetch_array($result)) {
        $tmp = array();
        $tmp["screenshot"] = array(
            "userid" => $row["userid"],
            "image_name" => $row["image_name"],
            //"screenshot" => $screenshot
        );
        array_push($totalArray, $tmp);
    }
    $result->close();
    echo json_encode($totalArray);
}

mysqli_close($connect);