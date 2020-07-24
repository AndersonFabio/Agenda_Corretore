<?php
$challenge = $_REQUEST['hub_challenge'];
$verify_token = $_REQUEST['hub_verify_token'];

define('VERIFY_TOKEN', '4840b6bee2d0aaf7df664bf772ec4fa2');

if ($verify_token === VERIFY_TOKEN) {
	echo strip_tags($challenge);
}
	//header('Location: );
	//$client = new HttpClient('https://www.capiteweb.com.br/CapiteWeb/rest/facebook/teste?input='.$input.'&token='.$verify_token.'&challenge='.$challenge);
	$input = json_decode(file_get_contents('php://input'), true);
	$json = json_encode($input);
	$formId = $input['entry'][0]['changes'][0]['value']['form_id'];
	$leadgenId = $input['entry'][0]['changes'][0]['value']['leadgen_id'];
	//$leadgenId = '307785353732615';
	//$leadgenId = $input['field'];
	//$sender = $input['entry'][0]['messaging'][0]['sender']['id'];

	$url = 'https://www.capiteweb.com.br/CapiteWeb/rest/facebook/teste?id='.$leadgenId;
	
	//error_log(print_r($input, true));
	$fields = array(
    'token'      => $verify_token,
    'input'      => $input,
    'challenge'    => $challenge	
	);
19
	//open connection
	$ch = curl_init();

	//set the url, number of POST vars, POST data
	curl_setopt($ch, CURLOPT_URL, $url);
	//curl_setopt($ch, CURLOPT_POST, count($fields));
	//curl_setopt($ch, CURLOPT_POSTFIELDS, http_build_query($fields));

	//execute post
	$result = curl_exec($ch);

	//close connection
	curl_close($ch);




//https://www.capiteweb.com.br/webhook.php?hub_challenge=mychallenge&hub_verify_token=4840b6bee2d0aaf7df664bf772ec4fa2
?>


