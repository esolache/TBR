<?php
    $user = "root";    
    $password = "";    
    $host ="localhost";    
    $db_name ="tutorbyrequest";
    $con = mysqli_connect($host,$user,$password,$db_name);
    
    $tutorID = null;
    $date = null;
    $time = null;
    $booked = 'FALSE';
    $result = null;

    if(isset($_REQUEST["TutorID"]) && isset($_REQUEST["date"]) && isset($_REQUEST["time"])){
        $tutorID = $_REQUEST["TutorID"];
        $date = $_REQUEST["date"];
        $time = $_REQUEST["time"];
        $sql = "insert tutor_availability_table values ('".$tutorID."', '".$date."', '".$time."', '".$booked."')";
        $result = mysqli_query($con,$sql);
    }

    if(!$result){
        echo 'insert failed';
    }

    mysqli_close($con);    
    ?>   