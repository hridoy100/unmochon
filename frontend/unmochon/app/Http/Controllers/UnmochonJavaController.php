<?php

namespace App\Http\Controllers;

use App\Models\ImageData;
use App\Models\UserDetail;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Http;
use Illuminate\Support\Facades\Storage;
//use phpDocumentor\Reflection\File;

class UnmochonJavaController extends Controller
{
    public function uploadPic($userid, Request $request){
        if ($request->hasFile('image')) {
            //  Let's do everything here
            if ($request->file('image')->isValid()) {

                //
//                $validated = $request->validate([
//                    'userid' => 'string|max:100',
//                    'user' => 'string|max:100',
////                    'image' => 'mimes:jpeg,png,gif,encrypted,jpg|max:1014',
//                ]);
                $extension = $request->image->extension();
                if($extension=="" || $extension=="bin")
                    $extension="encrypted";
//                $request->image->move('/img/', $validated['name'].".".$extension);
                $imageName = $userid."_".time();
                $request->image->storeAs('/img', $imageName.".".$extension);

                $response = Http::get('http://unmochon.org:8080/encrypt/'.$imageName.".".$extension);
//                $path = "/var/www/html/storage/app/img/".$imageName.".".$extension;
//                if(File::exists($path)){
//                    unlink($path);
//                }
//                if(strcmp($response, "failed"))
//                    return response('Could not upload image :(', 500);

                ImageData::create([
                    "userid" => $userid,
                    "image_url" => $imageName.".encrypted",
                ]);
                return response("success userid: ".$userid."; image_name: ".$imageName, 200);
            }
        }
        abort(500, 'Could not upload image :(');
//        return response($request->name, 200);
        return response('Could not upload image :(', 500);
    }

    public function uploadDetails(Request $request)
    {
        $user = UserDetail::where('userid', '=', $request->userid)->first();
        if($user==null) {
            UserDetail::create([
                "userid" => $request->userid,
                "victim_name" => $request->name,
                "victim_contact" => $request->contact,
                "victim_comment" => $request->comment,
                "userlink" => $request->userlink
            ]);
        }
        return response("success userid: ".$request->userid."; userlink: ".$request->userlink, 200);
    }

}
