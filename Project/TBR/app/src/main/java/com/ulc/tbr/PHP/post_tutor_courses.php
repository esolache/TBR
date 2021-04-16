<?php
    $user = "pistachi_user";    
    $password = "!=ioI~dW,ZIR";    
    $host ="localhost";    
    $db_name ="pistachi_TutorByRequest";
    $con = mysqli_connect($host,$user,$password,$db_name);
    
    $tutorID = null;
    $subject = null;
    $courseNumber = null;
    $result = null;

    if(isset($_REQUEST["tutor_id"]) && isset($_REQUEST["course"]) && isset($_REQUEST["course_num"])){
        $tutorID = $_REQUEST["tutor_id"];
        $subject = $_REQUEST["subject"];
        $courseNumber = $_REQUEST["course_num"];
        $sql = "insert tutor_availability_table values ('".$subject."', '".$course."', '".$courseNumber."')";
        $result = mysqli_query($con,$sql);
        if(!$result){
            echo 0;
        }
    }else{
        echo 'Invalid input.';
    }

    mysqli_close($con);    
    ?>   