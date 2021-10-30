<?php

namespace App\Http\Controllers;

use App\Models\ImageData;
use Facebook\Exceptions\FacebookResponseException;
use App\Models\User;
use Illuminate\Http\Request;
use Facebook\Exceptions\FacebookSDKException;
use Facebook\Facebook;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Http;
use Illuminate\Support\Facades\Log;

class GraphController extends Controller
{
    private $api;
    public function __construct(Facebook $fb)
    {
        $this->middleware(function ($request, $next) use ($fb) {
//            $fb->setDefaultAccessToken(Auth::user()->token);
            $user = User::findOrFail(1);
            $fb->setDefaultAccessToken($user->token);
            $this->api = $fb;
            return $next($request);
        });
    }

    public function retrieveUserProfile(){
        try {

            $params = "first_name,last_name,age_range,gender";

            $user = $this->api->get('/me?fields='.$params)->getGraphUser();

            dd($user);

        } catch (FacebookSDKException $e) {

        }

    }

    public function getPageAccessToken($page_id){
        try {
            // Get the \Facebook\GraphNodes\GraphUser object for the current user.
            // If you provided a 'default_access_token', the '{access-token}' is optional.
            $user = User::findOrFail(1);
//            if(strlen($user->token)==0)
            $response = $this->api->get('/me/accounts', $user->token);
//            else
//                $response = $user->token;
//            $response = $this->api->get('/me/accounts', Auth::user()->token);
        } catch(FacebookResponseException $e) {
            // When Graph returns an error
            echo 'Graph returned an error: ' . $e->getMessage();
            exit;
        } catch(FacebookSDKException $e) {
            // When validation fails or other local issues
            echo 'Facebook SDK returned an error: ' . $e->getMessage();
            exit;
        }
        try {
            $pages = $response->getGraphEdge()->asArray();
            foreach ($pages as $key) {
                if ($key['id'] == $page_id) {
//                    dd("Got access token");
                    return $key['access_token'];
                }
            }
        } catch (FacebookSDKException $e) {
            echo 'Facebook SDK returned an error: \n\n';
            dd($e); // handle exception
        }

        return 'EAAFbJIHdvPUBAAEqhgh4SKiCqTiLZBgaKkVYEoz1RRETkfJ0E8yyGHYtTbKAnFYb1LAUNs8CewRwZAcMCmha3kuFaMzab24eIOW5kZANru274zZBQjzXIJZBrTqr3FBCbb4XnK5v6VcR1bp0BEsG3jpJFtfHMFWSvIJmneRCMKbia1IOpAdqbtL9FmBB9ZCxh6WUoh3lWg5QZDZD';
    }

    public function publishToPage(Request $request){
        $page_id = '3098694983567945';

        $images = ImageData::where('is_posted_to_fb', '0')->get();
        if(sizeof($images)==0){
            return response("no image to upload");
        }

        foreach($images as $image){
            $msg = "image_id: ".$image->id;

            $response = Http::get('http://unmochon.org:8080/decrypt/'.$image->image_url);
//            if(str_contains($response, "failed"))
//                return response("failed to decrypt image");
            $image_name = $image->image_url;
            $image_name = substr($image_name, 0, strpos($image_name, ".en"));
            try {
                $post = $this->api->post('/' . $page_id . '/photos', [
                    'message' => $msg,
//                    'source'  =>  $this->api->fileToUpload('https://buetcareerclub.org/test/public/img.jpg')
                    'source'  =>  $this->api->fileToUpload('http://unmochon.org/storage/app/img/'.$image_name.".jpg")
                ], $this->getPageAccessToken($page_id));
//                dd("after accesstoken returned");
//                $post = $post->getGraphNode()->asArray();
                $image->is_posted_to_fb = 1;
                $image->update();
            } catch (FacebookSDKException $e) {
                Log::debug($e);
                return response($e, 500);
            }
        }
        return response("Images posted to fb");
    }
    public function publishToProfile(Request $request){
        $absolute_image_path = '/var/www/larave/storage/app/images/lorde.png';
        $msg = "I am testing a code..please ignore this post!";
        try {
            $response = $this->api->post('/me/photos', [
                'message' => $msg,
                'source'    =>  $this->api->fileToUpload('C:\xampp\htdocs\unmochon-test\kurama.jpg')
            ])->getGraphNode()->asArray();

            if($response['id']){
                // post created
                dd("Post created");
            }
        } catch (FacebookSDKException $e) {
            dd($e); // handle exception
        }
    }
}
