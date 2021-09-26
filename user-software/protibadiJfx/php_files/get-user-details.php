<?php
if($_SERVER["REQUEST_METHOD"] == "GET") {
    $connect = mysqli_connect("localhost", "chibangla", "xQF7XAsPi", "chibangla_unmochon");
    $query = "SELECT * from user_details";
    $result = mysqli_query($connect, $query);
    $totalArray = array();
    while ($row = mysqli_fetch_array($result)) {
        $tmp = array();
        $tmp["user"] = array(
            "userid" => $row["userid"],
            "userlink" => $row["userlink"],
            //"screenshot" => $screenshot
        );
        array_push($totalArray, $tmp);
    }
    $result->close();
    echo json_encode($totalArray);
}

mysqli_close($connect);