<?php
    $user = "root";    
    $password = "";    
    $host ="localhost";    
    $db_name ="tutorbyrequest";
    $con = mysqli_connect($host,$user,$password,$db_name);
    
    $tutorID = null;
    $subject = null;
    $courseNumber = null;
    $result = null;

    if(isset($_REQUEST["tutorID"]) && isset($_REQUEST["course"]) && isset($_REQUEST["courseNumber"])){
        $tutorID = $_REQUEST["tutorID"];
        $subject = $_REQUEST["subject"];
        $courseNumber = $_REQUEST["courseNumber"];
        $sql = "insert tutor_availability_table values ('".$subject."', '".$course."', '".$courseNumber."')";
        $result = mysqli_query($con,$sql);
    }
    else{
        $tutorID = "9999999999";
        $subject = "test";
        $courseNumber = "123";
        $sql = "insert tutor_courses_table values ('".$tutorID."', '".$subject."', '".$courseNumber."')";
        $result = mysqli_query($con,$sql);
    }

    if(!$result){
        echo 'insert failed';
    }

    mysqli_close($con);    
    ?>   