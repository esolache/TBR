<?php
    $user = "pistachi_user";    
    $password = "!=ioI~dW,ZIR";    
    $host ="localhost";    
    $db_name ="pistachi_TutorByRequest";
    $con = mysqli_connect($host,$user,$password,$db_name);
    
    $studentID = null; // Initially null.
    
    // Check that netid and password are set.  If not, they'll stay null.
    if (isset($_REQUEST["student_id"])){
        $studentID = $_REQUEST["student_id"];
        $sql = "select* from sessions_table where student_id = ('".$studentID."');";
        $result = mysqli_query($con,$sql);
        if($result){
    // Create array for JSON.
            while($row = $result->fetch_assoc()){
                $return['Sessions: '][] =
                    $array = array(
                        "student_id" => $row['student_id'],
                        "tutor_id" => $row['tutor_id'],
                        "date" => $row['date'],
                        "time" => $row['time'],
                        "subject" => $row['subject'],
                        "course_number" => $row['course_number'],
                        "location" => $row['location'],
                        "description" => $row['description'],
                        "session_id" => $row['session_id'],
                    );
            }
        }else{
            $return = null;
        }
    }else{
        $return = 'Invalid input.';
    }
    
    // Turn array into JSON.
    $myJSON = json_encode($return);

    echo $myJSON;

    mysqli_close($con);    
    ?>   