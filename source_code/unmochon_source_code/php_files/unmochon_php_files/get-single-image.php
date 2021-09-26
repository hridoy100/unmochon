<?php
if($_SERVER["REQUEST_METHOD"] == "POST") {
    $connect = mysqli_connect("localhost", "chibangla", "xQF7XAsPi", "chibangla_unmochon");
    $query = "SELECT * FROM image_list where image_name='" . $_POST["image_name"] . "'";
    $res = mysqli_query($connect, $query);
//    $result = array();
//    while($row = mysqli_fetch_array($res)) {
//        array_push($result,
//            array(
//                'image'=>base64_encode($row["image_blob"])
//            ));
//    }
//    echo json_encode(array($result));
    $row = mysqli_fetch_assoc($res);
//    header('Content-type: image/jpg');
//    echo $row["image_blob"];
    echo "<img src='data:image/jpeg;base64," . base64_encode( $row["image_blob"] )."'>";
}
mysqli_close($connect);
 ?>  