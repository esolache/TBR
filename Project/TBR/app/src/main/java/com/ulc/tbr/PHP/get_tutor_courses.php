<?php
    $user = "pistachi_user";    
    $password = "!=ioI~dW,ZIR";    
    $host ="localhost";    
    $db_name ="pistachi_TutorByRequest";
    $con = mysqli_connect($host,$user,$password,$db_name);
    
    $tutorid = null; // Initially null.
    
    // Check that netid and password are set.  If not, they'll stay null.
    if (isset($_REQUEST["tutor_id"])){
        $tutorid = $_REQUEST["tutor_id"];
        $sql = "select distinct c.subject, c.course, c.course_num from tutor_courses_table t, courses_table c where tutor_id = ('".$tutorid."') and c.course_num = t.course_num;";
        $result = mysqli_query($con,$sql);
        if($result){
            while($row = $result->fetch_assoc()){
                $return['Courses: '][] =
                    $array = array(
                        "subject" => $row['subject'],
                        "course" => $row['course'],
                        "course_num" => $row['course_num'],
                    );
            }
        }else{
            $return = null;
        }
    }else{
        $return = 'Invalid input.';
        // $tutorid = '1111111111';
        // $sql = "select distinct c.subject, c.course, c.course_num from tutor_courses_table t, courses_table c where tutor_id = ('".$tutorid."') and c.course_num = t.course_num;";
        // $result = mysqli_query($con,$sql);
        // if($result){
        //     while($row = $result->fetch_assoc()){
        //         $return['Courses: '][] =
        //             $array = array(
        //                 "subject" => $row['subject'],
        //                 "course" => $row['course'],
        //                 "course_num" => $row['course_num'],
        //             );
        //     }
        // }else{
        //     $return = null;
        // }
    }

    // Turn array into JSON.
    $myJSON = json_encode($return);

    echo $myJSON;

    mysqli_close($con);    
    ?>   