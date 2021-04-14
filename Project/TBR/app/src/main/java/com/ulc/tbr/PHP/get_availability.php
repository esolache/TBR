<?php
    $user = "root";    
    $password = "";    
    $host ="localhost";    
    $db_name ="tutorbyrequest";
    $con = mysqli_connect($host,$user,$password,$db_name);
    
    $netid = null; // Initially null.
    $loginpass = null;
    
    // Check that netid and password are set.  If not, they'll stay null.
    if (isset($_REQUEST["netid"]) && isset($_REQUEST["password"])){
        $netid = $_REQUEST["netid"];
        $loginpass = $_REQUEST["password"];
    }   
    
    // Create SQL query.
    $sql = "select* from users_table where net_id = ('".$netid."') and password = ('".$loginpass."');";
    $result = mysqli_query($con,$sql);
    
    // Fetch result.
    $row = $result->fetch_assoc();
    // Create array for JSON.
    $array = array(
        "student_id" => $row['student_id'],
        "net_id" => $row['net_id'],
        "name" => $row['name'],
        "email" => $row['email'],
        "tutor" => $row['tutor'],
        "tutee" => $row['tutee'],
    );

    // Turn array into JSON.
    $myJSON = json_encode($array);

    echo $myJSON;

    mysqli_close($con);    
    ?>   