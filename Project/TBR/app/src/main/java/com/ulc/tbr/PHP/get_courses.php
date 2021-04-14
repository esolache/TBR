<?php
    $user = "root";    
    $password = "";    
    $host ="localhost";    
    $db_name ="tutorbyrequest";
    $con = mysqli_connect($host,$user,$password,$db_name);
    
    $tutorid = null; // Initially null.
    
    // Check that netid and password are set.  If not, they'll stay null.
    if (isset($_REQUEST["tutorID"])){
        $tutorid = $_REQUEST["tutorID"];
    }
    else{
        $tutorid = '1111111111';
    }
    // Create SQL query.
    $sql = "select* from tutor_courses_table where tutor_id = ('".$tutorid."');";
    $result = mysqli_query($con,$sql);
    

    while($row = $result->fetch_assoc()){
        $return['Courses: '][] =
            $array = array(
                "subject" => $row['subject'],
                "course_num" => $row['course_num'],
            );
    }
    // Fetch result.
    // $row = $result->fetch_assoc();
    // // Create array for JSON.
    // $array = array(
    //     "student_id" => $row['student_id'],
    //     "net_id" => $row['net_id'],
    //     "name" => $row['name'],
    //     "email" => $row['email'],
    //     "tutor" => $row['tutor'],
    //     "tutee" => $row['tutee'],
    // );

    // Turn array into JSON.
    $myJSON = json_encode($return);

    echo $myJSON;

    mysqli_close($con);    
    ?>   