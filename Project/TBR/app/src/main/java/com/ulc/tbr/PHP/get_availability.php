<?php
    $user = "root";    
    $password = "";    
    $host ="localhost";    
    $db_name ="tutorbyrequest";
    $con = mysqli_connect($host,$user,$password,$db_name);
    
    $tutorid = null; // Initially null.
    $date = null;
    
    // Check that netid and password are set.  If not, they'll stay null.
    if (isset($_REQUEST["tutorid"]) && isset($_REQUEST["date"])){
        $tutorid = $_REQUEST["tutorid"];
        $date = $_REQUEST["date"];
    }
    // else{
    //     $tutorid = '1111111111';
    //     $date = '01/26/2021';
    // }   
    
    // Create SQL query.
    $sql = "select* from tutor_availability_table where tutor_id = ('".$tutorid."') and date = ('".$date."');";
    $result = mysqli_query($con,$sql);
    
    while($row = $result->fetch_assoc()){
        $return['Availability: '][] =
            $array = array(
                "tutor_id" => $row['tutor_id'],
                "date" => $row['date'],
                "time" => $row['time'],
                "booked" => $row['booked'],
            );
    }
    // Turn array into JSON.
    $myJSON = json_encode($return);

    echo $myJSON;

    mysqli_close($con);    
    ?>   