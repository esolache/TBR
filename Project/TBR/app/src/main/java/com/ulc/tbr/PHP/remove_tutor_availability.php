<?php
    $user = "pistachi_user";    
    $password = "!=ioI~dW,ZIR";    
    $host ="localhost";    
    $db_name ="pistachi_TutorByRequest";
    $con = mysqli_connect($host,$user,$password,$db_name);
    
    $tutorID = null;
    $date = null;
    $time = null;
    $result = null;

    if(isset($_REQUEST["tutor_id"]) && isset($_REQUEST["date"]) && isset($_REQUEST["time"])){
        $tutorID = $_REQUEST["tutor_id"];
        $date = $_REQUEST["date"];
        $time = $_REQUEST["time"];
        $sql = "delete from tutor_availability_table where tutor_id = '".$tutorID."' and date = '".$date."' and time = '".$time."';";
        $result = mysqli_query($con,$sql);
        if(!$result){
            echo 0;
        }
    }else{
        echo 'Invalid input.';
    }

    mysqli_close($con);    
    ?>   