<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "tutorbyrequest";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

$net_id = 'student';
$pass = 'student';
$stmt = $conn->prepare("SELECT * FROM users_table WHERE name=?");
$stmt->bind_param('s', $net_id);
$stmt->execute();

//$sql = "SELECT* FROM users_table";
//$sql = "SELECT* FROM users_table where net_id = 'student' and password = 'student'";

//$result = $conn->query($sql);
$result = $stmt->get_result();

$user = $result->fetch_assoc();
echo "student_id: " . $user["student_id"]. " - net_id: " . $user["net_id"]. " - name: " . $user["name"]. "<br>";
echo $user;
//if ($result->num_rows > 0) {
  // output data of each row
 // while($row = $result->fetch_assoc()) {
  //  echo "student_id: " . $row["student_id"]. " - net_id: " . $row["net_id"]. " - name: " . $row["name"]. "<br>";
 // }
//} else {
 // echo "0 results";
//}
$conn->close();
?>