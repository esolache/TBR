<?php
    $user = "root";    
    $password = "";    
    $host ="localhost";    
    $db_name ="tutorbyrequest";
    $con = mysqli_connect($host,$user,$password,$db_name);
           
    // $sql = "select* from users_table where net_id = ('".$netid."') and password = ('".$loginpass."');";
    //$sql = "select* from users_table where net_id = 'student' and password = 'student';";
    //$sql = "select* from users_table;";
    // $result = mysqli_query($con,$sql);
    
    // $row = $result->fetch_assoc();
    // $array = array(
    //     "student_id" => $row['student_id'],
    //     "net_id" => $row['net_id'],
    //     "name" => $row['name'],
    //     "email" => $row['email'],
    //     "tutor" => $row['tutor'],
    //     "tutee" => $row['tutee'],
    // );

    // $myJSON = json_encode($array);

    // echo $myJSON;

    mysqli_close($con);    
    ?>   