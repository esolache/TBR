<?php
    $user = "root";    
    $password = "";    
    $host ="localhost";    
    $db_name ="tutorbyrequest";
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

    if(isset($_REQUEST["studentID"]) && isset($_REQUEST["tutorID"]) && isset($_REQUEST["date"]) &&
                isset($_REQUEST["time"]) && isset($_REQUEST["subject"]) && isset($_REQUEST["courseNumber"]) &&
                isset($_REQUEST["location"]) && isset($_REQUEST["description"])){
        $studentID = $_REQUEST["studentID"];
        $tutorID = $_REQUEST["tutorID"];
        $date = $_REQUEST["date"];
        $time = $_REQUEST["time"];
        $subject = $_REQUEST["subject"];
        $courseNumber = $_REQUEST["courseNumber"];
        $location = $_REQUEST["location"];
        $description = $_REQUEST["description"];
        $sql = "insert into sessions_table (student_id, tutor_id, date, time, subject, course_number, location, description)
              values ('".$studentID."', '".$tutorID."', '".$date."','".$time."',
               '".$subject."', '".$courseNumber."', '".$location."', '".$description."')";
        $result = mysqli_query($con,$sql);
    }
    // else{
    //     $studentID = '1';
    //     $tutorID = '2';
    //     $date = '01/01/2021';
    //     $time = '1:00';
    //     $subject = 'test';
    //     $courseNumber = '123';
    //     $location = 'test';
    //     $description = 'test';
    //     // $sql = "insert tutor_availability_table values ('".$subject."', '".$course."', '".$courseNumber."')";
    //     $sql = "insert into sessions_table (student_id, tutor_id, date, time, subject, course_number, location, description)
    //           values ('".$studentID."', '".$tutorID."', '".$date."','".$time."',
    //            '".$subject."', '".$courseNumber."', '".$location."', '".$description."')";
    //     $result = mysqli_query($con,$sql);
    // }

    if(!$result){
        echo 'insert failed';
    }

    mysqli_close($con);    
    ?>   