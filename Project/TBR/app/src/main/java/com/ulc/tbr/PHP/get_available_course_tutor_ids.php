<?php
    $user = "pistachi_user";    
    $password = "!=ioI~dW,ZIR";    
    $host ="localhost";    
    $db_name ="pistachi_TutorByRequest";
    $con = mysqli_connect($host,$user,$password,$db_name);
    
    $subject = null; // Initially null.
    $coursenum = null;
    
    // Check that netid and password are set.  If not, they'll stay null.
    if (isset($_REQUEST["subject"]) && isset($_REQUEST["course_num"])){
        $subject = $_REQUEST["subject"];
        $coursenum = $_REQUEST["course_num"];
        $sql = "select* from tutor_courses_table where subject = ('".$subject."') and course_num = ('".$coursenum."');";
        $result = mysqli_query($con,$sql);
        if($result){
            while($row = $result->fetch_assoc()){
                $return['TutorIDs: '][] =
                    $array = array(
                        "tutor_id" => $row['tutor_id'],
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