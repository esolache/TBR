<?php
    $user = "pistachi_user";    
    $password = "!=ioI~dW,ZIR";    
    $host ="localhost";    
    $db_name ="pistachi_TutorByRequest";
    $con = mysqli_connect($host,$user,$password,$db_name);
    
    $studentID = null;
    $tutorID = null;
    $date = null;
    $time = null;
    $subject = null;
    $courseNumber = null;
    $location = null;
    $description = null;
    // $sessionID = null; // Auto increment by database.
    $result = null;

    if(isset($_REQUEST["student_id"]) && isset($_REQUEST["tutor_id"]) && isset($_REQUEST["date"]) &&
                isset($_REQUEST["time"]) && isset($_REQUEST["subject"]) && isset($_REQUEST["course_number"]) &&
                isset($_REQUEST["location"]) && isset($_REQUEST["description"])){
        $studentID = $_REQUEST["student_id"];
        $tutorID = $_REQUEST["tutor_id"];
        $date = $_REQUEST["date"];
        $time = $_REQUEST["time"];
        $subject = $_REQUEST["subject"];
        $courseNumber = $_REQUEST["course_number"];
        $location = $_REQUEST["location"];
        $description = $_REQUEST["description"];
        $sql = "insert into sessions_table (student_id, tutor_id, date, time, subject, course_number, location, description)
              values ('".$studentID."', '".$tutorID."', '".$date."','".$time."',
               '".$subject."', '".$courseNumber."', '".$location."', '".$description."')";
        $result = mysqli_query($con,$sql);
        if(!$result){
            echo 0;
        }
    }else{
        echo 'Invalid input.';
    }

    mysqli_close($con);    
    ?>   