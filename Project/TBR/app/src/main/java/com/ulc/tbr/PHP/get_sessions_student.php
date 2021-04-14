<?php
    $user = "root";    
    $password = "";    
    $host ="localhost";    
    $db_name ="tutorbyrequest";
    $con = mysqli_connect($host,$user,$password,$db_name);
    
    $studentID = null; // Initially null.
    
    // Check that netid and password are set.  If not, they'll stay null.
    if (isset($_REQUEST["studentID"])){
        $studentID = $_REQUEST["studentID"];
    }  
    
    // Create SQL query.
    $sql = "select* from sessions_table where student_id = ('".$studentID."');";
    $result = mysqli_query($con,$sql);
    
    // Fetch result.
    // $row = $result->fetch_assoc();
    // Create array for JSON.
    while($row = $result->fetch_assoc()){
        $return['Sessions: '][] =
            $array = array(
                "student_id" => $row['student_id'],
                "tutor_id" => $row['tutor_id'],
                "date" => $row['date'],
                "time" => $row['time'],
                "subject" => $row['course_number'],
                "location" => $row['location'],
                "description" => $row['description'],
                "session_id" => $row['session_id'],
            );
    }

    // Turn array into JSON.
    $myJSON = json_encode($return);

    echo $myJSON;

    mysqli_close($con);    
    ?>   