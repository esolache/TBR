<?php
    $user = "pistachi_user";    
    $password = "!=ioI~dW,ZIR";    
    $host ="localhost";    
    $db_name ="pistachi_TutorByRequest";
    $con = mysqli_connect($host,$user,$password,$db_name);
    
    $subject = null; // Initially null.
    
    // Check that netid and password are set.  If not, they'll stay null.
    if (isset($_REQUEST["subject"])){
        $subject = $_REQUEST["subject"];
        $sql = "select course, course_num from courses_table where subject = ('".$subject."');";
        $result = mysqli_query($con,$sql);
        if($result){
            while($row = $result->fetch_assoc()){
                $return['Courses: '][] =
                    $array = array(
                        "course" => $row['course'],
                        "course_num" => $row['course_num'],
                    );
            }
        }else{
            $return = null;
        }
    }else {
        $return = 'Invalid input.';
    }

    // Turn array into JSON.
    $myJSON = json_encode($return);

    echo $myJSON;

    mysqli_close($con);    
    ?>   