<?php  
 $connect = mysqli_connect("localhost", "chibangla", "xQF7XAsPi", "chibangla_unmochon");
 echo "userid";
  echo $_GET["userid"];
  echo "\n";
  echo $_GET["userlink"];
  echo "\n";
  
  $file = addslashes(file_get_contents($_FILES["screenshot"]["tmp_name"]));
  $query = "INSERT INTO user_details (userid,userlink) VALUES('".$_GET["userid"]."','".$_GET["userlink"]."')";
  if(mysqli_query($connect, $query))  
  {  
    echo "inserted user_details\n";  
  }  
  else echo "could not insert into user_details\n";

  $query = "INSERT INTO screenshot_table(id,userid,screenshot) VALUES(NULL,'".$_GET["userid"]."','".$file."')";
  if(mysqli_query($connect, $query))  
  {  
    echo "inserted screenshot\n";  
  }  
  else echo "could not insert into screenshot\n";

 ?>  