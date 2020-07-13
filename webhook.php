<?php

$challenge = $_REQUEST['hub_challenge'];
$verify_token = $_REQUEST['hub_verify_token'];

if ($verify_token === '4840b6bee2d0aaf7df664bf772ec4fa2') {
echo $challenge;
}

$input = json_decode(file_get_contents('php://input'), true);
error_log(print_r($input, true));
header('Location: http://www.capiteweb.com.br/CapiteWeb/rest/facebook/teste?iput='+$input+'&token='+$verify_token);
?>